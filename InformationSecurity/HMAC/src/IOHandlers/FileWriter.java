package IOHandlers;

import java.io.FileOutputStream;
import java.io.PrintStream;

import org.apache.commons.codec.binary.Base64;

public class FileWriter {

	public static void write(byte[] bs) {
		try (PrintStream out = new PrintStream(new FileOutputStream(InputWrapper.getDigestFile()))) {
			out.print(Base64.encodeBase64String(bs));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
