package loginf;

public class ValidationRegister {
	
	public static boolean checkName(String uName) {
		boolean nField = false;
		if(uName.length() > 7) {
			nField=true;
			for(int i=0;i<uName.length();i++) {
				if (Character.isWhitespace(uName.charAt(i)) ||
						Character.isSpaceChar(uName.charAt(i))) {
					nField=false;
					break;
				}
			}
		}
		return nField;
	}
	public static boolean checkPass(String uPass) {
		boolean pField = false;
//		final String passRegex="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!?,.@#$%^&*()]).{8,20}";
		final String passRegex ="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).*[A-Za-z0-9].{8,20}$";
//		 Pattern pattern =Pattern.compile(passRegex);
		 if(uPass.matches(passRegex)) {
			 pField=true;
		 }
		return pField;
	}
	public static boolean repPass(String uPass, String repPass) {
		boolean repField = false;
		if(uPass.equals(repPass)) {
			repField=true;
		}
		return repField;
	}
	
}
