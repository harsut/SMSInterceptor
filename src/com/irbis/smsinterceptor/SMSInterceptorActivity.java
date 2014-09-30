package com.irbis.smsinterceptor;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.irbis.smsinterceptor.data.Rule;
import com.irbis.smsinterceptor.data.SmsHistory;
import com.irbis.smsinterceptor.model.Model;
import com.irbis.smsinterceptor.reports.Log;
import com.irbis.smsinterceptor.view.main.MainView;

public class SMSInterceptorActivity extends SherlockFragmentActivity
{
	public final static String	TAG	        = SMSInterceptorActivity.class.getName();
	private boolean	           mTwoPaneMode	= false;
	private ArchiveFragment	   mArchiveFragment;
	private RulesFragment	   mRulesFragment;
	private SettingsFragment	mSettingsFragment;

	@Override
	public void onCreate(final Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		final boolean twoPaneMode = getResources().getBoolean(R.bool.has_two_panes);
		mTwoPaneMode = twoPaneMode;

		final FragmentManager fragmentManager = getSupportFragmentManager();
		if (null != savedInstanceState)
		{
			mArchiveFragment = (ArchiveFragment) fragmentManager.findFragmentByTag(savedInstanceState
			        .getString(ArchiveFragment.TAG));
			mRulesFragment = (RulesFragment) fragmentManager.findFragmentByTag(savedInstanceState
			        .getString(RulesFragment.TAG));
			mSettingsFragment = (SettingsFragment) fragmentManager.findFragmentByTag(savedInstanceState
			        .getString(SettingsFragment.TAG));

			/*if(!savedInstanceState.getBoolean(buildAttachedString(ArchiveFragment.TAG)))
			{
				FragmentTransaction fm = fragmentManager.beginTransaction();
				fm.detach(mArchiveFragment);
				fm.commit();
			}*/
		}
		else
		{
			mArchiveFragment = new ArchiveFragment();
			Log.debug(mArchiveFragment,
			          "new " + mArchiveFragment);
			mRulesFragment = new RulesFragment();
			Log.debug(mRulesFragment,
			          "new " + mRulesFragment);
			mSettingsFragment = new SettingsFragment();
			Log.debug(mSettingsFragment,
			          "new " + mSettingsFragment);

			/*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.add(mArchiveFragment, ArchiveFragment.TAG);
			ft.add(mRulesFragment, RulesFragment.TAG);
			ft.add(mSettingsFragment, SettingsFragment.TAG);
			ft.commit();*/
		}
		/*if(null == mArchiveFragment)
		{
			mArchiveFragment = new ArchiveFragment();    		
			Log.debug(mArchiveFragment, "new "+mArchiveFragment);
		}    	
		if(null == mRulesFragment)
		{
			mRulesFragment = new RulesFragment();
			Log.debug(mRulesFragment, "new "+mRulesFragment);
		}    	
		if(null == mSettingsFragment)
		{
			mSettingsFragment = new SettingsFragment();
			Log.debug(mSettingsFragment, "new "+mSettingsFragment);
		}*/
		mArchiveFragment.setName(getResources().getString(R.string.archive_tab_name));
		mRulesFragment.setName(getResources().getString(R.string.rules_tab_name));
		mSettingsFragment.setName(getResources().getString(R.string.settings_tab_name));

		final MainView mainView = MainView.makeView(this,
		                                            fragmentManager,
		                                            mRulesFragment,
		                                            mArchiveFragment,
		                                            mSettingsFragment);

		Log.debug("ArchiveFragment",
		          mArchiveFragment + "setting listener ");

		mArchiveFragment.setItemClickListener(new com.irbis.smsinterceptor.ArchiveFragment.IItemClickListener()
			{
				@Override
				public void onListItemClick(final SmsHistory inSmsHistory)
				{
					if (twoPaneMode)
					{
						HistoryFragment historyFragment = (HistoryFragment) fragmentManager
						        .findFragmentByTag(HistoryFragment.TAG);
						if (null == historyFragment)
						{
							historyFragment = new HistoryFragment();
						}
						historyFragment.setSmsHistory(SMSInterceptorActivity.this,
						                              inSmsHistory);
						mainView.setDetailedFragment(historyFragment,
						                             HistoryFragment.TAG);
					}
					else
					{
						final Intent intent = new Intent(SMSInterceptorActivity.this, DetailedActivity.class);
						final Bundle bundle = new Bundle();
						bundle.putInt(DetailedActivity.TYPE,
						              DetailedActivity.HISTORY_TYPE);
						bundle.putString(DetailedActivity.SENDER,
						                 inSmsHistory.getSender());
						intent.putExtras(bundle);
						startActivity(intent);
					}
				}
			});
		mRulesFragment.setItemClickListener(new com.irbis.smsinterceptor.RulesFragment.IItemClickListener()
			{
				@Override
				public void onListItemClick(final Rule inRule)
				{
					if (twoPaneMode)
					{
						RuleFragment ruleFragment = (RuleFragment) fragmentManager.findFragmentByTag(RuleFragment.TAG);
						if (null == ruleFragment)
						{
							ruleFragment = new RuleFragment();
						}
						ruleFragment.setRule(inRule);
						mainView.setDetailedFragment(ruleFragment,
						                             RuleFragment.TAG);
					}
					else
					{
						final Intent intent = new Intent(SMSInterceptorActivity.this, DetailedActivity.class);
						final Bundle bundle = new Bundle();
						bundle.putInt(DetailedActivity.TYPE,
						              DetailedActivity.RULE_TYPE);
						bundle.putString(DetailedActivity.RULE_ID,
						                 String.valueOf(inRule.getID()));
						intent.putExtras(bundle);
						startActivity(intent);
					}
				}
			});
		if (mTwoPaneMode)
		{
			mainView.setDetailedFragment(new StubFragment(),
			                             StubFragment.TAG);
		}
		setContentView(mainView);
		Application.getInstance().attach(this);
	}

	@Override
	public void onSaveInstanceState(final Bundle savedInstanceState)
	{
		super.onSaveInstanceState(savedInstanceState);

		savedInstanceState.putString(ArchiveFragment.TAG,
		                             mArchiveFragment.getTag());
		Log.debug(mArchiveFragment,
		          mArchiveFragment.getTag());

		savedInstanceState.putString(SettingsFragment.TAG,
		                             mSettingsFragment.getTag());
		Log.debug(mSettingsFragment,
		          mSettingsFragment.getTag());

		savedInstanceState.putString(RulesFragment.TAG,
		                             mRulesFragment.getTag());
		Log.debug(mRulesFragment,
		          mRulesFragment.getTag());
	}

	/*private void saveFragmentTag(Bundle inSavedInstanceState, Fragment inFragment, String inTag, String inDefaultTag)
	{
		if(inDefaultTag.isEmpty())
			throw new IllegalStateException("inDefaultTag");
		boolean isAttached = inTag.isEmpty();
		//inSavedInstanceState.putBoolean(buildAttachedString(inDefaultTag),isAttached);
		if(isAttached)  // if Fragment not attached to fragment manager
		{
			FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
			fragmentTransaction.add(inFragment, inDefaultTag);
			fragmentTransaction.commit();
			inSavedInstanceState.putString(inDefaultTag,inDefaultTag);
		}
		else
		{
			inSavedInstanceState.putString(inDefaultTag,inTag);
		}
	}
	private String buildAttachedString(String inTag)    
	{
		return  "attached" + "inTag";
	}
	*/
	@Override
	protected void onDestroy()
	{
		Model.getInstance(this).release();
		Application.getInstance().detach(this);
		super.onDestroy();
	}
}