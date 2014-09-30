package com.irbis.smsinterceptor.data.comparator;

import java.util.HashMap;

public final class PhoneNumberResolver
{
	//============================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
		static private PhoneNumberResolver mInstance = null;
		
		//HashMap<Long,PhoneNumber> mPhonesByID = new HashMap<Long,PhoneNumber>();
		HashMap<String,PhoneNumber> mPhonesByNumber = new HashMap<String,PhoneNumber>();
		
	// ===========================================================
	// Static Methods
	// ===========================================================
		static synchronized public PhoneNumberResolver getInstance()
    	{
    	   if(null == mInstance)
    	   {
    		   mInstance = new PhoneNumberResolver();
    	   }
    	   return mInstance;
		}
	// ===========================================================
	// Constructors
	// ===========================================================
		private PhoneNumberResolver(){}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
		synchronized public PhoneNumber getPhoneNumberByNumber(String inNumber)
		{	
			if ( null == inNumber ) 
			{
				throw new IllegalArgumentException("PhoneNumberResolver::getPhoneNumberByNumber() inNumber must be not null.");
			}
			PhoneNumber phoneNumber = mPhonesByNumber.get(inNumber);
			if(null == phoneNumber)
			{
				phoneNumber = new PhoneNumber(0,inNumber);
				mPhonesByNumber.put(inNumber, phoneNumber);
			}
			return phoneNumber;
		}
		synchronized public void addPhoneNumber(PhoneNumber inPhoneNumber)
		{	
			mPhonesByNumber.put(inPhoneNumber.getNumber(), inPhoneNumber);
		}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
	    
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
