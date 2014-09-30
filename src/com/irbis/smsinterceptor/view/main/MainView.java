package com.irbis.smsinterceptor.view.main;

import java.util.ArrayList;
import java.util.Arrays;

import com.irbis.smsinterceptor.BuildConfig;
import com.irbis.smsinterceptor.R;
import com.irbis.smsinterceptor.reports.Log;
import com.irbis.smsinterceptor.view.INamedFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

public class MainView extends LinearLayout
{
	
	//============================================================
	// Constants
	// ===========================================================
		
	// ===========================================================
	// Fields
	// ===========================================================
		ViewPager mNavigationViewPager;
		FragmentManager mFragmentManager;
		
	// ===========================================================
	// Static Methods
	// ===========================================================
	public static MainView makeView(Context inContext,FragmentManager inFragmentManager,INamedFragment... inFragments)
	{
		View view =  LayoutInflater.from(inContext).inflate(R.layout.main_layout,null);
		MainView mainView = (MainView) view ; //new MainView(inContext);
		if(BuildConfig.DEBUG) Log.info(mainView,"inflate");
		mainView.init(inFragmentManager, inFragments);
		return mainView;
	}
	// ===========================================================
	// Constructors
	// ===========================================================
	public MainView(Context inContext,AttributeSet inAttributeSet)
	{
		super(inContext,inAttributeSet);
		if(BuildConfig.DEBUG) 
		{
			Log.info(this,"MainView");
		}
	}
	// after inflating
	public void init(FragmentManager inFragmentManager,INamedFragment... inFragments)
	{

		if(null == mNavigationViewPager)
			throw new IllegalStateException("MainView must be inflated before init() call");
		mFragmentManager = inFragmentManager;	
		NavigationPagerAdapter navigationPagerAdapter = new NavigationPagerAdapter(inFragmentManager,
																							inFragments);		
		mNavigationViewPager.setAdapter(navigationPagerAdapter);
		mNavigationViewPager.setOffscreenPageLimit(inFragments.length);
	}
	
	
	public void setDetailedFragment(Fragment inFragment,String inTAG)
	{
		if(!getContext().getResources().getBoolean(R.bool.has_two_panes))
		{
			throw new IllegalStateException("Two panes layout excepted");
		}
		FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
		fragmentTransaction.replace(R.id.detailedFragmentContainer, inFragment,inTAG);
		fragmentTransaction.commit();
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
		mNavigationViewPager = (ViewPager) findViewById(R.id.pager);
	}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	private class NavigationPagerAdapter extends FragmentPagerAdapter 
	{	
		ArrayList<INamedFragment> mFragments;
		FragmentManager mFragmentManager;
	    public NavigationPagerAdapter(	FragmentManager inFragmentManager,
	    								INamedFragment... inFragments) 
	    {
	    	super(inFragmentManager);
	    	mFragmentManager = inFragmentManager;
	    	mFragments = new ArrayList<INamedFragment>(Arrays.asList(inFragments));
	    }
	    public NavigationPagerAdapter(	FragmentManager inFragmentManager,
										int inExpectedCount) 
		{
			super(inFragmentManager);
			mFragments = new ArrayList<INamedFragment>(inExpectedCount);
		}
	
	    @Override
	    public Fragment getItem(int position) 
	    {
	    	Fragment fragment = mFragments.get(position).getFragment();
	    	/*FragmentTransaction fm = mFragmentManager.beginTransaction();
    		fm.detach(fragment);
    		fm.commit();*/
	    	return fragment;
	    }
	
	    @Override
	    public int getCount() 
		{
	    	return mFragments.size();
	    }	    
	    @Override
	    public String getPageTitle(int position)
	    {
			return mFragments.get(position).getName();	    	
	    }
	}
}
