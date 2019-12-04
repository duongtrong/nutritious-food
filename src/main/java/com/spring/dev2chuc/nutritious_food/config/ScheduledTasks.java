package com.spring.dev2chuc.nutritious_food.config;

import com.spring.dev2chuc.nutritious_food.model.Device;
import com.spring.dev2chuc.nutritious_food.model.User;
import com.spring.dev2chuc.nutritious_food.service.device.DeviceService;
import com.spring.dev2chuc.nutritious_food.service.firebase.DefineMessage;
import com.spring.dev2chuc.nutritious_food.service.firebase.PushNotificationServiceImpl;
import com.spring.dev2chuc.nutritious_food.service.user.UserService;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Component
public class ScheduledTasks {

    @Autowired
    DefineMessage message;

    @Autowired
    PushNotificationServiceImpl pushNotificationService;

    @Autowired
    DeviceService deviceService;

    @Autowired
    UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);

    @Scheduled(cron = "0 26 9 * * ?")
    public void scheduleTaskWithCronToday() {
        List<User> users = userService.findAll();
        for (User user : users) {
            List<Device> devices = deviceService.getByUser(user);
            for (Device device : devices) {
                JsonObject(device, "morning.message", "today.message");
            }
        }
        logger.info("Push notification success!");
    }

    @Scheduled(cron = "0 0 12 * * ?")
    public void scheduleTaskWithCronNoonday() {
        List<User> users = userService.findAll();
        for (User user : users) {
            List<Device> devices = deviceService.getByUser(user);
            for (Device device : devices) {
                JsonObject(device, "noonday.message", "today.message");
            }
        }
        logger.info(message.get ("noonday.message"));
    }

    @Scheduled(cron = "0 45 12 * * ?")
    public void scheduleTaskWithCronLastNoonday() {
        List<User> users = userService.findAll();
        for (User user : users) {
            List<Device> devices = deviceService.getByUser(user);
            for (Device device : devices) {
                JsonObject(device, "last.noonday.message", "today.message");
            }
        }
        logger.info(message.get ("last.noonday.message"));
    }

    @Scheduled(cron = "0 0 19 * * ?")
    public void scheduleTaskWithCronDinner() {
        List<User> users = userService.findAll();
        for (User user : users) {
            List<Device> devices = deviceService.getByUser(user);
            for (Device device : devices) {
                JsonObject(device, "dinner.message", "today.message");
            }
        }
        logger.info(message.get ("dinner.message"));
    }

    @Scheduled(cron = "0 30 19 * * ?")
    public void scheduleTaskWithCronLastDinner() {
        List<User> users = userService.findAll();
        for (User user : users) {
            List<Device> devices = deviceService.getByUser(user);
            for (Device device : devices) {
                JsonObject(device, "last.dinner.message", "today.message");
            }
        }
        logger.info(message.get ("last.dinner.message"));
    }

    private void JsonObject(Device device, String body, String title) {
        JSONObject bodies = new JSONObject();
        bodies.put("to", device);
        bodies.put("collapse_key", "type_a");

        JSONObject notification = new JSONObject();
        notification.put("body", message.get(body));
        notification.put("title", message.get(title));

        bodies.put("notification", notification);

        HttpEntity<String> request = new HttpEntity<>(body.toString());
        CompletableFuture<String> pushNotification = pushNotificationService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try {
            String firebaseResponse = pushNotification.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
