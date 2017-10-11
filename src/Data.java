import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Data {
	public static int dataCount = 0;
	public static int noOfAttributes = 0;
	List<String> dataLine;
	
	public Data(String line) {
		String[] input = line.split(",");
		noOfAttributes = input.length-1;
		dataCount++;
		this.dataLine = Arrays.asList(line.split(","));
	}

	public List<String> getDataLine()
	{
		return this.dataLine;
	}
}
