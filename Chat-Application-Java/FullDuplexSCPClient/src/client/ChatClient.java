package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
  public static void main(String[] args) throws IOException {
    if (args.length != 2) {
      System.err.println("Usage: java EchoClient <host name> <port number>");
      System.exit(1);
    }
    Scanner sc = new Scanner(System.in);
    String hostName = args[0];
    int portNumber = Integer.parseInt(args[1]);

    try (Socket scpSocket = new Socket(hostName, portNumber);
        PrintWriter out = new PrintWriter(scpSocket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(scpSocket.getInputStream()));) {
      BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
      String fromServer;
      String toServer;
      String displayInput = "";
      String fromUser;
      System.out.print("Enter your username: ");
      String username = sc.nextLine();
      String connectMessage = SCPProtocolClient.processOtherRequests("CONNECT|" + username,hostName,portNumber);
      out.println(connectMessage);

      while ((fromServer = in.readLine()) != null) {
        System.out.println("From Server" + fromServer);
        if(fromServer.startsWith("SCP CHAT")) {
          displayInput = SCPProtocolClient.processChatRequest(fromServer);
          System.out.println("Host: " + displayInput);
        } else {
          toServer = SCPProtocolClient.processOtherRequests(fromServer,hostName,portNumber);          
          out.println(toServer);          
          if(toServer.equals("TERMINATE")) {
            System.out.println("Server rejected your CONNECT request due to time difference since epoch");
            break;
          }
          if(toServer.startsWith("DISCONNECT")) {
            out.println(toServer.substring(11));
            System.out.println("Host disconnected the session");
            break;
          }
        }

        fromUser = stdIn.readLine();
        if (fromUser != null) {
          System.out.println("You: " + fromUser);
          toServer = SCPProtocolClient.processScpOutput(fromUser,scpSocket);
          out.println(toServer);
          if(fromUser.equals("DISCONNECT"))
            break;
        }
      }
    } catch (UnknownHostException e) {
      System.err.println("Don't know about host " + hostName);
      System.exit(1);
    } catch (IOException e) {
      System.err.println("Couldn't get I/O for the connection to " + hostName);
      System.exit(1);
    }
  }
}
