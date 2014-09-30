package com.irbis.smsinterceptor.view.detailed;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.irbis.smsinterceptor.R;
import com.irbis.smsinterceptor.data.SmsMessage;

public class HistoryListItemView extends RelativeLayout
{
	
	TextView mPhoneNumber;
	TextView mDateTimeStamp;
	TextView mSmsText;
	public static HistoryListItemView makeView(Context inContext, SmsMessage inSmsMessage)
	{
		HistoryListItemView historyListItem = (HistoryListItemView)LayoutInflater.from(inContext).inflate(R.layout.history_item, null,false);
		historyListItem.init(inSmsMessage);
   		return historyListItem;
	}
	public HistoryListItemView(Context context, AttributeSet attrs)
	{
		super(context, attrs);		
	}
	public void init(SmsMessage inSmsMessage)
	{			
		mDateTimeStamp.setText(DateFormat.format("MM.dd.yy hh:mm",inSmsMessage.getTimeStampMillis()));
		mPhoneNumber.setText(inSmsMessage.getSender());
		mSmsText.setText(inSmsMessage.getBody());
	}
	@Override
	protected void onFinishInflate()
	{
		mPhoneNumber 	= (TextView) findViewById(R.id.history_item_phone_number);
		mDateTimeStamp 	= (TextView) findViewById(R.id.history_item_datetime_stamp);
		mSmsText 		= (TextView) findViewById(R.id.history_item_sms_text);
	}
}
