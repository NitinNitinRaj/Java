package Sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Serversocket {

  public static void main(String[] args)
    throws UnknownHostException, IOException {
    ServerSocket serverSocket = new ServerSocket(
      12900,
      100,
      InetAddress.getByName("localhost")
    );
    System.out.println("Server Started at: " + serverSocket);
    while (true) {
      System.out.println("Waiting for a connection...");
      Socket activeSocket = serverSocket.accept();
      System.out.println("Received a connection from " + activeSocket);
      Runnable runnable = () -> {
        handleClientRequest(activeSocket);
      };
      new Thread(runnable).start();
    }
  }

  private static void handleClientRequest(Socket activeSocket) {
    BufferedReader socketReader;
    BufferedWriter socketWriter;

    try {
      socketReader =
        new BufferedReader(
          new InputStreamReader(activeSocket.getInputStream())
        );
      socketWriter =
        new BufferedWriter(
          new OutputStreamWriter(activeSocket.getOutputStream())
        );

      String inMsg = null;
      while ((inMsg = socketReader.readLine()) != null) {
        System.out.println("Received form client: " + inMsg);
        String outMsg = inMsg;
        socketWriter.write(outMsg);
        socketWriter.newLine();
        socketWriter.flush();
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        activeSocket.close();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
