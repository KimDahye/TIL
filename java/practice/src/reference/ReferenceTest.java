package reference;

import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

import static org.junit.Assert.*;

/**
 * Created by sophie on 2015. 11. 26..
 */
public class ReferenceTest {

    @Test
    public void testWeakReference() throws InterruptedException {
//        Integer a = new Integer(3); //잘 됨
//        Node a = new Node(3, null); //잘 됨
//        ArrayList<String> a = new ArrayList<String>();//잘 됨
//        String a = "abc"; //fail 뜸!!! literal로 줄 때엔 optimization이 들어가는가 보다
        String a = new String("abc");
        ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        WeakReference<Object> weakString = new WeakReference<Object>(a, queue);
        System.out.println("before gc: " + weakString);
        a = null;

        System.gc();
        if (weakString.get() != null) {
            fail();
        }

        Reference<Object> reference = (Reference<Object>) queue.remove();//poll()을 하려면 객체가 나올 때까지 루프 돌아야 한다.

        if (reference != null) {
            System.out.println("after gc: " + reference);
            System.out.println("referent: " + reference.get()); // null이 나온다.
            //이렇게 수동적으로 poll()해주지 않는이상 reference객체는 계속 queue에 남아 있는 걸까? 아니면 언젠가 GC에 의해 정리되는 걸까?
        }
    }

    @Test
    public void testWeakReference2() throws InterruptedException {
        String a = new String("abc");
        ReferenceQueue<Object> queue = new ReferenceQueue<Object>();
        WeakReference<Object> weakString = new WeakReference<Object>(a, queue);
        System.out.println("before gc: " + weakString);
        a = null;

        if (weakString.get() != null) {
            fail();
        }

        Reference<Object> reference = (Reference<Object>) queue.remove();

        if (reference != null) {
            System.out.println("after gc: " + reference);
            System.out.println("referent: " + reference.get()); // null이 나온다.
        }
    }
}