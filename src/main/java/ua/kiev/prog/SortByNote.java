package ua.kiev.prog;

import java.util.Comparator;

public class SortByNote implements Comparator <Document> {

	@Override
	public int compare(Document o1, Document o2) {
		String s1 = o1.getNote();
		String s2 = o2.getNote();
		return s1.compareTo(s2);
	}
	

}
