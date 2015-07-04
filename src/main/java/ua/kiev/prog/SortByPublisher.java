package ua.kiev.prog;

import java.util.Comparator;

public class SortByPublisher implements Comparator <Document> {

	@Override
	public int compare(Document o1, Document o2) {
		String s1 = o1.getPublisher().getName();
		String s2 = o2.getPublisher().getName();
		return s1.compareTo(s2);
	}
	

}
