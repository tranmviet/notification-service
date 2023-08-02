package vn.com.itechcorp.notifier.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vn.com.itechcorp.notifier.telegram.proxy.TelegramProxy;

@Slf4j
@Service
public class TelegramSender {

    @Value("${telegram.bot.token}")
    private String botID;

    @Value("${telegram.bot.chatID}")
    private String chatID;

    @Autowired
    private TelegramProxy telegramProxy;

    public void sendMessage(String message) {
        try {
            if (message == null) {
                log.warn("Cannot send message: message is null");
                return;
            }

            telegramProxy.sendMessage(botID, chatID, message);
        } catch (Exception e) {
            log.error("Cannot send message, error msg is : {}", e.getMessage());
        }

    }

}
