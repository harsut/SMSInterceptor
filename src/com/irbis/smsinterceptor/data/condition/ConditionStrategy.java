package com.irbis.smsinterceptor.data.condition;

import java.io.Serializable;

import com.irbis.smsinterceptor.data.SmsMessage;


public abstract class ConditionStrategy implements Serializable
{		
	private static final long serialVersionUID = 7645995656045784268L;
	abstract public boolean matches(SmsMessage inSmsMessage);	
}
