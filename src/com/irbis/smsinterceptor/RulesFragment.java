package com.irbis.smsinterceptor;

import java.util.ArrayList;

import com.irbis.smsinterceptor.data.DataAccessException;
import com.irbis.smsinterceptor.data.Rule;
import com.irbis.smsinterceptor.model.Model;
import com.irbis.smsinterceptor.reports.Log;
import com.irbis.smsinterceptor.view.INamedFragment;
import com.irbis.smsinterceptor.view.main.RulesListAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class RulesFragment extends ListFragment implements INamedFragment
{	
	//============================================================
	// Constants
	// ===========================================================
	public static final String TAG = RulesFragment.class.getName();
	// ===========================================================
	// Fields
	// ===========================================================
	private String mName;
	private IItemClickListener mItemClickListener;
	ArrayList<Rule> mRules;
	// ===========================================================
	// Static Methods
	// ===========================================================

	// ===========================================================
	// Constructors
	// ===========================================================
	
	
	// ===========================================================
	// Getter & Setter
	// ===========================================================
	public FragmentActivity getActivitySecured()
	{
		FragmentActivity fragmentActivity = getActivity();
		if(null == fragmentActivity)
		{
			throw new IllegalStateException("Getting access to Activity from unattached Fragment");
		}
		return fragmentActivity;
	}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	@Override
	public void setName(String inName)
	{
		mName = inName;
	}
	
	@Override
	public String getName()
	{		
		return mName;
	}

	@Override
	public Fragment getFragment()
	{	
		return this;
	}
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		fillContent();
	}
	/*@Override
	public View onCreateView(LayoutInflater inInflater, ViewGroup inContainer, Bundle inSavedInstanceState) 
	{		
		RulesView view = new RulesView(inInflater.getContext()); // TODO FAKE
		return view;
	}	*/
		
	@Override
	public void onDestroy() 
	{
	    super.onDestroy();
	}	
	@Override
	public void onListItemClick(ListView inListView, View inView, int inPosition, long inId) 
	{
	    super.onListItemClick(inListView, inView, inPosition, inId);	    
	    if(null != mItemClickListener)
	    {
	    	mItemClickListener.onListItemClick(mRules.get(inPosition));
	    }
	    if(null == mItemClickListener)
	    {
	    	Log.info(this, "NNNNNNNNNNNNUUUUUUUUUUUUUUUUUUUUUULLLLLLLLLLLLLL");
	    }
	}
	// ===========================================================
	// Methods
	// ===========================================================
	public void fillContent()
	{
		try
		{
			mRules = new ArrayList<Rule>(Model.getInstance(getActivitySecured()).getRules());
			setListAdapter(new RulesListAdapter(getActivity(),mRules));
		} 
		catch (DataAccessException e)
		{
			Application.getInstance().finishWithReport(e);
		}	
	}
	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	public void setItemClickListener(IItemClickListener inItemClickListener)
	{
		mItemClickListener = inItemClickListener;		
	}
	
	public interface IItemClickListener
	{
		void onListItemClick(Rule inRule);
	}
}
