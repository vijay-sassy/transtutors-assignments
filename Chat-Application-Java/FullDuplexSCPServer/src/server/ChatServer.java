package server;

import java.io.IOException;
import java.net.ServerSocket;

public class ChatServer {
  private static String hostname;
  private static int portNumber;
  private static String welcomeMessage;

  public static void main(String[] args) throws IOException {
    if (args.length != 3) {
      System.err.println("Usage: java ChatServer <hostname> <port number> <welcome message>");
      System.exit(1);
    }

    hostname = String.valueOf(args[0]);
    portNumber = Integer.parseInt(args[1]);
    welcomeMessage = String.valueOf(args[2]);
    System.out.println("Waiting for a new client");
    boolean listening = true;
    try (ServerSocket serverSocket = new ServerSocket(portNumber)) {
      while(listening) {
        new ChatServerThread(serverSocket.accept(),welcomeMessage).start();
      }
    } catch (IOException e) {
      System.err.println("Could not listen on port " + portNumber);
      System.exit(-1);
  }
}

}
