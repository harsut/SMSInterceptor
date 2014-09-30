package com.irbis.smsinterceptor.data.comparator;

import java.util.List;

public interface IPhoneComparator
{
	List<PhoneNumber> getPhoneNumbers();
	boolean compare(IPhoneComparator inIComparator);
}
