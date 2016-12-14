package amery.memoryleak;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class WeakHashMap<K,V> implements Map<K,V> {

	 private void expungeStaleEntries() {
			/*Entry<K,V> e;
		        while ( (e = (Entry<K,V>) queue.poll()) != null) {
		            int hash = e.hash;

		            Entry<K,V> prev = getChain(hash);
		            Entry<K,V> cur = prev;
		            while (cur != null) {
		                Entry<K,V> next = cur.next;
		                if (cur == e) {
		                    if (prev == e)
		                        setChain(hash, next);
		                    else
		                        prev.next = next;
		                    break;
		                }
		                prev = cur;
		                cur = next;
		            }
		        }*/
		    }
	
/*    private static class Entry<K,V> extends WeakReference<K> 
      implements Map.Entry<K,V> {
        private V value;
        private final int hash;
        private Entry<K,V> next;
    }*/

    public V get(Object key) {
   /*     int hash = getHash(key);
        Entry<K,V> e = getChain(hash);
        while (e != null) {
            K eKey= e.get();
            if (e.hash == hash && (key == eKey || key.equals(eKey)))
                return e.value;
            e = e.next;
        }*/
        return null;
    }

	public void clear() {
		// TODO Auto-generated method stub
		
	}

	public boolean containsKey(Object key) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean containsValue(Object value) {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	public Set<K> keySet() {
		// TODO Auto-generated method stub
		return null;
	}

	public V put(K key, V value) {
		// TODO Auto-generated method stub
		return null;
	}

	public void putAll(Map<? extends K, ? extends V> m) {
		// TODO Auto-generated method stub
		
	}

	public V remove(Object key) {
		// TODO Auto-generated method stub
		return null;
	}

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	public Collection<V> values() {
		// TODO Auto-generated method stub
		return null;
	}
}