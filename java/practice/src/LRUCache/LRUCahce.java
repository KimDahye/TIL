package LRUCache;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by sophie on 2015. 11. 26..
 */
public class LRUCahce<K, V> {
    private Map<K, SoftReference<V>> cache = new HashMap<K, SoftReference<V>>();
//    private ReferenceQueue<Object> queue = new ReferenceQueue<Object>(); // ReferenceQueue<V>로 해도 될 것 같은데 일단 weakHashMap 따라함. parameterized type을 많이 쓰면 성능이 나빠지나?

    public V get(K key) {
        SoftReference<V> ref = cache.get(key);
        if(ref == null){
            //해당 키에 대해 value를 넣은 적이 없거나.
            // soft reference가 GC 되었거나
            if(cache.containsKey(key)){
                cache.remove(key);
            }
            return null;
        }

        V value = ref.get();
        if(value == null){
            // referent 가 finilize 되었을 때.
            cache.remove(key);
            System.out.println("cache cleared.");
        }

        System.out.println("cache hit!");
        return value;
    }

    public void put(K key, V value) {
        cache.put(key, new SoftReference(value));
    }
}
