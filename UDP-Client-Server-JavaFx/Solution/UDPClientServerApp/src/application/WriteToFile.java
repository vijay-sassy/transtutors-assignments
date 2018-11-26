package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.time.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Properties;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class WriteToFile extends TimerTask {

    ArrayList<String> serverData = new ArrayList<String>();
    public void clear() {
      serverData.clear();
    }
    public ArrayList<String> display() {
      return serverData;    
    }
	public void receive(WriteToFile wtf) throws IOException, InterruptedException {
			DatagramSocket dsock = new DatagramSocket(2000);            
		    DatagramPacket dpack;
		    
		    System.out.println("Started");
		 
		    while(true)   
		    {
		      byte arr1[] = new byte[100];
		      dpack = new DatagramPacket(arr1, arr1.length);
		      dsock.receive(dpack);                                                           
		      byte arr2[] = dpack.getData();             
		      String str = new String(arr2,0, dpack.getLength());
		      str = str.replaceAll(":","\t")+ "\t" + LocalDate.now() + " "
		        + LocalTime.now() + "\t" + "/127.0.0.1";
              serverData.add(str);
              System.out.println(str);
  			  Timer tm=new Timer();
        	  tm.schedule(wtf,0,2000);
		    }
		    }
	@Override
	public void run() {
		System.out.println("Log's AL" + serverData); 
		Collections.sort(serverData);
		try {
			FileReader reader = new FileReader(System.getProperty("PropertyFilePath"));
			Properties p = new Properties();
			p.load(reader);
			File file = new File(p.getProperty("OutputLogFilePath"));
			PrintWriter pw = new PrintWriter(file);
			for (String sd : serverData) {
				pw.println(sd);
			}
			pw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

}
