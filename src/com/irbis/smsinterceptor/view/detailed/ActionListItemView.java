package com.irbis.smsinterceptor.view.detailed;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.irbis.smsinterceptor.R;
import com.irbis.smsinterceptor.data.action.Action;

public class ActionListItemView extends RelativeLayout
{	
	TextView mActionName;
	public static ActionListItemView makeView(Context inContext, Action inAction)
	{    		
		
		ActionListItemView listItemView = (ActionListItemView) LayoutInflater.from(inContext).inflate(R.layout.actions_item, null,false);
		listItemView.init(inAction);
   		return listItemView;
	}
	public ActionListItemView(Context context, AttributeSet attrs)
	{
		super(context, attrs);		
	}
	public void init(Action inAction)
	{			
		mActionName.setText(inAction.toString());		
	}
	@Override
	protected void onFinishInflate()
	{
		mActionName = (TextView) findViewById(R.id.actionName);    		
	}
}
