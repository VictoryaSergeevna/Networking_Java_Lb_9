package com.company;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Socket clientSocket;
    private static ServerSocket server;
    private static BufferedReader in;
    private static BufferedWriter out;

    public static void main(String[] args) {

        try {
            try {
                int port=4004;
                server = new ServerSocket(port);
                System.out.println("Сервер запущен!");
                clientSocket = server.accept();
                try {
                    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    String word;
                    while ((word = in.readLine()) != null) {
                        if (word.equals("bye")) {
                            System.out.println("Сообшение: " + word);
                            break;
                        } else  {
                            System.out.println(word);
                            out.write("Это Сервер! Вы написали : " + word + "\n");
                            out.flush();
                        }
                    }
                } finally {
                    clientSocket.close();
                    in.close();
                    out.close();
                }
            } finally {
                System.out.println("Сервер закрыт!");
                server.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
    }

