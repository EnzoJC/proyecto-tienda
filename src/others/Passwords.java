package others;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class Passwords {

    public static String generar() {
	int n = (int) (Math.random() * 20 + 5);

	String cadena = "";
	for (int i = 33; i <= 122; i++) {
	    cadena += (char) i;
	}
	java.util.Random rand = new java.util.Random();

	String password = "";
	for (int i = n; i > 0; i--) {
	    password += cadena.charAt((int) (Math.random() * 50 + 1));
	};
	return password;
    }

    public static String encriptar(String password) {
	String returnPassword = "";

	for (int i = 0; i < password.length(); i++) {
	    if (password.charAt(i) >= ((char) 33) && password.charAt(i) <= ((char) 107)) {
		returnPassword = returnPassword + ((char) password.charAt(i) + 15);
	    } else if (password.charAt(i) >= ((char) 108) && password.charAt(i) <= ((char) 122)) {
		returnPassword = returnPassword + ((char) password.charAt(i) - 75);
	    }
	    if (i < password.length() - 1) {
		returnPassword += '-';
	    }
	}

	return returnPassword;
    }

    public static String desencriptar(String password) {
	String returnPassword = "";
	String[] numeros = password.split("-");

	for (String i : numeros) {
	    if (Integer.parseInt(i) >= 33 && Integer.parseInt(i) <= 47)
		returnPassword += (char) (Integer.parseInt(i) + 75);
	    else if (Integer.parseInt(i) >= 48 && Integer.parseInt(i) <= 122)
		returnPassword += (char) (Integer.parseInt(i) - 15);
	}

	return returnPassword;
    }
}
