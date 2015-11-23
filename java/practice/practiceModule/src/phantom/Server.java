package phantom;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by sophie on 2015. 11. 23..
 */
public class Server {
    public static void main(String args[]) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);
        while(true) {
            Socket acceptedSocket = serverSocket.accept();
        }
    }
}
