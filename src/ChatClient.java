import java.net.*;
import java.io.*;
import java.applet.*;
import java.awt.*;

public class ChatClient extends Applet
{
    private Socket socket               = null;
    private DataInputStream console     = null;
    private DataOutputStream streamOut  = null;
    private ChatClientThread client     = null;
    private TextArea display = new TextArea();
    private TextField input  = new TextField();
    private Button send      = new Button("Send"),  connect = new Button("Connect"),
                   quit      = new Button("Bye");
    private String  serverName = "localhost";
    private int     serverPort = 6969;

    public void init()
    {



    }
}
