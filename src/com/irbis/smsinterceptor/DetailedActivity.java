package com.irbis.smsinterceptor;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.irbis.smsinterceptor.R;
import com.irbis.smsinterceptor.data.DataAccessException;
import com.irbis.smsinterceptor.model.Model;
import com.irbis.smsinterceptor.view.INamedFragment;
import com.irbis.smsinterceptor.view.detailed.DetailedView;

import android.os.Bundle;

public class DetailedActivity extends  SherlockFragmentActivity
{
	//============================================================
	// Constants
	// ===========================================================
	public final static String SENDER = "sender";
	public final static String RULE_ID = "rule";	
	public final static String TYPE = "type";
	public final static int HISTORY_TYPE = 0;
	public final static int RULE_TYPE = 1;
	public final static int SETTINGS_TYPE = 2;
	
	// ===========================================================
	// Fields
	// ===========================================================

	// ===========================================================
	// Static Methods
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================

	// ===========================================================
	// Getter & Setter
	// ===========================================================

	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        if (getResources().getBoolean(R.bool.has_two_panes)) 
        {
            finish();
            return;
        }
        
        DetailedView detailedView = DetailedView.makeView(this);
        INamedFragment fragment = prepareFragment(getIntent().getExtras().getInt(TYPE));
        detailedView.setFragment(getSupportFragmentManager(),fragment.getFragment(),fragment.getName());
        
        setContentView(detailedView);
        Application.getInstance().attach(this);
    }
    @Override
    protected void onDestroy() 
    {
    	Model.getInstance(this).release();
    	Application.getInstance().detach(this);
    	super.onDestroy();
    }
	// ===========================================================
	// Methods
	// ===========================================================
    private INamedFragment prepareFragment(final int inType)
    {
    	INamedFragment fragment = null;
    	switch(inType)
    	{
	    	case HISTORY_TYPE:
	    		fragment = prepareHistoryFragment();
	    		break;
	    	case RULE_TYPE:
	    		fragment = prepareRuleFragment();
	    		break;
	    	case SETTINGS_TYPE:
	    		fragment = prepareSettingsFragment();
	    		break;
	    	default:
	    		throw new IllegalArgumentException("Unrecognized DetailedActivity launch type"); 
    	}
    	
    	return fragment; 
    }    
    private INamedFragment prepareHistoryFragment()
    {
    	String sender = getIntent().getExtras().getString(SENDER);
        HistoryFragment fragment = new HistoryFragment();        
        fragment.setSmsHistory(this, sender);
        fragment.setName(HistoryFragment.TAG);
        return fragment; 
    }
    private INamedFragment prepareRuleFragment()
    {
    	String rule_id = getIntent().getExtras().getString(RULE_ID);
        RuleFragment fragment = new RuleFragment();        
        try
		{
			fragment.setRule(Model.getInstance(this).getRule(rule_id));
		} 
        catch (DataAccessException e)
		{
			Application.getInstance().finishWithReport(e);
		}
		fragment.setName(RuleFragment.TAG);
        return fragment;
    }
    private INamedFragment prepareSettingsFragment()
    {
        return prepareStubFragment(); 
    }
    private INamedFragment prepareStubFragment()
    {
        return(new StubFragment());
    }
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}

