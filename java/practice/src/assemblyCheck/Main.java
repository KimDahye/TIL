package assemblyCheck;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by sophie on 2016. 1. 7..
 */
public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        //일반 for문
        for(int i = 0, n=list.size(); i<n; i++){
            int a = list.get(i) + 1;
        }

        //iterator 사용한 for문
        for(Iterator i = list.iterator(); i.hasNext(); ) {
            int a = (Integer) i.next();
            a++;
        }

        //for-each
        for(Integer e : list){
            int a = e + 1;
        }
    }
}
