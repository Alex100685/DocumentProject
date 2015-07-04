package ua.kiev.prog;

import java.util.Comparator;

public class SortByQuantityRev implements Comparator <Document> {

	@Override
	public int compare(Document o1, Document o2) {
		int s1 = o1.getQuantity();
		int s2 = o2.getQuantity();
		
		if(s1>s2){
			return -1;
		}
		if(s1<s2){
			return 1;
		}
		else{
			return 0;
		}
	}
	

}
