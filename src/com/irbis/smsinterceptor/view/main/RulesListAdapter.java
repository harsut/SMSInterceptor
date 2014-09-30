package com.irbis.smsinterceptor.view.main;

import java.util.ArrayList;

import com.irbis.smsinterceptor.data.Rule;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class RulesListAdapter extends ArrayAdapter<Rule>
{
	ArrayList<Rule> mRules;	
	public RulesListAdapter(	Context inContext,
								ArrayList<Rule> inRules) 
    {
        super(inContext, 0, inRules); 
        mRules  = inRules;
    }    
    @Override
   	public View getView(int position, View convertView, ViewGroup parent) 
    {
    	Rule rule = mRules.get(position);
    	RulesListItemView listItemView;
    	
    	if(null == convertView)
    	{
    		listItemView = RulesListItemView.makeView(getContext(), rule);
    	}
    	else
    	{
    		listItemView  = (RulesListItemView)convertView;
    		listItemView.init(rule);
    	}
   		return listItemView;
   	}
    /*public static class RulesListItemView extends RelativeLayout
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
    }*/
}
