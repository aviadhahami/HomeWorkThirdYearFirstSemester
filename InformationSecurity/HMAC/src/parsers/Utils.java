package parsers;

public class Utils {
	final protected static char[] hexArray = "0123456789ABCDEF".toCharArray();

	public static String intArrayToHexStr(int[] data) {
		String output = "";
		String tempStr = "";
		int tempInt = 0;
		for (int cnt = 0; cnt < data.length; cnt++) {

			tempInt = data[cnt];

			tempStr = Integer.toHexString(tempInt);

			if (tempStr.length() == 1) {
				tempStr = "0000000" + tempStr;
			} else if (tempStr.length() == 2) {
				tempStr = "000000" + tempStr;
			} else if (tempStr.length() == 3) {
				tempStr = "00000" + tempStr;
			} else if (tempStr.length() == 4) {
				tempStr = "0000" + tempStr;
			} else if (tempStr.length() == 5) {
				tempStr = "000" + tempStr;
			} else if (tempStr.length() == 6) {
				tempStr = "00" + tempStr;
			} else if (tempStr.length() == 7) {
				tempStr = "0" + tempStr;
			}
			output = output + tempStr;
		} // end for loop
		return output;
	}// end intArrayToHexStr

	public static byte[] concat(byte[]... arrays) {
		// Determine the length of the result array
		int totalLength = 0;
		for (int i = 0; i < arrays.length; i++) {
			totalLength += arrays[i].length;
		}

		// create the result array
		byte[] result = new byte[totalLength];

		// copy the source arrays into the result array
		int currentIndex = 0;
		for (int i = 0; i < arrays.length; i++) {
			System.arraycopy(arrays[i], 0, result, currentIndex, arrays[i].length);
			currentIndex += arrays[i].length;
		}

		return result;
	}

	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}
	public static final byte[] intToByteArray(int value) {
		return new byte[] { (byte) (value >>> 24), (byte) (value >>> 16), (byte) (value >>> 8), (byte) value };
	}
}
