package vn.com.itechcorp.notification;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.com.itechcorp.notification.api.jpa.MessageTypeRepository;
import vn.com.itechcorp.notification.api.jpa.SenderRepository;
import vn.com.itechcorp.notification.api.jpa.entity.MessageType;
import vn.com.itechcorp.notification.api.jpa.entity.Sender;
import vn.com.itechcorp.notification.api.service.dto.MessageTypeDTOCreate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
class ITechNotificationServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MessageTypeRepository messageTypeRepository;

	@Autowired
	private SenderRepository senderRepository;

	@Test
	void test1() {
		MessageTypeDTOCreate messageTypeDTOCreate = new MessageTypeDTOCreate();
		messageTypeDTOCreate.setId("TYPE1");
		messageTypeDTOCreate.setName("abcxyz");

		List<Sender> senders = new ArrayList<>();
		senders.add(new Sender(1));

		MessageType messageType = messageTypeDTOCreate.toEntry();
		messageType.setSenders(new HashSet<>(senders));

		messageTypeRepository.save(messageType);
		System.out.println("pass");
	}

	@Test
	void test2() {
		MessageType messageType = messageTypeRepository.findById("INFO").orElse(null);
//		Set<Sender> senders = messageType.getSenders();

		Sender sender1 = senderRepository.findById(1L).orElse(null);
		Sender sender2 = senderRepository.findById(2L).orElse(null);
		Set<Sender> senders = new HashSet<>();
		senders.add(sender1);
		senders.add(sender2);

		messageType.setSenders(senders);
		messageTypeRepository.save(messageType);

		System.out.println("pass");
	}
}
