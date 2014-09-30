package com.irbis.smsinterceptor.view.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.irbis.smsinterceptor.R;
import com.irbis.smsinterceptor.data.SmsHistory;

public class ArchiveListItemView extends RelativeLayout
{	
	TextView mSenderTextView;
	TextView mNewMessagesCount;
	TextView mTotalMessagesCount;
	public static ArchiveListItemView makeView(Context inContext, SmsHistory inSmsHistory)
	{    		
		
		ArchiveListItemView archiveListItemView = (ArchiveListItemView) LayoutInflater.from(inContext).inflate(R.layout.archive_item, null,false);
		archiveListItemView.init(inSmsHistory);
   		return archiveListItemView;
	}
	public ArchiveListItemView(Context context, AttributeSet attrs)
	{
		super(context, attrs);		
	}
	public void init(SmsHistory inSmsHistory)
	{			
		mSenderTextView.setText(inSmsHistory.getSender());
		mNewMessagesCount.setText(String.valueOf(inSmsHistory.getNewMessagesCount()));
		mTotalMessagesCount.setText(String.valueOf(inSmsHistory.getTotalMessagesCount()));
	}
	@Override
	protected void onFinishInflate()
	{
		mSenderTextView = (TextView) findViewById(R.id.archive_item_sender);
		mNewMessagesCount = (TextView) findViewById(R.id.archive_item_new);
		mTotalMessagesCount = (TextView) findViewById(R.id.archive_item_total);    		
	}
}