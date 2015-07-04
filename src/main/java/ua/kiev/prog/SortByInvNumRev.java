package ua.kiev.prog;

import java.util.Comparator;

public class SortByInvNumRev implements Comparator <Document> {

	@Override
	public int compare(Document o1, Document o2) {
		String s1 = o1.getInventaryNumber();
		String s2 = o2.getInventaryNumber();
		return s2.compareTo(s1);
	}
	

}

