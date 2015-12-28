package generic;

/**
 * Created by sophie on 2015. 12. 7..
 */
public class Main {
    private static UnaryFunction<Object> IDENTITY_FUNCTION = new UnaryFunction<Object>() {
        @Override
        public Object apply(Object arg) {
            return arg;
        }
    };


    public static <T> UnaryFunction<T> identityFunction() {
        return (UnaryFunction<T>) IDENTITY_FUNCTION;
    }

    public static void main (String[] args) {
        String[] strings = {"1", "b", "c"};
        UnaryFunction<String> sameString = identityFunction();
        for(String s : strings) System.out.println(sameString.apply(s));
    }

    private interface UnaryFunction<T> {
        T apply (T arg);
    }

}
