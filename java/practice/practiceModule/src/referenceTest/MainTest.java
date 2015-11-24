package referenceTest;

import org.junit.Test;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

/**
 * Created by sophie on 2015. 11. 20..
 */
public class MainTest {

    @Test(expected = NullPointerException.class)
    public void testWeakReference() {
//        Integer a = new Integer(3); //잘 됨
//        Node a = new Node(3, null); //잘 됨
//        ArrayList<String> a = new ArrayList<String>();//잘 됨
//        String a = "abc"; //fail 뜸!!! literal로 줄 때엔 optimization이 들어가는가 보다
        String a = new String();

        WeakReference<Object> weakString = new WeakReference<Object>(a);
        a = null;

//        try {
//            byte[] abc = new byte[123456789];
//        } catch(Throwable t) {
//            t.printStackTrace();
//        }

        System.gc();
//        System.runFinalization ();
        if (weakString.get() == null) {
            throw new NullPointerException();
        }
        if (weakString.get().equals("abc")) {
            System.out.println("here");
        }
    }

    @Test(expected = NullPointerException.class)
    public void testWeakHashMap() throws Exception {
        WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<Integer, String>();
        weakHashMap.put(new Integer(1), "hello"); //여기서도 key를 primitive type으로 1을 주면 test fail한다.
        System.gc();

        if (weakHashMap.get(1) == null) {
            throw new NullPointerException();
        }
        if (weakHashMap.get(1).equals("hello")) {
            System.out.println("here");
        }
    }

    //@Test(expected = NullPointerException.class)
    public void testSoftReference() throws Exception {
        SoftReference<Integer> softReference = new SoftReference<Integer>(new Integer(3));
    }
}
