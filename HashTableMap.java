import java.util.LinkedList;
import java.util.NoSuchElementException;
//do i need to implement a 'pair' class that can then be stored in the array?


public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {
	private int capacity;
	private int size;
	private Object[] array;
	
	public HashTableMap() {
		this.capacity = 10;
		this.size = 0;
		this.array = new Object[this.capacity];
	}
	
	public HashTableMap(int capacity) {
		this.capacity = capacity;
		this.size = 0;
		this.array = new Object[capacity];
	}
	
	public int capacity() {
		return this.capacity;
	}
	
	//grow by doubling capacity and rehashing, at capacity >= 85% - private helper methods
	//store new values at index corresponding to absolute value of key's hash code, modulus
	//hash table's current capacity
	//when put method is passed a key that is null or already in your hash table, call
	//returns false without making changes to hash table. only returns true after successfully
	//storing new key-value pair
	//use LinkedList chaining to handle collision
	public boolean put(KeyType key, ValueType value) {
	
		//returns false if key is null
		if (key == null) {return false;}
		
		//query hash table to see if key is already contained
		Pair<KeyType, ValueType> checkResult = checkAddRemove(key, null, false, false);
		//if a Pair found with the same key as parameter, return false
		if (checkResult != null) {
			return false;
		}
		
		//if tests passed, store current size, call checkAddRemove to add hashPair at correct place
		int temp = this.size;
		checkAddRemove(key, value, true, false);
		
		//if hash table at more than 85% capacity, double size and rehash
		Double threshold = 85.0;
		Double percentFull = (double)this.size/(double)this.capacity*100;
		System.out.println(percentFull);
		if (percentFull - threshold >= 0){
			doubleSizeRehash();
		}
		//if reached end of method and size is 1 greater than it was, put has been successful, return true
		if (this.size == temp+1) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * this private helper method doubles the capacity of the storage array,
	 * and rehashes all values, including out of LinkedLists
	 */
	private void doubleSizeRehash() {
		//store current array as temp
		Object[] temp = this.array;
		//compute new capacity, create new array with capacity, set this.capacity = new capacity and size = 0
		//(put will increment size)
		int newCapacity = 2*this.capacity;
		this.array = new Object[newCapacity];
		this.capacity = newCapacity;
		this.size = 0;
		//loop over temp and put each element in this.array
		for (Object obj : temp) {
			if (obj == null) {
			} else {
				@SuppressWarnings("unchecked")
				LinkedList<Pair<KeyType, ValueType>> foundList = ((LinkedList<Pair<KeyType, ValueType>>) obj);
				for (Pair<KeyType, ValueType> p: foundList) {
					this.put(p.getKey(), p.getVal());
				}
			}
		}
	}
	
	/**
	 * getter method
	 * @param KeyType key
	 * @return ValueType value associated with that unique identifying key
	 * @throws NoSuchELementException if hash table size is 0, or if key not found
	 */
	public ValueType get(KeyType key) throws NoSuchElementException {
		//if key parameter is null, throw NoSuchElementException
		if (key == null) {throw new NoSuchElementException("cannot get null");}
		//check for existence of key in hash table
		//checkResult will either be of class Pair, or else null
		Object checkResult = checkAddRemove(key, null, false, false);
		//if Pair, returns associated value
		if(checkResult instanceof Pair) {
			@SuppressWarnings("unchecked")
			Pair<KeyType, ValueType> foundPair = ((Pair<KeyType, ValueType>)checkResult);
			return foundPair.getVal();
		}
		//if null, throw NoSuchElementException
		if (checkResult == null) {
			throw new NoSuchElementException("key not found in hash table");
		}
		return null;
	}
	
	/**
	 * checker method
	 * @param key to check for
	 * @return true if key is contained in hash table, false otherwise
	 */
	public boolean containsKey(KeyType key) {
		Object checkResult = checkAddRemove(key, null, false, false);
		if(checkResult instanceof Pair) {
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * remove method
	 * @param key of pair to remove
	 * @return value associated with key if found, null otherwise
	 */
	public ValueType remove(KeyType key) {
		Object removeResult = checkAddRemove(key, null, false, true);
		if (removeResult instanceof Pair) {
			@SuppressWarnings("unchecked")
			Pair<KeyType, ValueType> removedPair = ((Pair<KeyType, ValueType>) removeResult);
			return removedPair.getVal();
		} else {
			return null;
		}
	}
	
	/**
	 * size getter method
	 * @return current size of hash table - counts every element, even if nested in linked list
	 */
	public int size() {
		return this.size;
	}
	
	/**
	 * public clear method removes all key values pairs (lets Java garbage collection delete them)
	 */
	public void clear() {
		int cap = capacity;
		array = new Object[cap];
		this.size = 0;
	}
	
	@Override
	public String toString() {
		String toReturn = "\nString representation of HashTable with capacity: "+this.capacity +" and size: " +this.size + "\n";
		for (Object o : array) {
			if (o == null) {toReturn = toReturn + "\tnull\n";}
			//if not null, then it's a linked list
			else { 
				for (Pair<KeyType, ValueType> p : ((LinkedList<Pair<KeyType, ValueType>>) o)) {
					toReturn = toReturn + "\t" + p.toString() + "     ";
				}
				toReturn = toReturn + "\n";
			}
		}
		return toReturn;
	}
	
	/**
	 * private helper method that converts a key into a hash index based on current capacity
	 * @param key
	 * @return
	 */
	private int hashIndex(KeyType key) {
		int hashVal = key.hashCode();
		int hashIndex = hashVal % this.capacity;
		if (hashIndex < 0) {
			hashIndex = -1*hashIndex;
		}
		return hashIndex;
	}
	
	/**
	 * private helper method that checks at a given hashIndex to see if there is a Pair with the associated key
	 * present. if yes, returns that Pair, potentially removing it. if no, either returns null or adds that Pair
	 * and returns it.
	 * I know that in java, a method is supposed to be single purpose, but I couldn't bring myself to re-write certain
	 * chunks of code the number of times that would have taken, and this struck me as a more elegant solution,
	 * coming from R programming where multi-use functions are the norm.
	 * @param key - a key of KeyType
	 * @return Pair<KeyType, ValueType> if one found with key matching parameter, or one added, null otherwise
	 */
	private Pair<KeyType, ValueType> checkAddRemove(KeyType key, ValueType value, boolean add, boolean remove) {
		//ensure method call is not using invalid combination of parameters
		if (add & remove || (add & (value==null))) {
			throw new NoSuchElementException("invalid parameters passed to checkAddRemove");
		}
		//compute hashIndex from key
		int hashIndex = hashIndex(key);
		
		//TODO debug print
		//System.out.println("hashIndex: "+hashIndex);
		
		//compose Pair out of key and value to use later in several places
		Pair<KeyType, ValueType> hashPair = new Pair<KeyType, ValueType>(key, value);
		
		//at array[hashIndex] there will either be null or a LinkedList,
		//based on implementation below
		
		//if null found at array[hashIndex], 3 possible cases (2 are the same outcome)
		//if just looking - return null
		//if removing - return null
		//if adding, then create a linked list, add hashPair, store it at array[hashIndex], increment size, 
		//and return added hashPair
		if (this.array[hashIndex] == null) {
			if (add) {
				LinkedList<Pair<KeyType, ValueType>> linked = new LinkedList<Pair<KeyType, ValueType>>();
				linked.add(hashPair); 
				this.array[hashIndex] = linked;
				size++;
				//System.out.println("hash index:" + hashIndex);
				return hashPair;
			} else {return null;}
		}
		
		//if LinkedList found at array[hashIndex], 3 possible cases, each different outcome:
		//if adding - add hashPair to beginning of found linked list, increment size, return hashPair
		//if just looking - loop over LinkedList and check each, when found return the Pair found, else return null
		//if removing - while looping over linked list, if found, remove the pair, decrement size, return found pair 
		//else return null
		else if (this.array[hashIndex] instanceof LinkedList) {
			@SuppressWarnings("unchecked")
			LinkedList<Pair<KeyType, ValueType>> foundLinkedList = ((LinkedList<Pair<KeyType, ValueType>>) this.array[hashIndex]);
			boolean found = false;
			if (add) {
				foundLinkedList.addFirst(hashPair); size++;
				//System.out.println("hash index:" + hashIndex);
				return hashPair;
				}
			for (int i=0; i<foundLinkedList.size(); i++) {
				Pair<KeyType, ValueType> foundPair = foundLinkedList.get(i);
				if (foundPair.getKey().equals(key)) {
					found = true;
					if (found & remove) {
						this.size--;
						return foundLinkedList.remove(i);
					} else if (found & !remove) {
						return foundPair;
					}
				}
			}
			return null;
		}
		return null;
	}
	
	
}

