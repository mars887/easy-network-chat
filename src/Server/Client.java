package Server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

class Client extends Thread {
    Socket socket;
    PrintStream out;
    Scanner in;
    NetChat chat;

    public String clientName;

    public Client(Socket socket, NetChat chat) {
        this.socket = socket;
        this.chat = chat;
    }

    public void run() {
        try {
            in = new Scanner(socket.getInputStream());
            out = new PrintStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        clientName = in.nextLine();
        System.out.println(clientName + " присоеденился");

        while (true) {
            if (in.hasNextLine()) {
                String line = in.nextLine();
                chat.send(line, this);
            }
        }
    }

    public String getClientName() {
        return clientName;
    }

    public void send(String message) {
        out.println(message);
    }
}