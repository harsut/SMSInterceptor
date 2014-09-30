package com.irbis.smsinterceptor.view.detailed;

import java.util.ArrayList;
import com.irbis.smsinterceptor.R;
import com.irbis.smsinterceptor.data.Rule;
import com.irbis.smsinterceptor.data.action.Action;
import com.irbis.smsinterceptor.data.condition.Condition;
import com.irbis.smsinterceptor.data.condition.ConditionStrategy;
import com.irbis.smsinterceptor.data.condition.ContentConditionStrategy;
import com.irbis.smsinterceptor.data.condition.SenderConditionStrategy;
import com.irbis.smsinterceptor.data.condition.SimpleConditionStrategy;
import com.irbis.smsinterceptor.utils.ManagedIllegalStateException;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

/** used just for simple rule building   TODO: make usefull for rule editing
 * */
public class SimpleRuleView extends LinearLayout
{
	//============================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
		private ListView mActionList;
		private EditText mName;
		private EditText mPhone;
		private EditText mSmsContent;
		
		private ActionListAdapter mActionListAdapter;
		//private Condition mSimpleCondition; 
		private Rule mRule;
	// ===========================================================
	// Static Methods
	// ===========================================================
	public static SimpleRuleView makeView(Context inContext,Rule inRule)
	{
		SimpleRuleView view = (SimpleRuleView)LayoutInflater.from(inContext).inflate(R.layout.simple_rule, null,false);
		view.init(inRule);
		return view;
	}
	// ===========================================================
	// Constructors
	// ===========================================================
	public SimpleRuleView(Context context)
	{
		super(context);
	}
	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	protected void onFinishInflate()
	{
		mActionList = (ListView) findViewById(R.id.simpleRuleActionList);
		mName = (EditText) findViewById(R.id.simpleRuleName);
		mPhone = (EditText) findViewById(R.id.simpleRulePhoneNumber);
		mSmsContent = (EditText) findViewById(R.id.SimpleRuleContent);
	}
	// ===========================================================
	// Methods
	// ===========================================================
	public void init(Rule inRule)
	{
		mActionListAdapter = new ActionListAdapter(getContext(), new ArrayList<Action>());
		mActionList.setAdapter(mActionListAdapter);
		if(null == inRule)
		{
			mRule = inRule;			
			parseRule(mRule);	
		}		  
	}
	
	public Rule save()
	{				
		if(null == mRule)
		{
			mRule = new Rule();
			ContentConditionStrategy contentConditionStrategy = new ContentConditionStrategy(mSmsContent.getText().toString());  
			SenderConditionStrategy senderConditionStrategy = new SenderConditionStrategy(mPhone.getText().toString());
			SimpleConditionStrategy simpleConditionStrategy = new SimpleConditionStrategy(senderConditionStrategy,contentConditionStrategy);
			Condition condition = new Condition(simpleConditionStrategy);
			mRule.setCondition(condition);
		}
		else
		{
			SimpleConditionStrategy simpleConditionStrategy = ((SimpleConditionStrategy)mRule
																						.getCondition()
																						.getStrategy());
			simpleConditionStrategy.getContentConditionStrategy().setContent(mSmsContent.getText().toString());
			simpleConditionStrategy.getSenderConditionStrategy().setSender(mPhone.getText().toString());			
		}
		int count = mActionListAdapter.getCount();
		mRule.clearActions();
		for(int i = 0; i< count; ++i)
		{
			mRule.addAction(mActionListAdapter.getAction(i));	
		}
		mRule.setName(mName.getText().toString());
		return mRule;
	}
	private void parseRule(Rule inRule)
	{
		ConditionStrategy strategy = inRule.getCondition().getStrategy();
		if (strategy instanceof SimpleConditionStrategy)
		{
			SimpleConditionStrategy simpleStrategy = ((SimpleConditionStrategy)strategy);			
			mSmsContent.setText(simpleStrategy.getContentConditionStrategy().getContent());
			mPhone.setText(simpleStrategy.getSenderConditionStrategy().getSender());
			mName.setText(mRule.getName());
		}	
		else
		{
			throw new IllegalStateException("SimpleRuleView is applicable only to SimpleConditions");	
		}
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================	
}
