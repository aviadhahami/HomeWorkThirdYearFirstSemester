package IOHandlers;

import java.io.FileOutputStream;
import java.io.PrintStream;

import org.apache.commons.codec.binary.Base64;

public class FileWriter {

	public static void write(String input) {
		try (PrintStream out = new PrintStream(new FileOutputStream(InputWrapper.getDigestFile()))) {
			out.print(new String(Base64.encodeBase64(input.getBytes())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
