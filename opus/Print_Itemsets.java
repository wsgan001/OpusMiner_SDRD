package opus;

import java.io.PrintStream;
import java.util.Collections;
import java.util.List;

public class Print_Itemsets {
	
	public boolean valgt(ItemsetRec i1, ItemsetRec i2){
		
		return i1.value > i2.value;
	}

	public static void print_itemset(PrintStream f, Itemset is){
		//order is according to attribute index
		Collections.sort(is);
		for (int item_it = 0; item_it != is.size(); item_it++){
			if (item_it != 0){
				//f.print(',');
				f.print(" & ");
			}
			if (Globals.consequentID == is.get(item_it)){
				f.print(String.format("%s", Globals.consequentName));
			}else{
				f.print(String.format("%s", Globals.itemNames.get(is.get(item_it))));
			}
			
		}
	}
	
	public static void print_itemsetRec(PrintStream f, final ItemsetRec is){
		
		print_itemset(f, is);
		
		f.print(String.format(",%d, %f, %f", is.count, is.leverage, is.lift));
		f.print(String.format(",%g", is.p));
		f.print(String.format(",%f,%f\n", is.antSup, is.strength));
		
		if (Globals.printClosures) {
			Itemset closure = new Itemset();
			
			Find_Closure.find_closure(is, closure);
			
			if (closure.size() > is.size()){
				f.print(" closure: ");
				print_itemset(f, closure);
				f.print("\n");
			}
		}
			
	}
	
	public static void print_itemsets(PrintStream f, List<ItemsetRec> is){
		int i;
		
		for (i = 2; i < Globals.alpha.size(); i++){
			f.print(String.format("Alpha for size %d = %g\n", i, Globals.alpha.get(i)));
		}
		
		f.print("\nSELF-SUFFICIENT ITEMSETS:\n");
		
		//print header
		f.print(String.format("%s, %s, %s, %s, %s, %s, %s\n", 
				"Rule", "No. of transactions", "Leverage", "Lift", "P-value", 
				"Antecedent Support", "Strength"));
				
		Collections.sort(is, ItemsetRec.ItemsetRecComparatorD);
		
		int it;
		
		int failed_count = 0;
		
		for (it = 0; it < is.size(); it++){
			if (!is.get(it).selfSufficient){
				failed_count++;
			}else{
				print_itemsetRec(f, is.get(it));
			}
		}
		
		if (failed_count > 0){
			f.print(String.format("\n%d itemsets failed test for self sufficiency\n", failed_count));
		}
		
		for (it = 0; it < is.size(); it++){
			if (!is.get(it).selfSufficient){
				print_itemsetRec(f, is.get(it));
			}
		}
			
	}
	
}
