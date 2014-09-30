/**
 * 
 */
package com.irbis.smsinterceptor.data.comparator;

import java.util.LinkedList;
import java.util.List;


public class PhoneNumber implements IPhoneComparator
{
	//============================================================
	// Constants
	// ===========================================================
		
	// ===========================================================
	// Fields
	// ===========================================================
		private String mNumber = "";
		private long mID = 0;
		private LinkedList<PhoneNumber> mPhoneNumbers = null;
	// ===========================================================
	// Static Methods
	// ===========================================================
			
	// ===========================================================
	// Constructors
	// ===========================================================		
		public PhoneNumber(long inID, String inPhoneNumber)
		{
			init(inID, inPhoneNumber);
		}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
		public String getNumber()
		{
			return mNumber;
		}
	
		public void setNumber(String inNumber)
		{
			if ( null == inNumber ) 
			{
				throw new IllegalArgumentException("inNumber must be not null.");
			}
			this.mNumber = inNumber;
		}
		public long getID()
		{
			return mID;
		}
		public void setID(long inID)
		{
			this.mID = inID;
		}
		public void init(long inID, String inNumber)
		{
			setNumber(inNumber);
			setID(inID);
		}
		public void init(PhoneNumber inPhoneNumber)
		{
			init(inPhoneNumber.getID(),inPhoneNumber.getNumber());
		}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		public String toString()
		{
			return mNumber;
		}
		@Override
		public List<PhoneNumber> getPhoneNumbers()
		{			
			if(null == mPhoneNumbers)
			{
				mPhoneNumbers = new LinkedList<PhoneNumber>();
				mPhoneNumbers.add(this);
			}
			return mPhoneNumbers;
		}
			
		@Override
		public boolean compare(IPhoneComparator inIOpponent)
		{
			assert(mNumber.length() > 0);
			boolean result = false;
			List<PhoneNumber> opponentsPhoneList = inIOpponent.getPhoneNumbers();
			for(PhoneNumber phoneNumber : opponentsPhoneList)
			{
				if(phoneNumber.equals(this))
				{
					result = true;
					break;
				}
			}
			return result;
		}
		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((mNumber == null) ? 0 : mNumber.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj)
		{
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			PhoneNumber other = (PhoneNumber) obj;
			if (mNumber == null)
			{
				if (other.mNumber != null)
					return false;
			} 
			else if (!mNumber.equals(other.mNumber))
			{				
				//TODO: Example: +37529XXXXXXX = 8029XXXXXXX
				//      and some countries has their own system... %(
				if(mID == other.mID)
				{
					//throw new IllegalStateException("If phone number is not equal, ID must not be equal too");//FIXME
				}
				return false;
			}
			if(mID != other.mID)
			{
				throw new IllegalStateException("If phone number is equal, ID must be equal too");
			}
			return true;
		}

	// ===========================================================
	// Methods
	// ===========================================================

	// ===========================================================
	// Inner and Anonymous Classes
	// ===========================================================
}
