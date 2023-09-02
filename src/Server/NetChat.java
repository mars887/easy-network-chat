package Server;

import java.util.ArrayList;

public class NetChat extends Thread{

    ArrayList<Client> clients;

    public NetChat(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public void send(String message,Client sender) {
        System.out.println(sender.getClientName() + " - " + message);
        for(Client client : clients) {
            if(client != sender) {
                client.send(sender.getClientName() + " - " + message);
            }
        }
    }
}
