import java.net.*;
import java.io.*;

public class ChatClientThread extends Thread{
    private Socket socket = null;
    Private chatClient client = null;
    private DataInputStream streamIn = null;

    Public ChatClientThread(ChatClient _client, _Socket socket) {
        client = _client;
        socket = _socket;
        open();
        start();
    }

    public void open() {
        try {
            streamIn = new DataInputStream(socket.getInputStream());
        }
        catch(IOExeption ioe) {
            System.out.println("Error getting input: " + ioe );
            client.stop();
        }
    }
    public void close() {
        try {
            if (stramIn != null) streamIn.Close();
        }
        catch(IOExeption ioe)
        { system.out.println("Error closing input stream: " + ioe);
        }
    }
    public void run() {
        while (true) {
            try {
                client.handle(streamIn.readUTF());
            }
            catch(IOExeption ioe) {
                system.out.println("listening error:" + ioe.getMessage());
            }
        }
    }
}
