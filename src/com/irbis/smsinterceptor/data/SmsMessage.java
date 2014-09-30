package com.irbis.smsinterceptor.data;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;


@DatabaseTable(tableName = "SmsMessages")
public class SmsMessage implements Comparable<SmsMessage> 
{
	//============================================================
	// Constants
	// ===========================================================
		public static final String  SMS_ID = "SmsID";
		public static final String  SMS_BODY = "SmsBody";
		public static final String  SMS_SENDER = "SmsSender";
		public static final String  SMS_TIMESTAMP = "SmsTimeStamp";
	// ===========================================================
	// Fields
	// ===========================================================
		@DatabaseField(generatedId = true,dataType = DataType.LONG, columnName = SMS_ID)
		private long mID;	
		@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = SMS_BODY)
		private String mBody = "";
		@DatabaseField(canBeNull = false, dataType = DataType.STRING, columnName = SMS_SENDER)
		private String mSender;
		@DatabaseField(canBeNull = false, dataType = DataType.LONG, columnName = SMS_TIMESTAMP)
		private long   mTimeStamp;
		
		private Date mDate;
	// ===========================================================
	// Static Methods
	// ===========================================================
		public static SmsMessage createFromPdus(Object[] inPdus)
		{   
		    android.telephony.SmsMessage smsMessage = android.telephony.SmsMessage.createFromPdu((byte[]) inPdus[0]);		    
		    SmsMessage result = new SmsMessage(	smsMessage.getOriginatingAddress(),
									    		smsMessage.getMessageBody(),
									    		smsMessage.getTimestampMillis());
		    for (int i = 1; i < inPdus.length; ++i)
		    {
		        smsMessage = android.telephony.SmsMessage.createFromPdu((byte[]) inPdus[i]);
		        result.mBody += smsMessage.getMessageBody();
		    }
		    return result;
		}
	// ===========================================================
	// Constructors
	// ===========================================================
		public SmsMessage()
		{
			
		}
		public SmsMessage(String inSender, String inBody, long inTimeStamp)
		{
			init(inSender,inBody,inTimeStamp);
		}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
		public String getBody()
		{
			return mBody;
		}
	
		public void setBody(String inBody)
		{
			this.mBody = inBody;
		}
	
		public String getSender()
		{
			return mSender;
		}
	
		public void setSender(String inSender)
		{
			this.mSender = inSender;
		}
	
		public long getTimeStampMillis()
		{
			return mTimeStamp;
		}
		public Date getTimeStamp()
		{
			synchronized(mDate)
			{
				if(null == mDate)
				{
					mDate = new Date(mTimeStamp);
				}
			}
			return mDate;
		}
	
		public void setTimeStampMillis(long inTimeStamp)
		{
			this.mTimeStamp = inTimeStamp;
		}
		public long getID()
		{
			return mID;
		}

		public void setID(long inID)
		{
			this.mID = inID;
		}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		public int compareTo(SmsMessage inAnother)
		{
			if(this.mTimeStamp > inAnother.mTimeStamp)
				return 1; 
			if(this.mTimeStamp < inAnother.mTimeStamp)
				return -1;
			return 0;
		}
	// ===========================================================
	// Methods
	// ===========================================================
		public void init(String inSender, String inBody, long inTimeStamp)
		{
			mSender = inSender;
			mTimeStamp = inTimeStamp;
			mBody = inBody;
		}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
		
	
}
