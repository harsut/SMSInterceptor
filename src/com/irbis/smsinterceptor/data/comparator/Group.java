package com.irbis.smsinterceptor.data.comparator;

import java.util.LinkedList;
import java.util.List;

public class Group implements IPhoneComparator
{
	//============================================================
	// Constants
	// ===========================================================

	// ===========================================================
	// Fields
	// ===========================================================
		private String mName;
		private LinkedList<PhoneNumber> mPhoneNumbers = new LinkedList<PhoneNumber>();
		private boolean mDirty = true; // true if mContacts has new contacts whoes phonenumbers not included to mPhoneNumbers
		
		
		private LinkedList<Contact> mContacts = new LinkedList<Contact>();
		private long mID;
	// ===========================================================
	// Static Methods
	// ===========================================================
	        
	// ===========================================================
	// Constructors
	// ===========================================================
		public Group()
		{
			
		}
		public Group(LinkedList<Contact> inContacts)
		{
			mDirty = true;
			mContacts.addAll(inContacts);
		}
	// ===========================================================
	// Getter & Setter
	// ===========================================================
		public void addContact(Contact inContact)
		{
			mDirty = true;
			mContacts.add(inContact);
		}
	// ===========================================================
	// Methods for/from SuperClass/Interfaces
	// ===========================================================
		@Override
		public List<PhoneNumber> getPhoneNumbers()
		{
			if(mDirty)
			{
				mPhoneNumbers.clear();
				for(Contact contact : mContacts)
				{
					mPhoneNumbers.addAll(contact.getPhoneNumbers());
				}	
				mDirty = false;
			}
			return mPhoneNumbers;
		}
	
		@Override
		public boolean compare(IPhoneComparator inIOpponent)
		{
			boolean result = false;			
			for(Contact contact : mContacts)
			{
				result = contact.compare(inIOpponent);
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
