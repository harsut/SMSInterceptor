package com.irbis.smsinterceptor.view.main;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.irbis.smsinterceptor.R;
import com.irbis.smsinterceptor.data.Rule;

public class RulesListItemView extends RelativeLayout
{
	TextView mItemCount;
	CheckBox mRulesItemEnabled;
	TextView mRulesItemName;
	
	public static RulesListItemView makeView(Context inContext, Rule inRule)
	{
		RulesListItemView listItemView = (RulesListItemView)LayoutInflater.from(inContext).inflate(R.layout.rules_item, null,false);
		   		
		listItemView.init(inRule);
		
   		return listItemView;
	}
	public RulesListItemView(Context context, AttributeSet attrs)
	{
		super(context, attrs);		
	}
	public void init(Rule inRule)
	{			
		mItemCount.setText(inRule.getCatchedCount()+"");
		mRulesItemEnabled.setChecked(inRule.isActive());
		mRulesItemName.setText(inRule.getName());
	}
	@Override
	protected void onFinishInflate()
	{
		mItemCount 			= (TextView) findViewById(R.id.rulesItemCount);
		mRulesItemEnabled	= (CheckBox) findViewById(R.id.rulesItemEnabled);
		mRulesItemName		= (TextView) findViewById(R.id.rulesItemName);    		 
	}
}