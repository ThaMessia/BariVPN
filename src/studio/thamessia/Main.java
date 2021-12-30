package studio.thamessia;

import sun.misc.Signal;

import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    private static void ensureSecurity() throws IOException {
        System.out.println("Searching for a secure environment...");
        ProxyGenerator proxyChecker = new ProxyGenerator();

        for (;;) {
            Signal.handle(new Signal("INT"), sig -> {
                System.out.println("Goodbye and thanks for using BariVPN!");
                System.exit(0);
            });

            InetSocketAddress inetSocketAddress = new InetSocketAddress("www.google.com", 80);
            Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyChecker.generateProxy(), new Random().nextInt(65535)));

            //Proxy proxyManager = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("127.37.4.115", new Random().nextInt(65535)));

            Socket socket = new Socket(proxy);

            try {
                socket.connect(inetSocketAddress);
            } catch (ConnectException e) {
                System.out.print("");
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (socket.isConnected()) {
                System.out.println("Secure environment found!");
                System.out.println("New IP: " + proxy.address() + "\n");

                System.out.print("Insert URL: ");

                String urlManager = scanner.nextLine();

                URL url = new URL(urlManager);
                URLConnection urlConnection = url.openConnection(proxy);
                InputStream inputStream = urlConnection.getInputStream();

                StringBuilder stringBuilder = new StringBuilder();
                int i = 0;
                while((i = inputStream.read()) != -1){
                    stringBuilder.append((char) i);
                }

                System.out.println(stringBuilder);
                System.exit(0);
            }


        }
    }

    public static void main(String[] args) throws IOException {
        ensureSecurity();
    }
}
