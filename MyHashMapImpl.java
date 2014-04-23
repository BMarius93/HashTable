import java.util.ArrayList;
import java.util.List;


public class MyHashMapImpl<K, V> implements MyHashMap<K, V>{
	
	ArrayList<BucketImpl> buckets = new ArrayList<BucketImpl>();

	public MyHashMapImpl(int nUM_BUCKETS) {
		// TODO Auto-generated constructor stub
		this.buckets = new ArrayList<BucketImpl>();
		for(int i = 0 ; i < nUM_BUCKETS; i++){
			BucketImpl element = new BucketImpl();
			buckets.add(i, element);
		}
		
		
		
	}

	@Override
	public V get(K key) {
		// TODO Auto-generated method stub
		
		int bucketIndex = Math.abs(key.hashCode()) % buckets.size();
		
		
		
			ArrayList<EntryImpl> entries = buckets.get(bucketIndex).entries;
			for (EntryImpl entr : entries){
				if(entr.key.equals(key)){
					return entr.value;
				}
			}
			return null;
		
	}

	@Override
	public V put(K key, V value) {
		
		EntryImpl e = new EntryImpl(key, value);
		if(buckets.size() == 0){
			return null;
		}
		int bucketIndex = Math.abs(key.hashCode()) % buckets.size();
		ArrayList<EntryImpl> entries = buckets.get(bucketIndex).entries;
		for (EntryImpl entr : entries){
			if(entr.key.equals(key)){
				entries.remove(entr);
				entries.add(e);
				return entr.value;
			}
		}
		
		entries.add(e);
		
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V remove(K key) {
		if(buckets.size() == 0){
			return null;
		}
		int bucketIndex = Math.abs(key.hashCode()) % buckets.size();
		ArrayList<EntryImpl> entries = buckets.get(bucketIndex).entries;
		for (EntryImpl entr : entries){
			if(entr.key.equals(key)){
				
				entries.remove(entr);
				return entr.value;
				
			}
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int size = 0;
		for(BucketImpl bucket : buckets){
			size += bucket.entries.size();
		}
		return size;
	}

	@Override
	public List<? extends MyHashMap.Bucket<K, V>> getBuckets() {
		// TODO Auto-generated method stub
		return this.buckets;
	}
	
	public class EntryImpl implements Entry<K, V>{

		public EntryImpl(K key, V value){
			
			this.key = key;
			this.value = value;
		
		}
		
		public K key;
		public V value;
		
		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}
		
	}
	
	public class BucketImpl implements Bucket<K, V>{
		
		ArrayList<EntryImpl> entries = new ArrayList<EntryImpl>();
		
		@Override
		public List<? extends MyHashMap.Entry<K, V>> getEntries() {
			
			// TODO Auto-generated method stub
			return this.entries;
		}
		
	}

}
