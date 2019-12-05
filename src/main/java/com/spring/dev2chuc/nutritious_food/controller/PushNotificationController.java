package com.spring.dev2chuc.nutritious_food.controller;

import com.spring.dev2chuc.nutritious_food.model.Device;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.payload.response.ApiResponseError;
import com.spring.dev2chuc.nutritious_food.service.device.DeviceService;
import com.spring.dev2chuc.nutritious_food.service.firebase.PushNotificationServiceImpl;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/send")
    public ResponseEntity<?> send() throws JSONException {

        List<User> users = userService.findAll();
        for (User user : users) {
            List<Device> devices = deviceService.getByUser(user);
            for (Device device : devices) {
                JSONObject body = new JSONObject();
                body.put("to", device);
                body.put("collapse_key", "type_a");

                JSONObject notification = new JSONObject();
                notification.put("body", "JSA Notification");
                notification.put("title", "Happy Message!");

                body.put("notification", notification);

                HttpEntity<String> request = new HttpEntity<>(body.toString());
                CompletableFuture<String> pushNotification = pushNotificationService.send(request);
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
