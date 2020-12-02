package main;

import controller.ControllerLogin;
import javax.swing.JOptionPane;
import model.database.CRUD;
import model.database.Conexion;
import model.database.Login;
import view.FrmLogin;

public class Main {
    public static void main(String[] args) {
	try {
	    javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    JOptionPane.showMessageDialog(null, "Fallo al iniciar la libreria FlatLaf" + ex);
	}

	FrmLogin vista = new FrmLogin();
	Login login = new Login();
	CRUD modelo = new CRUD();
	ControllerLogin controlador = new ControllerLogin(vista, login, modelo);

	java.awt.EventQueue.invokeLater(new Runnable() {
	    public void run() {
	    vista.setVisible(true);
	    }
	});
    }
}
