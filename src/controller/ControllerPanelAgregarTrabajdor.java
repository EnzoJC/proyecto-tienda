package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Anio;
import model.Trabajador;
import model.Dia;
import model.Dominio;
import model.Fecha;
import model.Horario;
import model.Mes;
import model.database.CRUD;
import others.Passwords;
import view.PanelAgergarTrabajador;

public class ControllerPanelAgregarTrabajdor implements ActionListener, KeyListener {

    private PanelAgergarTrabajador vista;
    private CRUD consulta;
    private Trabajador trabajador;
    private Horario horario;
    private Statement stmt;
    private Fecha fecha;
    private boolean isLlenito = false;

    public ControllerPanelAgregarTrabajdor() {
    }

    public ControllerPanelAgregarTrabajdor(PanelAgergarTrabajador vista, CRUD consulta) {
	this.vista = vista;
	this.consulta = consulta;
	this.vista.btnGenerarPassword.addActionListener(this);
	this.vista.btnAgregarUsuario.addActionListener(this);
	this.vista.txtDNI.addKeyListener(this);
	this.vista.cboDia.addActionListener(this);
	this.vista.txtTelefono.addKeyListener(this);
	this.vista.cboDia.setModel(this.getListaFecha().get(2));
	this.vista.cboMes.setModel(this.getListaFecha().get(1));
	this.vista.cboAnio.setModel(this.getListaFecha().get(0));
	this.vista.cboDominios.setModel(this.getListaDominios());
	this.vista.cboHorarios.setModel(this.getListaHorarios());

	//this.vista.cboDia.putClientProperty("JComponent.outline", "error");
	//java.awt.Window window = SwingUtilities.windowForComponent(vista);
    }

    public DefaultComboBoxModel getListaDominios() {
	DefaultComboBoxModel DCM = new DefaultComboBoxModel();
	ResultSet rs = consulta.select("SELECT * FROM DOMINIOS");
	try {
	    while (rs.next()) {
		DCM.addElement(new Dominio(rs.getInt(1), rs.getString(2)));
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
	return DCM;
    }

    public ArrayList<DefaultComboBoxModel> getListaFecha() {
	ArrayList<DefaultComboBoxModel> arregloDCM = new ArrayList(3);
	int year = Year.now().getValue();
	DefaultComboBoxModel auxAnio = new DefaultComboBoxModel();
	DefaultComboBoxModel auxMes = new DefaultComboBoxModel();
	DefaultComboBoxModel auxDia = new DefaultComboBoxModel();

	auxAnio.addElement("Año");

	for (int i = 1900; i <= year - 18; i++) {
	    auxAnio.addElement(new Anio(i));
	}
	arregloDCM.add(auxAnio);

	auxMes.addElement("Mes");
	String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto",
	    "Septiembre", "Octubre", "Noviembre", "Diciembre"};

	for (int i = 0; i < 12; i++) {
	    auxMes.addElement(new Mes(i + 1, meses[i]));
	}
	arregloDCM.add(auxMes);

	auxDia.addElement("Día");
	for (int i = 1; i <= 31; i++) {
	    auxDia.addElement(new Dia(i));
	}
	arregloDCM.add(auxDia);

	return arregloDCM;
    }

    public DefaultComboBoxModel getListaHorarios() {
	DefaultComboBoxModel DCM = new DefaultComboBoxModel();
	ResultSet rs = consulta.select("SELECT * FROM HORARIOS");
	try {
	    while (rs.next()) {
		DCM.addElement(new Horario(rs.getInt(1), rs.getTime(2), rs.getTime(3)));
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
	return DCM;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == vista.btnGenerarPassword) {
	    vista.txtPassword.setText(Passwords.generar());
	}
	if (e.getSource() == vista.btnAgregarUsuario) {
	    trabajador.setNombres(vista.txtNombres.getText());
	    trabajador.setPrimerApellido(vista.txtPrimerApellido.getText());
	    trabajador.setSegundoApellido(vista.txtSegundoApellido.getText());
	    trabajador.setDni(vista.txtDNI.getText());
	    trabajador.setCorreo(vista.txtCorreo.getText() + vista.cboDominios.getSelectedItem());
	    boolean f = fecha.validarFecha(Integer.parseInt(fecha.getDia().toString()), Integer.parseInt(fecha.getDia().toString()), Integer.parseInt(fecha.getDia().toString()));
	    if (f) {
		trabajador.setFechaNacimiento(Date.valueOf(fecha.getAnio().toString() + fecha.getMes().toString() + fecha.getDia().toString()));
	    } else {
		JOptionPane.showMessageDialog(null, "Fecha incorrecta", "Advertencia", JOptionPane.WARNING_MESSAGE);
	    }
	    trabajador.setTelefono(vista.txtTelefono.getText());
	    horario = (Horario) vista.cboHorarios.getSelectedItem();
	    trabajador.setHorario(horario);
	    trabajador.setUsuario(vista.txtDNI.getText());
	    trabajador.setPassword(Passwords.encriptar(vista.txtPassword.getText()));
	    
	}
	if (e.getSource() == vista.cboDia) {
	    if (vista.cboDia.getSelectedIndex() != 0) {
		fecha = new Fecha();
		fecha.setDia((Dia) vista.cboDia.getSelectedItem());
	    }
	}
	if (e.getSource() == vista.cboMes) {
	    if (vista.cboMes.getSelectedIndex() != 0) {
		fecha = new Fecha();
		fecha.setMes((Mes) vista.cboMes.getSelectedItem());
	    }
	}
	if (e.getSource() == vista.cboAnio) {
	    if (vista.cboAnio.getSelectedIndex() != 0) {
		fecha = new Fecha();
		fecha.setAnio((Anio) vista.cboAnio.getSelectedItem());
	    }
	}
    }

    @Override
    public void keyTyped(KeyEvent e) {
	if (e.getSource() == vista.txtDNI) {
	    if ((e.getKeyChar() < '0' || e.getKeyChar() > '9')) {
		e.consume();
	    }
	    if (vista.txtDNI.getText().length() == 8) {
		e.consume();
	    }
	    if (vista.txtDNI.getText().length() == 7 && e.getKeyChar() != com.sun.glass.events.KeyEvent.VK_BACKSPACE) {
		isLlenito = true;
		vista.txtDNI.putClientProperty("JComponent.outline", null);
	    } else if (!isLlenito) {
		vista.txtDNI.putClientProperty("JComponent.outline", "error");
	    }
	}
	if (e.getSource() == vista.txtTelefono) {
	    if ((e.getKeyChar() < '0' || e.getKeyChar() > '9')) {
		e.consume();
	    }
	    if (vista.txtTelefono.getText().length() == 9) {
		e.consume();
	    }
	}
    }

    @Override
    public void keyPressed(KeyEvent e) {
	if (e.getSource() == vista.txtDNI) {
	    if (vista.txtDNI.getText().length() == 8 && e.getKeyChar() == com.sun.glass.events.KeyEvent.VK_BACKSPACE) {
		vista.txtDNI.putClientProperty("JComponent.outline", "error");
		isLlenito = false;
	    }
	}
    }

    @Override
    public void keyReleased(KeyEvent e) {
	//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
