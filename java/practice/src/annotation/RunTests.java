package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;

/**
 * Created by sophie on 2015. 12. 29..
 */
// Program to process marker annotations
public class RunTests {
    public static void main(String[] args) throws Exception {
        int tests = 0;
        int passed = 0;
        Class testClass = Class.forName("annotation.Sample");
        for (Method m : testClass.getDeclaredMethods()) {

            if (m.isAnnotationPresent(ExceptionTest.class)) {
                tests++;
                ExceptionTest a = m.getAnnotation(ExceptionTest.class);

                try {
                    System.out.println("value= " + a.value());
                    m.invoke(null);
                    System.out.printf("Test %s failed: no exception%n", m);
                } catch (Throwable wrappedExc) {
                    Throwable exc = wrappedExc.getCause();
                    Class<? extends Exception> excType = a.exception();
                    if (excType.isInstance(exc)) {
                        passed++;
                    } else {
                        System.out.printf("Test %s failed: expected %s, got %s%n", m, excType.getName(), exc);
                    }
                }
            }
        }
        System.out.printf("Passed: %d, Failed: %d%n", passed, tests - passed);
    }
}