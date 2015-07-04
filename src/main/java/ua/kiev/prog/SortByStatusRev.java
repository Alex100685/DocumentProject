package ua.kiev.prog;

import java.util.Comparator;

public class SortByStatusRev implements Comparator <Document> {

	@Override
	public int compare(Document o1, Document o2) {
		String s1 = o1.getReceiver().getName();
		String s2 = o2.getReceiver().getName();
		return s2.compareTo(s1);
	}
	

}
