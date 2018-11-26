package server;

import java.net.Socket;

public class SCPProtocolServer {
  public static String makeAccept(String username,Socket clientSocket) {
    String acceptSCP = "SCP ACCEPTUSERNAME \"" + username + "\"CLIENTADDRESS " +
      clientSocket.getLocalAddress() + "CLIENTPORT" + " " + clientSocket.getLocalPort()
      + "SCP END";
    System.out.println(acceptSCP);
    return acceptSCP;
  }
 
  public static String makeReject(double timeDiff,Socket clientSocket) {
    String rejectSCP = "SCP REJECTTIMEDIFFERENTIAL " + timeDiff + "REMOTEADDRESS "
      + clientSocket.getLocalAddress() + "SCP END";
    System.out.println(rejectSCP);
    return rejectSCP;
  }
  
  public static String makeAcknowShort() {
    return "SCP ACKNOWLEDGESCP END";
  }
  
  public static String makeDisconnect() {
    return "SCP DISCONNECTSCP END";
  }
  
  public static String processInput(String input) {
    if(input.startsWith("SCP DISCONNECT")) {
      return "DISCONNECT";
    }
    int startpos = input.indexOf("CONTENT")+7;
    int endpos = input.lastIndexOf("SCP");
    String response = input.substring(startpos, endpos);
    return response;
  }
  
  public static String processScpInput(String input,String username,Socket clientSocket,long epochTime,String welcomeMessage) {
    String scpResponse = null;
    if(input.startsWith("SCP CONNECT")) {
      double timeDiff = (System.currentTimeMillis() - epochTime) / 1000;
      if(timeDiff > 5) {
        System.out.println("Time since epoch is more than 5 seconds. So, rejecting the request");
        return makeReject(timeDiff, clientSocket);
      } else {
        System.out.println("Accepting client's request");
        return makeAccept(username, clientSocket);
      }
    } else if(input.startsWith("SCP ACKNOWLEDGEUSER")) {
//      System.out.println("Welcome about");
      return processScpOutput(welcomeMessage,clientSocket);  
    } else if(input.equals("DISCONNECT")) {
      return makeAcknowShort();
    }
    return scpResponse;
  }
  
  public static String processScpOutput(String output,Socket clientSocket) {
    if(output.equals("DISCONNECT")) {
      return makeAcknowShort();
    }
    String outData = "SCP CHATREMOTEADDRESS " + clientSocket.getLocalAddress()
      + "REMOTEPORT " + clientSocket.getLocalPort() + "MESSAGECONTENT" + output
      + "SCP END";
 //   System.out.println(outData);
    return outData;
  }
  
}
