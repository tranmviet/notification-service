package vn.com.itechcorp.notifier;

import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import vn.com.itechcorp.notifier.mail.EmailSender;
import vn.com.itechcorp.notifier.sms.proxy.SmsProxy;
import vn.com.itechcorp.notifier.telegram.TelegramSender;
import vn.com.itechcorp.notifier.telegram.proxy.TelegramProxy;
import vn.com.itechcorp.notifier.zalo.ZaloClient;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class NotifierApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private TelegramSender telegramSender;

    @Autowired
    private TelegramProxy telegramProxy;

    @Value("${telegram.bot.token}")
    private String botID;
    @Value("${telegram.bot.chatID}")
    private String chatID;

    @Autowired
    private EmailSender emailSender;

    @Autowired
    private ZaloClient zaloClient;

    @Test
    void test1() throws Exception {
        // simple message
        telegramSender.sendMessage("message sent from spring app");

        // send image
        File image = new File("C:\\Users\\Viet\\Downloads\\pg_c.jpg");
        Resource byteArrayResource = new ByteArrayResource(Files.readAllBytes(image.toPath()));
        MultipartFile mockMultipartFile = new MockMultipartFile("lmao", image.getName(), "image/jpeg", byteArrayResource.getInputStream());
        try (feign.Response response = telegramProxy.sendPhoto(botID, chatID, mockMultipartFile)) {
            System.out.println(response.body());
        }

        // bot send file
        File doc = new File("C:\\Users\\Viet\\Downloads\\hl7.log");
        Resource resource = new ByteArrayResource(Files.readAllBytes(doc.toPath()));
        MultipartFile multipartFile = new MockMultipartFile("lmao", doc.getName(), "text/plain", resource.getInputStream());
        try (feign.Response response = telegramProxy.sendDocument(botID, chatID, multipartFile)) {
            System.out.println(response.body());
        }

        System.out.println("done");
    }

    @Test
    void test2() {
        emailSender.sendMimeEmail(
                new String[]{"tranvi3t@gmail.com"},
                "test",
                "message from spring app"
        );

    }

    @Test
    void test3() {
        JsonObject response = zaloClient.getInterestedUsers(0,10);
        if (response != null) {
            System.out.println(response);
        }
//        zaloClient.sendMessage();

        System.out.println("pass");
    }

    @Autowired
    private SmsProxy smsProxy;

    @Value("${sms.username}")
    private String accountSid;

    @Test
    void test4() {
        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("To","+84889604605");
        requestBody.put("From","+12345401913");
        requestBody.put("Body","abcxyz");

        try(feign.Response response = smsProxy.sendSMS(accountSid,requestBody)) {
            System.out.println(response.body());
        };
        System.out.println("pass");
    }
}
