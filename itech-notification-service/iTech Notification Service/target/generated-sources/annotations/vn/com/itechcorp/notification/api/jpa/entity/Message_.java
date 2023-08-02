package vn.com.itechcorp.notification.api.jpa.entity;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Message.class)
public abstract class Message_ extends vn.com.itechcorp.base.persistence.model.AuditableSerialIDEntry_ {

	public static volatile SingularAttribute<Message, MessageType> messageType;
	public static volatile SingularAttribute<Message, String> subject;
	public static volatile SingularAttribute<Message, Integer> retryCount;
	public static volatile SingularAttribute<Message, String> messageTypeID;
	public static volatile SingularAttribute<Message, Date> deliveredTime;
	public static volatile SingularAttribute<Message, String> message;

	public static final String MESSAGE_TYPE = "messageType";
	public static final String SUBJECT = "subject";
	public static final String RETRY_COUNT = "retryCount";
	public static final String MESSAGE_TYPE_ID = "messageTypeID";
	public static final String DELIVERED_TIME = "deliveredTime";
	public static final String MESSAGE = "message";

}

