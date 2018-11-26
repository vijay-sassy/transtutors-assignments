package client;

import java.net.Socket;

public class SCPProtocolClient {
  private static String username;
  public static String getUsername() {
    return username;
  }
  public static void setUsername(String username) {
    SCPProtocolClient.username = username;
  }

  public static String makeAcknowShort() {
    return "SCP ACKNOWLEDGESCP END";
  }
  
  public static String makeDisconnect() {
    return "SCP DISCONNECTSCP END";
  }
  
  public static String processChatRequest(String input) {
    if(input.startsWith("SCP DISCONNECT")) {
      return "DISCONNECT";
    }
    int startpos = input.indexOf("CONTENT")+7;
    int endpos = input.lastIndexOf("SCP");
    String message = input.substring(startpos, endpos);
    return message;
  }
  
  public static String processOtherRequests(String input,String hostname,int port) {
    String message = "";
    if(input.startsWith("CONNECT")) {
      System.out.println(input);
      setUsername(input.substring(8));
      System.out.println("User P:" + username);
      message = "SCP CONNECTSERVERADDRESS " + hostname + "SERVERPORT " + port
        + "REQUESTCREATED " + System.currentTimeMillis() + "USERNAME \"" + 
        username + "\"SCP END";
//      System.out.println(message);
    } else if(input.startsWith("SCP ACCEPT")) {
      message = "SCP ACKNOWLEDGEUSERNAME \"" + username + "\"SERVERADDRESS"
          + " " + hostname + "SERVERPORT " + port + "SCP END";
//        System.out.println(message);  
    } else if(input.startsWith("SCP REJECT")) {
      return "TERMINATE";
    } else if(input.startsWith("SCP DISCONNECT")) {
      return "DISCONNECT|SCP ACKNOWLEDGESCP END";
    }
    return message;
  }
  
  public static String processScpOutput(String userInput,Socket scpSocket) {
    if(userInput.equals("DISCONNECT")) {
      return makeAcknowShort();
    }
    String outData = "SCP CHATREMOTEADDRESS " + scpSocket.getLocalAddress()
      + "REMOTEPORT " + scpSocket.getLocalPort() + "MESSAGECONTENT" + userInput
      + "SCP END";
    return outData;
  }
}
