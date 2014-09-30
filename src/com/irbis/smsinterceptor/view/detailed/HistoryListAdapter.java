package com.irbis.smsinterceptor.view.detailed;

import java.util.ArrayList;

import com.irbis.smsinterceptor.data.SmsMessage;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class HistoryListAdapter extends ArrayAdapter<SmsMessage> 
{
	ArrayList<SmsMessage> mSmsMessages;	
	public HistoryListAdapter(	Context inContext,
								ArrayList<SmsMessage> inSmsMessages) 
    {
            super(inContext, 0, inSmsMessages); 
            
            mSmsMessages  = inSmsMessages;
    }    
    @Override
   	public View getView(int position, View convertView, ViewGroup parent) 
    {
    	SmsMessage smsMessage = mSmsMessages.get(position);
    	HistoryListItemView view;
    	if(null == convertView)
    	{
    		view = HistoryListItemView.makeView(getContext(), smsMessage);
    	}
    	else
    	{
    		view  = (HistoryListItemView)convertView;
    		view.init(smsMessage);
    	}
   		return view;
   	}

}
