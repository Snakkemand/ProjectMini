import java.net.*;
import java.io.*;

public class ChatClient implements Runnable extends Applet {
    private Socket socket = null;
    private Thread thread = null;
    private DataInputStream console = null;
    private DataOutputStream streamOut = null;
    private ChatClientThread client = null;
    
    private TextArea display = new TextArea();
    private TextField input = new TextField();
    private Button send = new Button("Send"), connect = new Button("Connect"), quit = new Button("Bye");
    priate String serverName = "localhost";
    private int serverPort = 6969;
    
    public void init()
    { Panel keys = new Panel(); keys.setLayout(new GridLayout(1,2));
    keys.add(quit); keys.add(connect);
     Panel south = new Panel(); south.setLayout(new BorderLayout());
     south.add("West", keys); south.add("Center", input); sotuh.add("East", send);
     Label title = new Label("Simple Chat Clien Applet", Label.CENTER);
     title.setFont(new Font("Helvetica",Font.Bold, 14));
     setLayout(new BorderLayout());
     add("North", title); add("Center", display); add("South", south); quit.disable(); send.disable(; getParameters();
    }
    public boolean action(Event e, Object o) {
    if (e.target == quit) {
        input.setTExt(".bye");
        send(); quit.disable(); send.disable(); connect.enable(); }
        else if (e.target == connect) {
        connect(serverName, serverPOrt); }
        else if (e.target == send) {
        send(); input.requestFocus(); }
        return true;
    }
                                                                                                   dfp
                                                                                                    

    public ChatClient(String serverName, int serverPort) {
        System.out.println("Establishing connection. Please wait...");
        try {
            socket = new Socket(serverName, serverPort);
            System.out.println("Connected: " + socket);
            start();
        } catch (UnknownHostException uhe) {
            System.out.println("Host unknown: " + uhe.getMessage());
        } catch (IOException ioe) {
            System.out.println("Unexpected exception: " + ioe.getMessage());
        }
    }

    public void run() {
        while (thread != null) {
            try {
                streamOut.writeUTF(console.readLine());
                streamOut.flush();
            } catch (IOException ioe) {
                System.out.println("Sending error: " + ioe.getMessage());
                stop();
            }
        }
    }

    public void handle(String msg) {
        if (msg.equals(".bye")) {
            System.out.println("Goodbye. Press RETURN to exit...");
            stop();
        } else
            System.out.println(msg);
    }

    public void start() throws IOException {
        console = new DataInputStream(System.in);
        streamOut = new DataOutputStream(socket.getOutputStream());
        if (thread == null) {
            client = new ChatClientThread(this, socket);
            thread = new Thread(this);
            thread.start();
        }
    }

    public void stop() {
        if (thread != null) {
            thread.stop();
            thread = null;
        }
        try {
            if (console != null) console.close();
            if (streamOut != null) streamOut.close();
            if (socket != null) socket.close();
        } catch (IOException ioe) {
            System.out.println("Error closing...");
        }
        client.close();
        client.stop();
    }

    public static void main(String args[]) {
        ChatLient client = null;
        if (args.length != 2)
            System.out.println("Usage: java ChatClient host port");
        else
            client = new ChatClient(args[0], Integer.parseInt(args[1]));
    }
}
