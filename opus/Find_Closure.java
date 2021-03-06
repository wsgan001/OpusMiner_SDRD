package opus;

import java.util.Collections;

public class Find_Closure {

	public static void find_closure(final Itemset is, Itemset closure){
		Tidset thistids = new Tidset();
		
		closure.addAll(is);
		
		Utils.gettids(is, thistids);
		
		int item;
		
		for (item = 0; item < Globals.noOfItems; item++){
			if (Globals.tids.get(item).size() >= thistids.size() && !is.contains(item)
					&& Tidset.count_intersection(thistids, Globals.tids.get(item)) == thistids.size()){
				Collections.reverse(closure);
				closure.add(item);
				Collections.reverse(closure);
			}
		}
	}
}
