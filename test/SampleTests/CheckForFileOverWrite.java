package SampleTests;

import java.io.File;
import java.io.IOException;

public class CheckForFileOverWrite {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File testFile = new File("datafiles/Tests/TestFile.txt");
		try {
			testFile.createNewFile();
			System.out.println("The file was created");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
