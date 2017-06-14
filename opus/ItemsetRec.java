package opus;

import java.util.Comparator;

public class ItemsetRec extends Itemset{

	public static final long serialVersionUID = 1L;
	public int count;
	public float value;
	public double p;
	public boolean selfSufficient;
	
	public ItemsetRec(int count, float value, double p, boolean selfSufficient){
		this.count = count;
		this.value = value;
		this.p = p;
		this.selfSufficient = selfSufficient;
	}
	
	public ItemsetRec(){
		//By default
		this.count = 0;
		this.value = (float)0.0;
		this.p = 1.0;
		this.selfSufficient = true;
	}
	//TODO The less the value, the better?	
	public boolean lessThan(ItemsetRec pI){
		return this.value > pI.value;
	}
	
	
	public static Comparator<ItemsetRec> ItemsetRecComparator = new Comparator<ItemsetRec>(){

		@Override
		public int compare(ItemsetRec o1, ItemsetRec o2) {
			// TODO Auto-generated method stub
			if (o1.value < o2.value)
				return -1;
			if (o1.value > o2.value)
				return 1;
			return 0;
		}
	};
	
	public static Comparator<ItemsetRec> ItemsetRecSizeComparator = new Comparator<ItemsetRec>(){
		@Override
		public int compare(ItemsetRec o1, ItemsetRec o2) {
			// TODO Auto-generated method stub
			if (o1.size() < o2.size())
				return -1;
			if (o1.size() > o2.size())
				return 1;
			return 0;
		}
	};
}