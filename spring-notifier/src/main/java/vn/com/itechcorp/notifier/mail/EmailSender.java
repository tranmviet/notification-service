package vn.com.itechcorp.notifier.mail;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Slf4j
@Service
public class EmailSender {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    public void sendSimpleEmail(String toEmail, String subject, String body) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

            simpleMailMessage.setFrom(fromEmail);
            simpleMailMessage.setTo(toEmail);
            simpleMailMessage.setSubject(subject);
            simpleMailMessage.setText(body);

            log.info("Sending email : {} to {}", simpleMailMessage, toEmail);
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            log.error("Can not send email. Due to: {}", e.getMessage());
        }

    }

    public void sendMimeEmail(String[] toEmail, String subject, String body) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom("noreply@baeldung.com");
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(body);

            // add image inline
//            FileSystemResource res = new FileSystemResource(new File("src/main/resources/static/image/pg_c.jpg"));
//            helper.addInline("identifier1234", res);

            // add file as attachment
            File file1 = new File("src/main/resources/static/document/hl7.log");
            helper.addAttachment(file1.getName(), new FileSystemResource(file1));

            File file2 = new File("C:\\Users\\Viet\\ITech\\spring-notifier\\src\\main\\resources\\static\\document\\QR&SVD.docx");
            helper.addAttachment("QR.docx", new FileSystemResource(file2));

            log.info("Sending email : {} to {}", message, toEmail);
            javaMailSender.send(message);
        } catch (Exception e) {
            log.error("Can not send email. Due to: {}", e.getMessage());
        }

    }
}
