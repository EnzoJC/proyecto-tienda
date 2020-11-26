package model.database;

import java.sql.Connection;
import javax.swing.JOptionPane;

public class Conexion {
    private String user;
    private String password;
    private String URL;

    public Conexion() {
    }
    
    public Connection getConexion() {
	URL =   "jdbc:sqlserver://upn-shop-server.database.windows.net:1433;"
		+ "database=shop-database;"
		+ "user=administrador@upn-shop-server;"
		+ "password=querty123_;"
		+ "encrypt=true;"
		+ "trustServerCertificate=false;"
		+ "hostNameInCertificate=*.database.windows.net;"
		+ "loginTimeout=30;";	
	java.sql.Connection con = null;
	try {
	    con = java.sql.DriverManager.getConnection(URL);
	    //JOptionPane.showMessageDialog(null, "Conexión exitosa");
	    //con.close();
	} catch (java.sql.SQLException e) {
	    JOptionPane.showMessageDialog(null, "Conexión fallida\n" + e);
	}
        return con;  
    }
    public void setConexion(String user, String password) {
	URL =   "jdbc:sqlserver://upn-shop-server.database.windows.net:1433;"
		+ "database=shop-database;"
		+ "user=" + user + "@upn-shop-server;"
		+ "password=" + password + ";"
		+ "encrypt=true;"
		+ "trustServerCertificate=false;"
		+ "hostNameInCertificate=*.database.windows.net;"
		+ "loginTimeout=30;";
        //Server=.\SQLExpress;AttachDBFileName=c:\dir\\mydb.mdf;Database=dbName;Trusted_Connection=yes;
        
	java.sql.Connection con = null;
	try {
	    con = java.sql.DriverManager.getConnection(URL);
	    //JOptionPane.showMessageDialog(null, "Conexión exitosa");
	    con.close();
	} catch (java.sql.SQLException e) {
	    JOptionPane.showMessageDialog(null, "Conexión fallida\n" + e);
	}
    }
}
