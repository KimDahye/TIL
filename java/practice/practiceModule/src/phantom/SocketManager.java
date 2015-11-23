package phantom;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.net.Socket;
import java.util.LinkedList;

/**
 * Created by sophie on 2015. 11. 23..
 */
public class SocketManager {
    private LinkedList<PhantomReference> phantomReferences = new LinkedList<PhantomReference>();
    private ReferenceQueue queue = new ReferenceQueue();
    private Thread referenceThread = new Thread() {
        @Override
        public void run() {
            while(true) {
                try {
                    SocketPhantomReference ref = (SocketPhantomReference) queue.remove();
                    ref.cleanUp(); //예제에서는 ref.close() 였다.
                    phantomReferences.remove(ref); // 이후에야 비로소 phantom reference가 참조하고 있던 referent가 GC 된다.
                } catch (InterruptedException e) {
                    //continue to while loop
                }
            }
        }
    };

    public void addSocket(String serverName, int serverPort){
        Socket newSocket = null;
        try {
            newSocket = new Socket(serverName, serverPort);
        } catch (IOException e) {
            e.printStackTrace();
        }
        phantomReferences.add(new PhantomReference(newSocket, queue));
    }

    public void cleanUpReferences() {
        referenceThread.setDaemon(true); //TODO. Daeomon 쓰레드 이해하기
        referenceThread.start();
    }
}
