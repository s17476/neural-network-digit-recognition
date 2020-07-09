package controler;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Extension;

public class DataController {
	static final String extentFilePath = "data.bin";
	static final String learningFilePath = "res/pendigits.tra";
	static final String testingFilePath = "res/pendigits.tes";
	
	public static void save(Extension extension) {
		try {
			// Write the extent to the stream
	    	if(extension.getSize() > 0) {
		    	ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(extentFilePath));
				extension.writeExtents(out);
				out.close();
	    	}	
		} catch (Exception e) {e.printStackTrace();}
	}
	
	public static Extension load() {
		Extension tmpExtension = new Extension();
		try {
		    // Read the extent from the stream
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(extentFilePath));
			tmpExtension.readExtents(in);
			in.close();	
			return tmpExtension;
		} catch (Exception e) {e.printStackTrace();}
		return new Extension();
	}
	
	public static Stream<String> getLearnData() throws IOException {
		return Files.lines(Paths.get(learningFilePath));
	}
	
	public static Stream<String> getTestingData() throws IOException {
		return Files.lines(Paths.get(testingFilePath));
	}
}
