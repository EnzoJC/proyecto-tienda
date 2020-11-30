package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import model.database.CRUD;
import model.database.Conexion;
import model.database.Login;
import others.Passwords;
import view.FrmAdmin;
import view.FrmLogin;
import view.FrmSupervisor;

public class ControllerLogin implements ActionListener {

    private FrmLogin sesion;
    private Login login;
    private Conexion conexion;

    public ControllerLogin() {
    }

    public ControllerLogin(FrmLogin sesion, Login login, Conexion conexion) {
	this.sesion = sesion;
	this.login = login;
	this.conexion = conexion;
	sesion.btnLogin.addActionListener(this);
	sesion.txtUser.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
		    intentar();
		    nextForm(sesion.txtUser.getText(), new String(sesion.txtPassword.getPassword()));
		}
	    }
	});
	sesion.txtPassword.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
		    intentar();
		    nextForm(sesion.txtUser.getText(), new String(sesion.txtPassword.getPassword()));
		}
	    }
	});
	sesion.chkMostrar.addItemListener(new ItemListener() {
	    public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() != ItemEvent.SELECTED) {
		    sesion.txtPassword.setEchoChar('\u2022');
		} else {
		    sesion.txtPassword.setEchoChar((char) 0);
		}
	    }
	});
    }

    public void intentar() {
	login.setUser(sesion.txtUser.getText());
	login.setPassword(new String(sesion.txtPassword.getPassword()));

	conexion.setConexion(login.getUser(), login.getPassword());
    }

    public void nextForm(String txtUser, String txtPassword) {
        if (txtUser.compareTo("administrador") == 0 && txtPassword.compareTo(Passwords.desencriptar("38-42-116-39-41-46-64-65-66-110")) == 0) {
            FrmAdmin form = new FrmAdmin();
	    CRUD crud = new CRUD();
            ControllerAdmin controlador = new ControllerAdmin(form, crud);
            sesion.dispose();
            form.setVisible(true);
        }else{
	    JOptionPane.showMessageDialog(sesion, "Credenciales incorrectas. Intente de nuevo");
	}
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == sesion.btnLogin) {
	    //intentar();
	    nextForm(sesion.txtUser.getText(), new String(sesion.txtPassword.getPassword()));
	}
    }
}
