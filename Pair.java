/**
 * 
 * @author kal
 * this class facilitates combined storage of a KeyType object with a ValueType object for convenience
 * in HashTableMap
 * @param <KeyType>
 * @param <ValueType>
 */
public class Pair<KeyType, ValueType> {
	private KeyType key;
	private ValueType val;
	
	public Pair() {
		this.key = null;
		this.val = null;
	}
	
	public Pair(KeyType key, ValueType value) {
		this.key = key;
		this.val = value;
	}
	
	public KeyType getKey() {
		return this.key;
	}
	
	public ValueType getVal() {
		return this.val;
	}
	
	public boolean equals(Pair<KeyType, ValueType> pairTwo) {
		boolean toReturn = false;
		
		if (pairTwo.getKey().equals(this.key) && pairTwo.getVal().equals(this.val)) {
			toReturn = true;
		}
		
		return toReturn;
	}
	
	public boolean compareKey(Pair<KeyType, ValueType> pairTwo) {
		if (pairTwo.getKey().equals(this.key)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public String toString() {
		String toReturn = "key: " + key.toString() + " val: " + val.toString();
		return toReturn;
	}
}
