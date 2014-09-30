package com.irbis.smsinterceptor.data;

import java.util.ArrayList;
import java.util.List;

import com.irbis.smsinterceptor.data.comparator.IPhoneComparator;
import com.irbis.smsinterceptor.data.comparator.PhoneNumber;
import com.irbis.smsinterceptor.data.comparator.PhoneNumberResolver;


//TODO store in DB, and synchronize on changes 
public class SmsHistory
{
	//============================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
	private IPhoneComparator mPhoneComparator; 
	private ArrayList<SmsMessage> mSmsMessageList = new  ArrayList<SmsMessage>(); // FIXME change to TreeSet - automatic sort
	// ===========================================================
	// Static Methods
	// ===========================================================
	public static ArrayList<SmsHistory> parseSmsMessages(ArrayList<SmsHistory> inSmsHistories, List<SmsMessage> inSmsMessages)
	{	
		if(!inSmsMessages.isEmpty())
		{
			if(inSmsHistories.isEmpty())
			{
				PhoneNumber phone = PhoneNumberResolver.getInstance().getPhoneNumberByNumber(inSmsMessages.get(0).getSender());
				inSmsHistories.add(new SmsHistory(phone));
			}
			for(SmsMessage smsMessage : inSmsMessages)
			{
				boolean notFound = true;
				for(SmsHistory smsHistory : inSmsHistories)
				{
					if(smsHistory.addSmsMessage(smsMessage))
					{
						notFound = false;
						break;
					}
				}
				if(notFound)
				{
					PhoneNumber phone = PhoneNumberResolver.getInstance().getPhoneNumberByNumber(smsMessage.getSender());
					SmsHistory smsHistory = new SmsHistory(phone);
					smsHistory.addSmsMessage(smsMessage);
					inSmsHistories.add(smsHistory);
				}
			}	
		}
		return inSmsHistories;
	}
	public static ArrayList<SmsHistory> parseSmsMessages(List<SmsMessage> inSmsMessages)
	{	
		return parseSmsMessages(new ArrayList<SmsHistory>(),inSmsMessages);
	}
	// ===========================================================
	// Constructors
	// ===========================================================
	public SmsHistory(IPhoneComparator inPhoneComparator)
	{
		if ( null == inPhoneComparator ) 
		{
			throw new IllegalArgumentException("inPhoneComparator must be not null.");
		}
		mPhoneComparator = inPhoneComparator;		
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public String getSender()
	{
		return  mPhoneComparator.toString();
	}
	public long getTotalMessagesCount()
	{
		return  mSmsMessageList.size();
	}
	public long getNewMessagesCount()
	{
		return  getTotalMessagesCount(); // TODO count new message count)
	}
	public void resetUnreadCount()
	{
		 // TODO reset new message count)
	}
	public ArrayList<SmsMessage> getMessages()
	{
		return mSmsMessageList;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
	// ===========================================================
	// Methods
	// ===========================================================	
	public boolean addSmsMessage(SmsMessage inSmsMessage)
	{
		boolean result = false;
		PhoneNumber phoneNumber = PhoneNumberResolver.getInstance().getPhoneNumberByNumber(inSmsMessage.getSender());
		result = mPhoneComparator.compare(phoneNumber); 
		if(result)
		{			
			mSmsMessageList.add(inSmsMessage);
		}
		return result;
	}
	
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
