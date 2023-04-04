package Sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Clientsocket {

  public static void main(String[] args) {
    Socket socket = null;
    BufferedReader socketReader = null;
    BufferedWriter socketWriter = null;
    try {
      socket = new Socket("localhost", 12900);
      System.out.println("Client started at " + socket.getLocalSocketAddress());
      socketReader =
        new BufferedReader(new InputStreamReader(socket.getInputStream()));
      socketWriter =
        new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
      BufferedReader consoleReader = new BufferedReader(
        new InputStreamReader(System.in)
      );
      String promptMsg ="Please enter a message (Bye to quit):";
      String outMsg = null;
      System.out.println(promptMsg);
      while((outMsg = consoleReader.readLine()) != null){
        if(outMsg.equalsIgnoreCase("bye")) break;
        socketWriter.write(outMsg);
        socketWriter.newLine();
        socketWriter.flush();

        String inMsg = socketReader.readLine();
        System.out.println("Server: " + inMsg);
        System.out.println();
        System.out.println(promptMsg);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }finally{
        try {
            socket.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
  }
}
