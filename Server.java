import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server{
    public static void main(String[] args) throws IOException{
        ServerSocket serversocket = new ServerSocket(8080);

        try{
            System.out.println("Server is waiting for the client to connect");
            
            while(true){
                Socket socket = serversocket.accept();
                System.out.println("Client Connected "+socket);

                DataInputStream dis = new  DataInputStream(socket.getInputStream());
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

                //receive age from client
                int age = dis.readUTF();

                string message;
                if(age>=18){
                    message = "you are eligible to vote";
                }
                else{
                    message = "you are not eligible to vote";
                }
                
                //send response
                dos.writeUTF(message);

                socket.close();
                System.out.println("Client disconnected");
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            serversocket.close();
        }
    }
}