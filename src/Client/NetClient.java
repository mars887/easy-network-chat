package Client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class NetClient {

    static final String serverIP = "127.0.0.1";
    static final int serverPort = 1234;

    static Scanner in;
    static Scanner sin;
    static PrintWriter out;

    static String clientName = "";

    public static void main(String[] args) throws InterruptedException {

        sin = new Scanner(System.in);
        System.out.print("client name - ");
        clientName = sin.nextLine();


        boolean flag;
        do {
            flag = connect();
        } while (!flag);

        out.println(clientName);
        out.flush();

        while (true) {
            if(sin.hasNextLine()) {
                String line = sin.nextLine();
                out.println(line);
                out.flush();
            }
        }
    }

    static boolean connect() throws InterruptedException {
        try {
            Socket socket = new Socket(serverIP, serverPort);
            in = new Scanner(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());

            new Thread() {
                @Override
                public void run() {
                    while (true) {
                        if(in.hasNextLine()) {
                            System.out.println(in.nextLine());
                        }
                    }
                }
            }.start();


            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Thread.sleep(100);
            return false;
        }
    }
}
