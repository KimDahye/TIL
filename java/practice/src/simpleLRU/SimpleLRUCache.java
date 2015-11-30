package simpleLRU;

import java.util.HashMap;

/**
 * Created by sophie on 2015. 11. 27..
 */
public class SimpleLRUCache<K, V> {
    int capacity;
    HashMap<K, DoublyNode<K, V>> map = new HashMap<K, DoublyNode<K, V>>();
    DoublyNode head = null;
    DoublyNode end = null;

    public SimpleLRUCache(int capacity) {
        this.capacity = capacity;
    }

    public V get(int key) {
        if (map.containsKey(key)) {
            DoublyNode n = map.get(key);
            remove(n);
            setHead(n);
            return (V) n.getValue();
        }

        return null;
    }

    public void set(K key, V value) {
        if (map.containsKey(key)) {
            DoublyNode old = map.get(key);
            old.setValue(value);
            remove(old);
            setHead(old);
            return;
        }

        DoublyNode created = new DoublyNode(key, value);
        if (map.size() >= capacity) {
            map.remove(end.getKey()); //이걸 위해 DoublyNode 에 key가 필요함. key가 중복으로 저장되는 이슈가 생김. 여기서 LRU element가 교체되는 전략 실현.
            remove(end);
            setHead(created);
        } else {
            setHead(created);
        }
        map.put(key, created);
    }

    private void remove(DoublyNode n) {
        if (n.getPre() != null) {
            n.getPre().setNext(n.getNext());
        }else {
            head = n.getNext();
        }
        if(n.getNext() != null) {
            n.getNext().setPre(n.getPre());
        } else {
            end = n.getPre();
        }
    }

    private void setHead(DoublyNode n) {
        n.setNext(head);
        n.setPre(null);

        if(head != null) {
            head.setPre(n);
        }

        head = n;

        if(end == null) {
            end = n;
        }
    }
}
