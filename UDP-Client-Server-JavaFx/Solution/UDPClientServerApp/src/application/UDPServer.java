package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

public class UDPServer {
  public static final int SERVERPORT = 8888;
  public ArrayList<String> action(int code) {
	  WriteToFile wtf = new WriteToFile();
	  if(code == 1) {
	    return wtf.display();
	  } else if(code == 2) {
	    wtf.clear();
	  } else {
	  try {
			wtf.receive(wtf); 
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	  }
	  ArrayList<String> empty = new ArrayList<>();
	  return empty;
  }
  public static void main(String[] args) throws InterruptedException {
  }
}
