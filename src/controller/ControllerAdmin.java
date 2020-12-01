package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import model.Anio;
import model.Cargo;
import model.Dia;
import model.Fecha;
import model.Horario;
import model.Mes;
import model.Trabajador;
import model.database.CRUD;
import others.Passwords;
import view.FrmAdmin;

public class ControllerAdmin implements ActionListener {

    private FrmAdmin vista;
    private CRUD consulta;
    private Trabajador trabajador;
    private Horario horario;
    private Fecha fecha;
    private Cargo cargo;

    public ControllerAdmin() {
    }

    public ControllerAdmin(FrmAdmin vista, CRUD consulta) {
	this.vista = vista;
	this.consulta = consulta;
	this.vista.cboCargo.setModel(this.getListaCargos());
	ControllerPanelAgregarTrabajdor c = new ControllerPanelAgregarTrabajdor(vista.panelAgergarTrabajador, consulta);

	vista.panelAgergarTrabajador.cboDia.setModel(c.getListaFecha().get(2));
	vista.panelAgergarTrabajador.cboMes.setModel(c.getListaFecha().get(1));
	vista.panelAgergarTrabajador.cboAnio.setModel(c.getListaFecha().get(0));
	vista.panelAgergarTrabajador.cboDominios.setModel(c.getListaDominios());
	vista.panelAgergarTrabajador.cboHorarios.setModel(c.getListaHorarios());
	vista.panelAgergarTrabajador.btnAgregarUsuario.setVisible(false);

	vista.cboCargo.setSelectedItem(null);
	vista.cboCargo.addActionListener(this);
	vista.cboSupervisor.setVisible(false);
	vista.lblSupervisor.setVisible(false);
	vista.btnAgregar.addActionListener(this);
    }

    private DefaultComboBoxModel getListaCargos() {
	DefaultComboBoxModel DCM = new DefaultComboBoxModel();
	ResultSet rs = consulta.select("SELECT * FROM CARGOS");
	try {
	    while (rs.next()) {
		DCM.addElement(new Cargo(rs.getInt(1), rs.getString(2)));
	    }
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	}
	return DCM;
    }

    private DefaultComboBoxModel getListaSupervisores() {
	DefaultComboBoxModel DCM = new DefaultComboBoxModel();
	//Cargo c = (Cargo) vista.cboCargo.getSelectedItem();
	ResultSet rs = consulta.select("SELECT * FROM TRABAJADOR WHERE id_cargo = 101" );
	
	try {
	    while (rs.next()) {
		int auxId = rs.getInt(1);
		ResultSet rsHorario = consulta.select("SELECT h.id_horario, h.hora_inicial, h.hora_salida " +
							"FROM HORARIOS h, TRABAJADOR t " +
							"WHERE t.id_horario = h.id_horario AND t.id_cajero =" + auxId);
		while (rsHorario.next()){
		    horario = new Horario(rsHorario.getInt(1), rsHorario.getTime(2), rsHorario.getTime(3));
		}
		
		ResultSet rsCargo = consulta.select("SELECT c.id_cargo, c.cargo " +
						    "FROM Cargos c, TRABAJADOR t " +
						    "WHERE t.id_cargo = c.id_cargo AND t.id_cajero =" + auxId);
		while (rsHorario.next()){
		    cargo = new Cargo(rsCargo.getInt(1), rsCargo.getString(2));
		}
		
		DCM.addElement(new Trabajador(auxId, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), 
				rs.getDate(8), rs.getString(9), horario, cargo, rs.getString(12), rs.getString(13)));
	    }
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	}
	return DCM;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == vista.cboCargo) {
	    if (vista.cboCargo.getSelectedItem().toString().compareTo("Cajero") == 0) {
		vista.cboSupervisor.setVisible(true);
		vista.lblSupervisor.setVisible(true);
		vista.cboSupervisor.setModel(this.getListaSupervisores());
		vista.cboSupervisor.setSelectedItem(null);
	    } else {
		vista.cboSupervisor.setVisible(false);
		vista.lblSupervisor.setVisible(false);
	    }
	}
	if (e.getSource() == vista.btnAgregar) {
	    trabajador = new Trabajador();
	    trabajador.setNombres(vista.panelAgergarTrabajador.txtNombres.getText());
	    trabajador.setPrimerApellido(vista.panelAgergarTrabajador.txtPrimerApellido.getText());
	    trabajador.setSegundoApellido(vista.panelAgergarTrabajador.txtSegundoApellido.getText());
	    trabajador.setDni(vista.panelAgergarTrabajador.txtDNI.getText());
	    trabajador.setCorreo(vista.panelAgergarTrabajador.txtCorreo.getText() + vista.panelAgergarTrabajador.cboDominios.getSelectedItem());
	    fecha = new Fecha();
	    fecha.setDia((Dia) vista.panelAgergarTrabajador.cboDia.getSelectedItem());
	    Mes auxMes = (Mes) vista.panelAgergarTrabajador.cboMes.getSelectedItem();
	    fecha.setAnio((Anio) vista.panelAgergarTrabajador.cboAnio.getSelectedItem());
	    boolean f = fecha.validarFecha(Integer.parseInt(fecha.getDia().toString()), auxMes.getMes(), Integer.parseInt(fecha.getAnio().toString()));
	    if (f) {
		trabajador.setFechaNacimiento(Date.valueOf(fecha.getAnio().toString() + "-" + auxMes.getMes() + "-" + fecha.getDia().toString()));
	    } else {
		JOptionPane.showMessageDialog(null, "Fecha incorrecta", "Advertencia", JOptionPane.WARNING_MESSAGE);
	    }
	    trabajador.setTelefono(vista.panelAgergarTrabajador.txtTelefono.getText());
	    horario = (Horario) vista.panelAgergarTrabajador.cboHorarios.getSelectedItem();
	    trabajador.setHorario(horario);
	    trabajador.setUsuario(vista.panelAgergarTrabajador.txtDNI.getText());
	    trabajador.setPassword(Passwords.encriptar(vista.panelAgergarTrabajador.txtPassword.getText()));
	    trabajador.setCargo((Cargo) vista.cboCargo.getSelectedItem());
	    cargo = (Cargo) vista.cboCargo.getSelectedItem();
	     
	    if (cargo.getCargo().compareTo("Cajero") == 0) {
		trabajador.setIdSupervisor((Trabajador) vista.cboSupervisor.getSelectedItem());
		consulta.insert("INSERT INTO TRABAJADOR VALUES("
						+ "'" + trabajador.getIdSupervisor().getIdCajero()	+ "'," 
						+ "'" + trabajador.getNombres()				+ "'," 
						+ "'" + trabajador.getPrimerApellido()			+ "'," 
						+ "'" + trabajador.getSegundoApellido()			+ "'," 
						+ "'" + trabajador.getDni()				+ "'," 
						+ "'" + trabajador.getCorreo()				+ "'," 
						+ "'" + trabajador.getFechaNacimiento().toString()	+ "'," 
						+ "'" + trabajador.getTelefono()			+ "'," 
						+	trabajador.getHorario().getId()			+ ","
						+	trabajador.getCargo().getId()			+ ","
						+ "'" + trabajador.getUsuario()				+ "'," 
						+ "'" + trabajador.getPassword()			+ "'," +
						        "NULL"
						+ ")");
		JOptionPane.showMessageDialog(null, "Registro correcto", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	    }else{
		consulta.insert("INSERT INTO TRABAJADOR VALUES("
						+ "NULL,"
						+ "'" + trabajador.getNombres()				+ "'," 
						+ "'" + trabajador.getPrimerApellido()			+ "'," 
						+ "'" + trabajador.getSegundoApellido()			+ "'," 
						+ "'" + trabajador.getDni()				+ "'," 
						+ "'" + trabajador.getCorreo()				+ "'," 
						+ "'" + trabajador.getFechaNacimiento().toString()	+ "'," 
						+ "'" + trabajador.getTelefono()			+ "'," 
						+	trabajador.getHorario().getId()			+ "," 
						+	trabajador.getCargo().getId()			+ "," 
						+ "'" + trabajador.getUsuario()				+ "'," 
						+ "'" + trabajador.getPassword()			+ "'," +
						        "NULL"
						+ ")");
	    }

	}
    }
}
