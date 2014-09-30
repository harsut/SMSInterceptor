package com.irbis.smsinterceptor.data.comparator;

import java.util.LinkedList;
import java.util.List;

public class Contact implements IPhoneComparator
{
	//============================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
		private LinkedList<PhoneNumber> mPhoneNumbers = new LinkedList<PhoneNumber>();
		private long mID;
		private String mName;
	// ===========================================================
	// Static Methods
	// ===========================================================
	        
	// ===========================================================
	// Constructors
	// ===========================================================
		public Contact()
		{
			
		}
		public Contact(LinkedList<PhoneNumber> inPhoneNumbers)
		{
			mPhoneNumbers.addAll(inPhoneNumbers);
		}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
		public void addPhoneNumber(PhoneNumber inPhoneNumber)
		{
			mPhoneNumbers.add(inPhoneNumber);
		}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		public List<PhoneNumber> getPhoneNumbers()
		{
			return mPhoneNumbers;
		}
	
		@Override
		public boolean compare(IPhoneComparator inIOpponent)
		{
			boolean result = false;			
			for(PhoneNumber phoneNumberLeft : mPhoneNumbers)
			{
				result = phoneNumberLeft.compare(inIOpponent);
				if (result)
				{
					result = true;
					break;
				}
			}
			return result;
		}
		@Override
		public String toString()
		{
			return mName;
		}
	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
	
}
