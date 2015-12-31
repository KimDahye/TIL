package reasonableThreadPoolExecutor;

import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * Created by sophie on 2015. 12. 31..
 */
public class ThreadPoolExecutorTest {
    @Test
    public void testExecuteWithUnboundedQueue() throws Exception {
        // 기존 ThreadPoolExecutor에서는 LinkedBlockingQueue를 사용하면 unbounded queue이기 때문에 core개수만 실행된다.
        // 바꾼 거에서 어떻게 동작하는지 보자.
        // max 개수만큼 바로 실행되고, 나머지는 큐에 넣어져 나중에 실행될 것이다.
        // 큐가 unbounded이므로 reject되는 것은 없음.
        // 그렇게 실행됨.

        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
        ThreadPoolExecutor t = new ThreadPoolExecutor(3, 5, 30, TimeUnit.SECONDS, queue, rejectionHandler);

        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            t.execute(worker);
        }

        t.shutdown();
        while (!t.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }

    @Test
    public void testExecuteWithBoundedQueue() throws Exception {
        // 2개 reject 될 거 예상!
        // 예상대로 돌아감!

        RejectedExecutionHandlerImpl rejectionHandler = new RejectedExecutionHandlerImpl();
        BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(3);
        ThreadPoolExecutor t = new ThreadPoolExecutor(3, 5, 30, TimeUnit.SECONDS, queue, rejectionHandler);

        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            t.execute(worker);
        }

        t.shutdown();
        while (!t.isTerminated()) {
        }
        System.out.println("Finished all threads");
    }
}