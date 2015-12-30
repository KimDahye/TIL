package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * Created by sophie on 2015. 12. 29..
 */
// Program to process marker annotations
public class RunTests {
    public static void main(String[] args) throws Exception {
        Integer[] arr = new Integer[2];
        int [] arr2 = new int[2];
        for(int i = 0; i <arr.length; i++) {
            arr[i] = i;
            arr2[i] = i;
        }
        System.out.println(Arrays.asList(arr));
        System.out.println(Arrays.asList(arr2));

        Sample.sum(null);

        int tests = 0;
        int passed = 0;
        Class testClass = Class.forName("annotation.Sample");
        for (Method m : testClass.getDeclaredMethods()) {

            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                ExceptionTest a = m.getAnnotation(ExceptionTest.class);

                try {
                    System.out.println("value= " + a.value2());
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (Throwable wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    //Class<? extends Exception> excType = a.exception();
//                    if (excType.isInstance(exc)) {
//                        passed++;
//                    } else {
//                        System.out.printf("Test %s failed: expected %s, got %s%n", m, excType.getName(), exc);
//                    }
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
    }
}