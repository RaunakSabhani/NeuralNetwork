import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PostProcess {

	File inputFile;
	List<Data> inputData;
	public PostProcess(File inputFile) {
		this.inputFile = inputFile;
		this.inputData = new ArrayList<>();
	}
	
	public void readData() {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(inputFile));
			String line;
			while((line = reader.readLine())!= null) {
				Data data = new Data(line);
				inputData.add(data);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public List<Data> getInputData()
	{
		return this.inputData;
	}
}
