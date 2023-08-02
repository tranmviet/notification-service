package vn.com.itechcorp.notification.api.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(MessageType.class)
public abstract class MessageType_ extends vn.com.itechcorp.base.persistence.model.BaseDbEntry_ {

	public static volatile SetAttribute<MessageType, Sender> receivers;
	public static volatile SingularAttribute<MessageType, String> name;
	public static volatile SetAttribute<MessageType, Sender> senders;

	public static final String RECEIVERS = "receivers";
	public static final String NAME = "name";
	public static final String SENDERS = "senders";

}

