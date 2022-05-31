import java.net.ServerSocket;
import java.net.Socket;

public class DBServer {
    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(8800);

            while(true){
                System.out.println("Waiting clients");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected");

                DBServerThread dbServerThread = new DBServerThread(socket);
                dbServerThread.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
