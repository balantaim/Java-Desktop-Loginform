package loginf;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class SecurePassword {

	public static String Encoder(String s) {
		Encoder encoder = Base64.getEncoder();
		String encodedString = encoder.encodeToString(s.getBytes());
		return encodedString;
	}
	public static String Decoder(String s) {
		Decoder decoder = Base64.getDecoder();
        byte[] bytes = decoder.decode(s);
		return new String(bytes);
	}
}
