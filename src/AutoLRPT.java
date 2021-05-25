import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AutoLRPT {

	public static void main(String[] args) {

		String homePath = System.getProperty("user.home") + "/AutoLRPT";
		String passesPath = System.getProperty("user.home") + "/AutoLRPT/passes";

		Process process;

		try {
			process = Runtime.getRuntime().exec("predict -p METEOR-M2 -o " + passesPath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> result = null;
		try {

			Stream<String> lines = Files.lines(Paths.get(passesPath));
			{
				result = lines.collect(Collectors.toList());
//		        System.out.println(result.get(0));
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		String[] passArray = result.get(0).split(" ");
		String[] timeArray = passArray[3].split(":");

		

		Date date = new Date();

		int offset = date.getTimezoneOffset() / 60;

		int hour = Integer.parseInt(timeArray[0]) - offset;
		int minute = Integer.parseInt(timeArray[1]);
		
		if (hour==22) {hour=0;}
		if (hour==23) {hour=1;};

		Process at;
		
		try {
			at = Runtime.getRuntime().exec("at -f " + homePath + "/start-mlrpt " + hour + ":" + minute);
		} catch (Exception c) {
			c.printStackTrace();
		}
		;

	}
}