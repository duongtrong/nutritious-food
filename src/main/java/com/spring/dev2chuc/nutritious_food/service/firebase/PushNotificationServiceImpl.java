package com.spring.dev2chuc.nutritious_food.service.firebase;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

@Service
public class PushNotificationServiceImpl {

//    @Value("#{${app.notifications.defaults}}")
//    private Map<String, String> defaults;
//
//    private Logger logger = LoggerFactory.getLogger(PushNotificationServiceImpl.class);
//    private FCMService fcmService;
//
//    public PushNotificationServiceImpl(FCMService fcmService) {
//        this.fcmService = fcmService;
//    }
//
//    @Scheduled(cron = "0 45 13 * * ?")
//    public void sendSamplePushNotification() {
//        try {
//            fcmService.sendMessageWithoutData(getSamplePushNotificationRequest());
//        } catch (InterruptedException | ExecutionException e) {
//            logger.error(e.getMessage());
//        }
//    }
//
//    public void sendPushNotification(PushNotificationRequest request) {
//        try {
//            fcmService.sendMessage(getSamplePayloadData(), request);
//        } catch (InterruptedException | ExecutionException e) {
//            logger.error(e.getMessage());
//        }
//    }
//
//    public void sendPushNotificationWithoutData(PushNotificationRequest request) {
//        try {
//            fcmService.sendMessageWithoutData(request);
//        } catch (InterruptedException | ExecutionException e) {
//            logger.error(e.getMessage());
//        }
//    }
//
//    public void sendPushNotificationToToken(PushNotificationRequest request) {
//        try {
//            fcmService.sendMessageToToken(request);
//        } catch (InterruptedException | ExecutionException e) {
//            logger.error(e.getMessage());
//        }
//    }
//
//
//    private Map<String, String> getSamplePayloadData() {
//        Map<String, String> pushData = new HashMap<>();
//        pushData.put("messageId", defaults.get("payloadMessageId"));
//        pushData.put("text", defaults.get("payloadData") + " " + LocalDateTime.now());
//        return pushData;
//    }
//
//
//    private PushNotificationRequest getSamplePushNotificationRequest() {
//        PushNotificationRequest request = new PushNotificationRequest(defaults.get("title"),
//                defaults.get("message"),
//                defaults.get("topic"));
//        return request;
//    }



    private static final String FIREBASE_SERVER_KEY = "AAAAAZMywAs:APA91bE22xV5JdWo5U5hR9lOo14eHPtlzTt39_JiT2NJ2cIhnxnHpEfZFyT4j_4BvBcV7W4cmpxRZRpGX5Tt37zavnwFOZFfzsy0uQHHo4ylimQQ0WXoiQjdvBPKqfFFsTk-kVWOG_ex";
    private static final String FIREBASE_API_URL = "https://fcm.googleapis.com/fcm/send";

//    private static final String FIREBASE_SERVER_KEY = "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiI3IiwiaWF0IjoxNTc1Mzc5NzMxLCJleHAiOjE1NzU5ODQ1MzF9.jY-RdCVSUkNYuSVcV-gBRiRCm89KvVQcPeJEJEwvQFQHRGUsuFlZxLOvRDKByEKPhmSAlgd44Y9nnEFQVmYE6A";
//    private static final String FIREBASE_API_URL = "localhost:9000/api/address";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        /**
         https://fcm.googleapis.com/fcm/send
         Content-Type:application/json
         Content-Type:application/json
         Authorization:key=FIREBASE_SERVER_KEY*/

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();
        interceptors.add(new FCMSender("Authorization", "key=" + FIREBASE_SERVER_KEY));
//        interceptors.add(new FCMSender("Authorization", FIREBASE_SERVER_KEY));
        interceptors.add(new FCMSender("Content-Type", "application/json"));
        restTemplate.setInterceptors(interceptors);
        String firebaseResponse = restTemplate.postForObject(FIREBASE_API_URL, entity, String.class);
        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
