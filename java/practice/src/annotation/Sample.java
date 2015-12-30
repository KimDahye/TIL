package annotation;

/**
 * Created by sophie on 2015. 12. 29..
 */
public class Sample {
    public static void m1() {  // Test should pass
        int i = 0;
        i = i / i;
    }

    //@ExceptionTest(exception = ArithmeticException.class)
    public static void m2() {  // Should fail (wrong exception)
        int[] a = new int[0];
        int i = a[1];
    }

    //@ExceptionTest(1)
    public static void m3() {
    }  // Should fail (no exception)

    static int sum(Integer... args) {
        int sum = 0;
        System.out.println("hi: "+args);
//        for (int arg : args)
//            sum += arg;
        return sum;
    }
}
