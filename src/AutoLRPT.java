import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AutoLRPT {

	public static void main(String[] args) {

		//Path to folder AutoLRPT and to the file passes
		String homePath = System.getProperty("user.home")+"/AutoLRPT";
		String passesPath = System.getProperty("user.home")+"/AutoLRPT/passes";

		
		//Executing predict to get the pass times and save it in "passes"
		Process process;

		try {
			process = Runtime.getRuntime().exec("predict -p METEOR-M2 -o " + passesPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		//Reading the file and parsing the lines into the List "result"
		List<String> result = null;
		try {

			Stream<String> lines = Files.lines(Paths.get(passesPath));
			{
				result = lines.collect(Collectors.toList());
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//Reading the first line (AOS) from "passes", splitting it up and storing it in an Array
		String[] passArray = result.get(0).split(" ");
		String[] timeArray = passArray[3].split(":");

		
		//Getting the time offset of the timezone and converting the time from UTC to local time
		Date date = new Date();

		int offset = date.getTimezoneOffset() / 60;

		int hour = (Integer.parseInt(timeArray[0]) - offset)%24;
		if (hour<0) {hour=24+hour;};
		int minute = Integer.parseInt(timeArray[1]);

		//Scheduling the reception with at
		Process at;
		
		try {
			at = Runtime.getRuntime().exec("at -Mf " + homePath + "/start-mlrpt " + hour + ":" + minute);
		} catch (Exception c) {
			c.printStackTrace();
		}
		;

	}
}
