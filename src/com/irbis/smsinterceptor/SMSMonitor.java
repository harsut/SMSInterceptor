package com.irbis.smsinterceptor;

import java.util.Collection;

import com.irbis.smsinterceptor.data.DataAccessException;
import com.irbis.smsinterceptor.data.Rule;
import com.irbis.smsinterceptor.data.SmsMessage;
import com.irbis.smsinterceptor.model.Model;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class SMSMonitor extends BroadcastReceiver
{
	//private final static String TAG = SMSMonitor.class.getName();
	
	@Override
	public void onReceive(Context inContext, Intent inIntent)
	{	
		Bundle bundle = inIntent.getExtras();
        Object[] pdus = (Object[]) bundle.get("pdus");
        
        if (pdus.length != 0)
        {            
        	SmsMessage smsMessage = SmsMessage.createFromPdus(pdus);
        	Model model = Model.getInstance(inContext);
        	boolean processMessage = false;
        	
        	Collection<Rule> rules ;
			try
			{
				rules = model.getRules();
			} 
			catch (DataAccessException e)
			{
				Application.getInstance().finishWithReport(e);
				return;
			}
			for(Rule rule : rules)
			{
				processMessage = rule.applyRule(inContext,smsMessage);
			}       	
        	if(!processMessage)
        	{
        		abortBroadcast();	
        	}
        } 
	}
}
