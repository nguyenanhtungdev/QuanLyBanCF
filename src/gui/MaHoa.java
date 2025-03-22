package gui;

import java.security.MessageDigest;
import java.util.Base64;

public class MaHoa {

	public static String toSHA1(String pass) {
		String salt = "hbuwbdi@HionnH;shjdb!jn12343";
		String result = null;
		
		pass = pass + salt;
		
		try {
			byte[] dataBytes = pass.getBytes("UTF-8");
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			result = Base64.getEncoder().encodeToString(md.digest(dataBytes));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
}
