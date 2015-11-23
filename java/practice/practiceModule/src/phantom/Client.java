package phantom;

/**
 * Created by sophie on 2015. 11. 23..
 */
public class Client {

    public static void main(String args[]){
        SocketManager socketManager = new SocketManager();
        socketManager.addSocket("127.0.0.1", 9000);
        socketManager.cleanUpReferences();
    }
}
