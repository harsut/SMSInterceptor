package com.irbis.smsinterceptor.view.main;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.irbis.smsinterceptor.data.SmsHistory;

public class ArchiveListAdapter extends ArrayAdapter<SmsHistory>
{
	ArrayList<SmsHistory> mSmsHistories;	
	public ArchiveListAdapter(	Context inContext,
								ArrayList<SmsHistory> inSmsHistories) 
    {
            super(inContext, 0, inSmsHistories);            
            mSmsHistories  = inSmsHistories;
    }
    @Override
   	public View getView(int position, View convertView, ViewGroup parent) 
    {
    	SmsHistory smsHistory = mSmsHistories.get(position);
    	ArchiveListItemView view;
    	if(null == convertView)
    	{
    		view = ArchiveListItemView.makeView(getContext(), smsHistory);
    	}
    	else
    	{
    		view  = (ArchiveListItemView)convertView;
    		view.init(smsHistory);
    	}
   		return view;
   	}
   
}
