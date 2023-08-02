package vn.com.itechcorp.notification.api.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vn.com.itechcorp.notification.util.AccountType;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Receiver.class)
public abstract class Receiver_ extends vn.com.itechcorp.base.persistence.model.AuditableSerialIDEntry_ {

	public static volatile SingularAttribute<Receiver, AccountType> accountType;
	public static volatile SingularAttribute<Receiver, String> config;
	public static volatile SetAttribute<Receiver, MessageType> messageTypes;
	public static volatile SingularAttribute<Receiver, String> account;
	public static volatile SingularAttribute<Receiver, Boolean> enabled;

	public static final String ACCOUNT_TYPE = "accountType";
	public static final String CONFIG = "config";
	public static final String MESSAGE_TYPES = "messageTypes";
	public static final String ACCOUNT = "account";
	public static final String ENABLED = "enabled";

}

