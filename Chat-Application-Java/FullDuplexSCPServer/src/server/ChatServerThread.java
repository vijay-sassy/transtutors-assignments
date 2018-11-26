package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ChatServerThread extends Thread{
  private static int portNumber;
  private static String welcomeMessage;
  private static String username;
  private static boolean isUserSet = false;
  private static boolean isChatStarted = false;
  private Socket clientSocket = null;
  private String welcomeMsg;

  public ChatServerThread(Socket socket,String message) {
      super("ChatServerThread");
      clientSocket = socket;
      welcomeMessage = message;
  }
  public void run() {
    try(
    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    ) {

  String fromClient, toClient, fromUser, displayInput;
  BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
  
  long epochTime = 0;

  while ((fromClient = in.readLine()) != null) {
    System.out.println("From Client:" + fromClient);

    if (!isUserSet) {
      if (fromClient.startsWith("SCP CONNECT")) {
        int startpos = fromClient.indexOf("\"");
        int endpos = fromClient.indexOf("\"", startpos + 1);
        username = fromClient.substring(startpos + 1, endpos);
        isUserSet = true;
      }
    }
 
    if (!isChatStarted) {
      if (fromClient.equals("SCP ACKNOWLEDGEUSER")) {
        System.out.println("Chat will begin");
        isChatStarted = true;
      }
      if(fromClient.startsWith("SCP CONNECT")) {
        int startpos = fromClient.indexOf("CREATED")+8;
        int endpos = fromClient.lastIndexOf("USER");
        String response = fromClient.substring(startpos, endpos);
        epochTime = Long.valueOf(response);
      }
      toClient = SCPProtocolServer.processScpInput(fromClient, username, clientSocket, epochTime,
          welcomeMessage);
      out.println(toClient);
    } else {
      displayInput = SCPProtocolServer.processInput(fromClient);
      if (displayInput.equals("DISCONNECT")) {
        toClient = SCPProtocolServer.processScpInput(displayInput, username, clientSocket,
            epochTime, welcomeMessage);
        out.println(toClient);
        break;
      }
      System.out.println(username + ": " + displayInput);
    }

    fromUser = stdIn.readLine();
    if (fromUser != null) {
      System.out.println("You: " + fromUser);
      toClient = SCPProtocolServer.processScpOutput(fromUser, clientSocket);
      out.println(toClient);
      if (fromUser.equals("DISCONNECT"))
        break;
    }
  }
} catch (IOException e) {
  System.out
      .println("Exception caught when trying to listen on port " + portNumber + " or listening for a connection");
  System.out.println(e.getMessage());
}
}
}
