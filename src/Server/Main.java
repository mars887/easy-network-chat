package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) throws IOException {

        ServerSocket server = new ServerSocket(1234);

        ArrayList<Client> clients = new ArrayList<>();
        NetChat chat = new NetChat(clients);

        while(true) {
            Socket socket = server.accept();

            clients.add(new Client(socket,chat));

            clients.get(clients.size() - 1).start();
        }
    }

}