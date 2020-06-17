package io.sansam.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 * BIODemo
 * </p>
 *
 * @author houcb
 * @since 2020-06-17 16:45
 */
public class BIODemo {

    public static void main(String[] args) {
        BIOServer server = new BIOServer();
        server.start();


        try (Socket client = new Socket(InetAddress.getLocalHost(), server.getPort())) {
            System.out.println("client start");
//            PrintWriter writer = new PrintWriter(client.getOutputStream());
//            String sendData = "client id = " + System.currentTimeMillis();
//            writer.write(sendData);
//            writer.flush();
//            System.out.println("client send msg");

            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            StringBuilder builder = new StringBuilder();
            reader.lines().forEach(s -> builder.append(s));
            System.out.println("response : " + builder.toString());

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

class BIOServer extends Thread {

    private ServerSocket serverSocket;

    public int getPort() {
        return serverSocket.getLocalPort();
    }

    @Override
    public void run() {

        try {
            serverSocket = new ServerSocket(0);
            ExecutorService threadPool = Executors.newFixedThreadPool(10);
            System.out.println("server start!");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("server receive msg!");

                threadPool.execute(new RequestHandler(socket));
//                    final RequestHandler handler = new RequestHandler(socket);
//                    handler.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}

class RequestHandler extends Thread {

    private Socket socket;

    public RequestHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
//            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//            StringBuilder builder = new StringBuilder();
//            reader.lines().forEach(s -> builder.append(s));
//
//            System.out.println("server receive : " + builder.toString());

            writer = new PrintWriter(socket.getOutputStream());
            writer.write("hello world ");
            writer.flush();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                writer.close();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}