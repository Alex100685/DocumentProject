package ua.kiev.prog;

import java.util.Comparator;
import java.util.Date;

public class SortByPublishDate implements Comparator <Document> {

	@Override
	public int compare(Document o1, Document o2) {
		String s1 = o1.getPublishDate();
		String s2 = o2.getPublishDate();
		return s1.compareTo(s2);
	}
	

}
