package ua.kiev.prog;

import java.util.Comparator;

public class SortByDocTypeRev implements Comparator <Document> {

	@Override
	public int compare(Document o1, Document o2) {
		String s1 = o1.getDocType();
		String s2 = o2.getDocType();
		return s2.compareTo(s1);
	}
	

}
