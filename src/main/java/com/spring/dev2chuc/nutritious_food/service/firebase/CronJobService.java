package com.spring.dev2chuc.nutritious_food.service.firebase;

import com.spring.dev2chuc.nutritious_food.model.Device;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.service.device.DeviceService;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class CronJobService {

    @Autowired
    DeviceService deviceService;

    @Autowired
    UserService userService;

    @Autowired
    DefineMessage message;

    @Autowired
    PushNotificationServiceImpl pushNotificationService;

    private void JsonObject(Device device, String body, String title) {
        JSONObject bodies = new JSONObject();
        bodies.put("to", device);
        bodies.put("collapse_key", "type_a");

        JSONObject notification = new JSONObject();
        notification.put("body", message.get(body));
        notification.put("title", message.get(title));

        bodies.put("notification", notification);

        HttpEntity<String> request = new HttpEntity<>(body);
        CompletableFuture<String> pushNotification = pushNotificationService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void checkUserAndDevice(String body, String title) {
        List<User> users = userService.findAll();
        for (User user : users) {
            List<Device> devices = deviceService.getByUser(user);
            for (Device device : devices) {
                JsonObject(device, body, title);
            }
        }
    }
}
