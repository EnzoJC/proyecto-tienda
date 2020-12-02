package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import model.Cargo;
import model.Horario;
import model.Trabajador;
import model.database.CRUD;
import model.database.Conexion;
import model.database.Login;
import others.Passwords;
import view.FrmAdmin;
import view.FrmCajero;
import view.FrmLogin;
import view.FrmSupervisor;

public class ControllerLogin implements ActionListener {

    private FrmLogin sesion;
    private Login login;
    private CRUD consulta;
    private Cargo cargo;
    private Horario horario;
    private Trabajador trabajador;

    public ControllerLogin() {
    }

    public ControllerLogin(FrmLogin sesion, Login login, CRUD consulta) {
	this.sesion = sesion;
	this.login = login;
	this.consulta = consulta;
	sesion.btnLogin.addActionListener(this);
	sesion.txtUser.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
		    //intentar();
		    nextForm(sesion.txtUser.getText(), new String(sesion.txtPassword.getPassword()));
		}
	    }
	});
	sesion.txtPassword.addKeyListener(new KeyAdapter() {
	    @Override
	    public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
		    //intentar();
		    nextForm(sesion.txtUser.getText(), new String(sesion.txtPassword.getPassword()));
		}
	    }
	});
	sesion.chkMostrar.addItemListener(new ItemListener() {
	    @Override
	    public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() != ItemEvent.SELECTED) {
		    sesion.txtPassword.setEchoChar('\u2022');
		} else {
		    sesion.txtPassword.setEchoChar((char) 0);
		}
	    }
	});
    }

//    public void intentar() {
//	login.setUser(sesion.txtUser.getText());
//	login.setPassword(new String(sesion.txtPassword.getPassword()));
//
//	consulta..setConexion(login.getUser(), login.getPassword());
//    }
    public Horario getHorarioFromSQL(int idCajero) {
	Horario auxHorario = null;
	try {
	    ResultSet rsHorario = consulta.select("SELECT h.id_horario, h.hora_inicial, h.hora_salida "
		    + "FROM HORARIOS h, TRABAJADOR t "
		    + "WHERE t.id_horario = h.id_horario AND t.id_cajero =" + idCajero);
	    while (rsHorario.next()) {
		auxHorario = new Horario(rsHorario.getInt(1), rsHorario.getTime(2), rsHorario.getTime(3));
	    }
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	} finally {
	    try {
		consulta.getConexion().close();
	    } catch (SQLException ex) {
		JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	    }
	}
	return auxHorario;
    }

    public Cargo getCargoFromSQL(int idCajero) {
	Cargo auxCargo = null;
	try {
	    ResultSet rsCargo = consulta.select("SELECT c.id_cargo, c.cargo "
		    + "FROM Cargos c, TRABAJADOR t "
		    + "WHERE t.id_cargo = c.id_cargo AND t.id_cajero =" + idCajero);
	    while (rsCargo.next()) {
		auxCargo = new Cargo(rsCargo.getInt(1), rsCargo.getString(2));
	    }
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	} finally {
	    try {
		consulta.getConexion().close();
	    } catch (SQLException ex) {
		JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	    }
	}
	return auxCargo;
    }

    public void nextForm(String txtUser, String txtPassword) {
	if (txtUser.compareTo("administrador") == 0 && txtPassword.compareTo(Passwords.desencriptar("38-42-116-39-41-46-64-65-66-110")) == 0) {
	    FrmAdmin form = new FrmAdmin();
	    CRUD crud = new CRUD();
	    ControllerAdmin controlador = new ControllerAdmin(form, crud);
	    sesion.dispose();
	    form.setVisible(true);
	} else {
	    try {
		CallableStatement cstmt = consulta.getConexion().prepareCall("{call buscarUsuario(?, ?, ?)}");
		cstmt.setString("usuario", txtUser);
		cstmt.setString("contrasenia", Passwords.encriptar(txtPassword));

		cstmt.registerOutParameter(3, java.sql.Types.BIT);
		cstmt.execute();
		boolean isValido = cstmt.getBoolean(3);

		if (isValido) {
		    ResultSet rs = consulta.select("SELECT * FROM TRABAJADOR WHERE USUARIO ='" + txtUser + "'");
		    try {
			while (rs.next()) {
			    int auxId = rs.getInt(1);
			    horario = getHorarioFromSQL(auxId);
			    cargo = getCargoFromSQL(auxId);
			    
			    trabajador = new Trabajador(auxId, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
				    rs.getDate(8), rs.getString(9), horario, cargo, rs.getString(12), rs.getString(13));
			}
		    } catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Error:\n" + ex);
		    } finally {
			try {
			    consulta.getConexion().close();
			} catch (SQLException ex) {
			    JOptionPane.showMessageDialog(null, "Error:\n" + ex);
			}
		    }
		    
		    if (cargo.getCargo().compareToIgnoreCase("Cajero") == 0) {
			LocalDateTime tiempoActual = LocalDateTime.now();
			trabajador.setUltimaSesion(Timestamp.valueOf(tiempoActual));
			consulta.update("UPDATE TRABAJADOR SET ultima_sesion ='" + trabajador.getUltimaSesion().toString() + "' WHERE ID_CAJERO = " + trabajador.getIdCajero());
			FrmCajero f = new FrmCajero();
			CRUD crud = new CRUD();
			ControllerCajero controlador = new ControllerCajero(f, trabajador, crud);
			sesion.dispose();
			f.setVisible(true);
		    } else if (cargo.getCargo().compareToIgnoreCase("Supervisor") == 0) {
			LocalDateTime tiempoActual = LocalDateTime.now();
			trabajador.setUltimaSesion(Timestamp.valueOf(tiempoActual));
			consulta.update("UPDATE TRABAJADOR SET ultima_sesion ='" + trabajador.getUltimaSesion().toString() + "' WHERE ID_CAJERO = " + trabajador.getIdCajero());
			FrmSupervisor f = new FrmSupervisor();
			CRUD crud = new CRUD();
			ControllerSupervisor controlador = new ControllerSupervisor(f, trabajador, crud);
			sesion.dispose();
			f.setVisible(true);
		    }
		} else {
		    JOptionPane.showMessageDialog(sesion, "Credenciales incorrectas");
		}
	    } catch (SQLException ex) {
		JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	    } finally {
		try {
		    consulta.getConexion().close();
		} catch (SQLException ex) {
		    JOptionPane.showMessageDialog(null, "Error:\n" + ex);
		}
	    }
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
