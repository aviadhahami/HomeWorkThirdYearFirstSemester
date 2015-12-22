package HMAC;

/**
 * Main class to run the HMAC. <br/>
 * The program receives the following (via CLI, in this order):
 * <ol>
 * <li>Input file(ASCII encoded)</li>
 * <li>Digest file</li>
 * <li>Key file(Which contains the key)</li>
 * <li>Functional option ("compute" in order to compute HMAC or "verify" in
 * order to verify HMACed file with given digest</li>
 * </ul>
 * 
 * @author aviadh & Aviyas
 *
 */
public class Main {

	public static void main(String[] args) {
		InputWrapper parsedInput = Parser.parseInput(args);
		FilesContentHolder fp = new FilesContentHolder(parsedInput.getInputFile(), parsedInput.getDigestFile(),
				parsedInput.getKeyFile());
		if (InputWrapper.getFunctionOption().toLowerCase().equals("verify")) {
			System.out.println("verify");
		} else if (InputWrapper.getFunctionOption().toLowerCase().equals("compute")) {
			System.out.println("compute");
		} else {
			System.err.println("Not valid option!");
			System.exit(1);
		}
	}
}
