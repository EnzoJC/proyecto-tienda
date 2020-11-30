package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import model.Horario;
import model.Mes;
import model.database.CRUD;
import others.Passwords;
import view.PanelAgergarTrabajador;

public class ControllerPanelAgregarTrabajdor implements ActionListener, KeyListener {

    private PanelAgergarTrabajador vista;
    private CRUD consulta;
    private Trabajador trabajdor;
    private Horario horario;
    private Statement stmt;
    private boolean isLlenito = false;

    public ControllerPanelAgregarTrabajdor() {
    }

    public ControllerPanelAgregarTrabajdor(PanelAgergarTrabajador vista, CRUD consulta) {
	this.vista = vista;
	this.consulta = consulta;
	this.vista.btnGenerarPassword.addActionListener(this);
	this.vista.btnAgregarUsuario.addActionListener(this);
	this.vista.txtDNI.addKeyListener(this);

	this.vista.cboDia.putClientProperty("JComponent.outline", "error");
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

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == vista.btnGenerarPassword) {
	    vista.txtPassword.setText(Passwords.generar());
	}
	if (e.getSource() == vista.btnAgregarUsuario) {
	    trabajdor.setNombres(vista.txtNombres.getText());
	    trabajdor.setPrimerApellido(vista.txtPrimerApellido.getText());
	    trabajdor.setSegundoApellido(vista.txtSegundoApellido.getText());
	    trabajdor.setDni(vista.txtDNI.getText());
	    trabajdor.setCorreo(vista.txtCorreo.getText() + vista.cboDominios.getSelectedItem());
	    
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
