package vn.com.itechcorp.notifier.sms.proxy;

import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import feign.form.spring.SpringFormEncoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "smsProxy", url = "${sms.url}", configuration = SmsProxy.Configuration.class)
public interface SmsProxy {

    @PostMapping(value = "/Accounts/{accountSid}/Messages.json", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    feign.Response sendSMS(@PathVariable String accountSid,
                           @RequestBody Map<String, ?> body);

    @org.springframework.context.annotation.Configuration
    class Configuration {

        @Value("${sms.username}")
        private String username;

        @Value("${sms.password}")
        private String password;

        @Bean
        public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
            return new BasicAuthRequestInterceptor(username, password);
        }

        @Bean
        public Encoder encoder(ObjectFactory<HttpMessageConverters> converters) {
            return new SpringFormEncoder(new SpringEncoder(converters));
        }
    }
}
