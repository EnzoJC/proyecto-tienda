package others;

public class Passwords {

    public static String generar() {
        int n = (int) (Math.random() * 20 + 5);
	
	String cadena = "";
        for (int i = 33; i <= 122; i++)
            cadena += (char) i;
        java.util.Random rand = new java.util.Random();
	
        String password = "";
        for (int i = n; i > 0; i--)
            password += cadena.charAt((int)(Math.random() * 50 + 1));;
        return password;
    }
}
