package simpleLRU;

/**
 * Created by sophie on 2015. 11. 27..
 */
public class DoublyNode<K, V> {

    private K key;
    private V value;
    private DoublyNode pre;
    private DoublyNode next;

    public DoublyNode(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public DoublyNode getNext() {
        return next;
    }

    public DoublyNode getPre() {
        return pre;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public void setPre(DoublyNode pre) {
        this.pre = pre;
    }

    public void setNext(DoublyNode next) {
        this.next = next;
    }
}
