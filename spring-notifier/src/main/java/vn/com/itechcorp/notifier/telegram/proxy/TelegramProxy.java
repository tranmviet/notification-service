package vn.com.itechcorp.notifier.telegram.proxy;

import feign.Feign;
import feign.Headers;
import feign.Logger;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.MultipartConfigElement;

@FeignClient(name = "telegramProxy", url = "${telegram.api.url}", configuration = TelegramProxy.Configuration.class)
public interface TelegramProxy {

    @GetMapping("/{botID}/sendMessage")
    feign.Response sendMessage(@PathVariable(name = "botID") String botID,
                               @RequestParam(name = "chat_id") String chatID,
                               @RequestParam(name = "text") String text);
    @PostMapping(value = "/{botID}/sendDocument", consumes = "multipart/form-data")
    feign.Response sendDocument(@PathVariable(name = "botID") String botID,
                             @RequestParam("chat_id") String chatID,
                             @RequestPart("document") MultipartFile document);

    @PostMapping(value = "/{botID}/sendPhoto", consumes = "multipart/form-data")
    feign.Response sendPhoto(@PathVariable(name = "botID") String botID,
                                @RequestParam("chat_id") String chatID,
                                @RequestPart("photo") MultipartFile photo);

    class Configuration {

//        private final ObjectFactory<HttpMessageConverters> messageConverters;
//
//        public Configuration(ObjectFactory<HttpMessageConverters> messageConverters) {
//            this.messageConverters = messageConverters;
//        }
//
//        @Bean
//        public SpringFormEncoder feignEncoder() {
//            return new SpringFormEncoder(new SpringEncoder(messageConverters));
//        }

        @Bean
        public Feign.Builder feignBuilder() {
            return Feign.builder().logLevel(Logger.Level.FULL);
        }

        @Bean
        public MultipartConfigElement multipartConfigElement() {
            MultipartConfigFactory factory = new MultipartConfigFactory();
            factory.setMaxFileSize(DataSize.ofMegabytes(10)); // set max file size
            factory.setMaxRequestSize(DataSize.ofMegabytes(10)); // set max request size
            return factory.createMultipartConfig();
        }
    }

}
