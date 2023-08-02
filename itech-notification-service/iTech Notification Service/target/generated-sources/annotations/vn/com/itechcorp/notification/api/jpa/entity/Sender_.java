package vn.com.itechcorp.notification.api.jpa.entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import vn.com.itechcorp.notification.util.AccountType;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Sender.class)
public abstract class Sender_ extends vn.com.itechcorp.base.persistence.model.AuditableSerialIDEntry_ {

	public static volatile SingularAttribute<Sender, AccountType> accountType;
	public static volatile SingularAttribute<Sender, String> secret;
	public static volatile SingularAttribute<Sender, String> config;
	public static volatile SetAttribute<Sender, MessageType> messageTypes;
	public static volatile SingularAttribute<Sender, String> account;
	public static volatile SingularAttribute<Sender, Boolean> enabled;

	public static final String ACCOUNT_TYPE = "accountType";
	public static final String SECRET = "secret";
	public static final String CONFIG = "config";
	public static final String MESSAGE_TYPES = "messageTypes";
	public static final String ACCOUNT = "account";
	public static final String ENABLED = "enabled";

}

