package com.company;

import java.io.*;
import java.net.Socket;

public class Client {
    private static Socket clientSocket;
    private static BufferedReader reader;
    private static BufferedReader in;
    private static BufferedWriter out;
    public static void main(String[] args) {
        try {
            try {
                int portNumber =4004;
                clientSocket = new Socket("localhost", portNumber);
                reader = new BufferedReader(new InputStreamReader(System.in));
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                System.out.println("Введите текст здесь:");
                String word = reader.readLine();
                out.write(word + "\n");
                out.flush();
                String serverWord;
                while ((serverWord = in.readLine()) != null) {
                    if (serverWord.equals("bye")) {
                        break;
                    }

                    System.out.println("Message:"+serverWord);
                }
            } finally {
                System.out.println("Клиент был закрыт...");
                clientSocket.close();
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
