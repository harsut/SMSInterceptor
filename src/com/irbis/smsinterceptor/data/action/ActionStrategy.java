package com.irbis.smsinterceptor.data.action;

import java.io.Serializable;

import com.irbis.smsinterceptor.data.SmsMessage;

import android.content.Context;


public abstract class ActionStrategy implements Serializable
{		
	private static final long serialVersionUID = 2807213874156292954L;		
	/**
	 * @param inExtendedSmsMessage
	 * @return false if received message should be aborted
	 */
	abstract public boolean performAction(Context inContext, SmsMessage inSmsMessage);	
}