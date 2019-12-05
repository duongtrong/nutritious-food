package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.config.ScheduledTasks;
import com.spring.dev2chuc.nutritious_food.model.Device;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.DeviceRequest;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.payload.response.DeviceDTO;
import com.spring.dev2chuc.nutritious_food.service.device.DeviceService;
import com.spring.dev2chuc.nutritious_food.service.firebase.PushNotificationServiceImpl;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/push")
public class PushNotificationController {

    @Autowired
    PushNotificationServiceImpl pushNotificationService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/send", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> send() throws JSONException {

        List<User> users = userService.findAll();
        for (User user : users) {
            List<Device> devices = deviceService.getByUser(user);
            for (Device device : devices) {
                JSONObject body = new JSONObject();
                body.put("to", "faKUFdp5bo0:APA91bGyuiI9K070mPjeNa0fGhHNR4DbO64_c10QvwkjXMafJ_-oRpRoe39avwDKinJbq0ODQCSrCXm9T-ANLZRYUMS3S9EwEjiybMEJYf1o0zWxI6KJO-je8fiWHwiIIHqur_DbQ0kn");
                body.put("collapse_key", "type_a");

                JSONObject notification = new JSONObject();
                notification.put("body", "JSA Notification");
                notification.put("title", "Happy Message!");

                body.put("notification", notification);

                HttpEntity<String> request = new HttpEntity<>(body.toString());
                System.out.println(request);
                CompletableFuture<String> pushNotification = pushNotificationService.send(request);
                System.out.println(pushNotification);
                CompletableFuture.allOf(pushNotification).join();

                try {
                    String firebaseResponse = pushNotification.get();
                    return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }

        return new ResponseEntity<>(
                new ApiResponseError(HttpStatus.NOT_FOUND.value(), "Device not found"), HttpStatus.NOT_FOUND);
    }
}
