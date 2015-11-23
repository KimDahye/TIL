package phantom;

import java.io.IOException;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.net.Socket;

/**
 * Created by sophie on 2015. 11. 23..
 */
public class SocketPhantomReference extends PhantomReference {
    private Socket socket;

    public SocketPhantomReference(Socket socket, ReferenceQueue queue) {
        super(socket, queue);
        this.socket = socket;
    }

    public void cleanUp() {
        if(socket != null) {
            try {
                System.out.println("socket" + socket + " is closing!");
                socket.close();
                System.out.println("socket closed");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
