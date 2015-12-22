package HMAC;

import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileWriter {

	public static void write(String input) {
		try (PrintStream out = new PrintStream(new FileOutputStream(InputWrapper.getDigestFile()))) {
			out.print(input);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
