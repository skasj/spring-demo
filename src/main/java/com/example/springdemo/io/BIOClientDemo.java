package com.example.springdemo.io;

import java.io.*;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class BIOClientDemo {

    public static void main(String[] args) throws InterruptedException {
        try (Socket socket = new Socket()){
            socket.setSendBufferSize(10);

            socket.bind(new InetSocketAddress(InetAddress.getLocalHost(),5001));
//            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(),5002));
            socket.connect(new InetSocketAddress(InetAddress.getLocalHost(),8379));
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream,StandardCharsets.UTF_8));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));
            writer.write("Socket Test");
            writer.newLine();
            writer.flush();
            Scanner scanner = new Scanner(System.in);
            while (true){
//                System.out.println(reader.readLine());
                writer.write(scanner.nextLine());
                writer.newLine();
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
