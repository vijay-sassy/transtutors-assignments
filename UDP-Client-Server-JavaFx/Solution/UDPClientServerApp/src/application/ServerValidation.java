package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

public class ServerValidation {
	public static final int SERVERPORT = 8888;
	private ArrayList<String> loadData = new ArrayList<>();

	public void load() {
		Scanner sc;
		try {
			FileReader reader = new FileReader(System.getProperty("PropertyFilePath"));
			Properties p = new Properties();
			p.load(reader);
			File file = new File(p.getProperty("ServerDataFilePath"));
			sc = new Scanner(file);
			while (sc.hasNextLine()) {
				String data = sc.nextLine();
				loadData.add(data);
				System.out.println(data);
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public String validate(String str) {
		System.out.println(str);
		int phoneCounter = 0;
		int pinCounter = 0;
		for (int i = 0; i < loadData.size(); i++) {
			if (!str.split(":")[1].equals(loadData.get(i).split(" ")[0])) {
				++phoneCounter;
			}

			if (phoneCounter == 16) {
				return "Message Status**Invalid number";
			}

			if (!str.split(":")[2].equals(loadData.get(i).split(" ")[1])) {
				++pinCounter;
			}

			if (pinCounter == 16) {
				return "Message Status**Invalid pin";
			}

			if ((str.split(":")[1].equals(loadData.get(i).split(" ")[0])
					&& (str.split(":")[2].equals(loadData.get(i).split(" ")[1])))) {
				String data = str.replaceAll(":", "\t") + "\t" + LocalDate.now() + " " + LocalTime.now() + "\t"
						+ "/127.0.0.1";
				return "Message Status**Success";
			}
		}
		return "Error Processing";
	}
}
