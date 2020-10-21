package com.example.springdemo.io;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class BIOServiceDemo {

    public static void main(String[] args) throws InterruptedException {
        try (ServerSocket serverSocket = new ServerSocket();){
            serverSocket.bind(new InetSocketAddress(5002));
            Socket accept = serverSocket.accept();
            BufferedReader reader = new BufferedReader(new InputStreamReader(accept.getInputStream(),StandardCharsets.UTF_8));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(accept.getOutputStream(),StandardCharsets.UTF_8));
            while (true){
                System.out.println(reader.readLine());
                Thread.sleep(1000);
                writer.write("Socket heart");
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
