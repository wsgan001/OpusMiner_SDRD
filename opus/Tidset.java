package opus;

import java.util.ArrayList;
import java.util.HashSet;

public class Tidset extends ArrayList<Long>{

	private static final long serialVersionUID = 1L;

	public void add(long TID){
		if (!this.contains(TID))
			super.add(TID);
	}
	
	public void addAll(Tidset ts){
		
		HashSet<Long> hs = new HashSet<Long>(this);
		
		hs.addAll(ts);
		
		this.clear();
		
		this.addAll(hs);
	}
	
	// count the size of the intersection
	// relies on the sets both being stored in ascending order
	public static int count_intersection(Tidset s1, Tidset s2){
		
		if (s1.size() == 0 || s2.size() == 0){
			return 0;
		}
		int count = 0;
		
		for (Long i : s1){
			if (s2.contains(i)){
				count++;
			}
		}
		return count;
	}
	
	// find the intersection of two tidsets
	// relies on the sets both being stored in ascending order
	public static void intersection(Tidset result, Tidset s1, Tidset s2){
//		result.clear();
//		result.ensureCapacity(Math.min(s1.size(), s2.size()));
//
//		if (s1.size() == 0 || s2.size() == 0){
//			return;
//		}
//		int it1 = 0;
//		long v1 = s1.get(it1);
//		int end1 = s1.size();
//		int it2 = 0;
//		long v2 = s2.get(it2);
//		int end2 = s2.size();
//		while (true){
//
//			if (v1 == v2){
//				result.add(v1);
//				it1++;
//				if (it1 == end1) break;
//				v1 = s1.get(it1);
//				it2++;
//				if (it2 == end2) break;
//				v2 = s2.get(it2);
//			}else if (v1 < v2){
//				it1++;
//				if (it1 == end1) break;
//				v1 = s1.get(it1);
//			}else{
//				it2++;
//				if (it2 == end2) break;
//				v2 = s2.get(it2);
//			}
//		}
		result.clear();
		result.ensureCapacity(Math.min(s1.size(), s2.size()));
		
		if (s1.size() == 0 || s2.size() == 0){
			return;
		}
		
		for (Long i : s1){
			if (s2.contains(i)){
				result.add(i);
			}
		}
		
	}
	
	// destructively update s1 to its intersection with s2
	public static void dintersection (Tidset s1, Tidset s2){
		if (s1.size() == 0){
			return;
		}
		
		if (s2.size() == 0){
			s1.clear();
			return;
		}
		
		Tidset tmp = new Tidset();
		
		for (Long i:s1){
			if (s2.contains(i)){
				tmp.add(i);
			}
		}
		
		s1.clear();
		s1.addAll(tmp);
		
		
	}
	
	// destructively update s1 to its union with s2
	public static void dunion(Tidset s1, Tidset s2){
		
		HashSet<Long> tmp = new HashSet<Long>();
		
		tmp.addAll(s1);
		tmp.addAll(s2);
		
		s1.clear();
		s1.addAll(tmp);
	}
	
}
