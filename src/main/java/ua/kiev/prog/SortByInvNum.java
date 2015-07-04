package ua.kiev.prog;

import java.util.Comparator;

public class SortByInvNum implements Comparator <Document> {

	@Override
	public int compare(Document o1, Document o2) {
		String s1 = o1.getInventaryNumber();
		String s2 = o2.getInventaryNumber();
		return s1.compareTo(s2);
	}
	

}

