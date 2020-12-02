package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Anio;
import model.Cargo;
import model.Dia;
import model.Fecha;
import model.Horario;
import model.Mes;
import model.Trabajador;
import model.database.CRUD;
import model.database.Login;
import others.Passwords;
import view.FrmAdmin;
import view.FrmLogin;

public class ControllerAdmin implements ActionListener {

    private FrmAdmin vista;
    private CRUD consulta;
    private Trabajador trabajador;
    private Horario horario;
    private Fecha fecha;
    private Cargo cargo;
    private DefaultTableModel DTM;
    private DefaultTableModel DTMR;

    public ControllerAdmin() {
    }

    public ControllerAdmin(FrmAdmin vista, CRUD consulta) {
	this.vista = vista;
	this.consulta = consulta;
	this.vista.cboCargo.setModel(this.getListaCargos());
	ControllerPanelAgregarTrabajdor c = new ControllerPanelAgregarTrabajdor(vista.panelAgergarTrabajador, consulta);
	
	vista.panelAgergarTrabajador.btnAgregarUsuario.setVisible(false);

	vista.cboCargo.setSelectedItem(null);
	vista.cboCargo.addActionListener(this);
	vista.cboSupervisor.setVisible(false);
	vista.lblSupervisor.setVisible(false);
	vista.btnAgregar.addActionListener(this);
	vista.btnCerrarSesion.addActionListener(this);
	vista.btnTemaClaro.addActionListener(this);
	vista.btnTemaOscuro.addActionListener(this);
	vista.btnEjecutarInsert.addActionListener(this);
	vista.btnEjecutarSelect.addActionListener(this);
	vista.btnEjecutarUpdate.addActionListener(this);
	vista.btnEjecutarDelete.addActionListener(this);
	vista.tablaTrabajadores.setModel(this.getTablaTrabajadores());
	vista.addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent we) {
		int respuesta = JOptionPane.showConfirmDialog(vista, "¿Salir?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION);
		if (respuesta == JOptionPane.YES_OPTION) {
		    cerrarSesion();
		}
	    }
	});
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
	} finally {
	    try {
		consulta.getConexion().close();
	    } catch (SQLException ex) {
		JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	    }
	}
	return DCM;
    }

    private DefaultComboBoxModel getListaSupervisores() {
	DefaultComboBoxModel DCM = new DefaultComboBoxModel();
	ResultSet rs = consulta.select("SELECT * FROM TRABAJADOR WHERE id_cargo = 101");
	try {
	    while (rs.next()) {
		int auxId = rs.getInt(1);
		horario = getHorarioFromSQL(auxId);
		cargo = getCargoFromSQL(auxId);

		DCM.addElement(new Trabajador(auxId, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
			rs.getDate(8), rs.getString(9), horario, cargo, rs.getString(12), rs.getString(13)));
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

    public DefaultTableModel getTablaTrabajadores() {
	DTM = new DefaultTableModel() {
	    @Override
	    public boolean isCellEditable(int row, int column) {
		return false;
	    }
	};
	String[] headers = {"ID", "Nombres", "Apellidos", "Supervisor", "DNI", "Correo", "Fecha Nac.", "Teléfono", "Horario", "Cargo", "Última Sesión"};
	for (String i : headers) {
	    DTM.addColumn(i);
	}

	ResultSet rs = consulta.select("SELECT * FROM TRABAJADOR");
	ArrayList<Trabajador> arrayTrabajadores = new ArrayList<>();
	try {
	    while (rs.next()) {
		int auxId = rs.getInt(1);
		horario = getHorarioFromSQL(auxId);
		cargo = getCargoFromSQL(auxId);
		Trabajador sup = getSupToCajFromSQL(rs.getInt(2));

		arrayTrabajadores.add(new Trabajador(auxId, sup, rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7),
			rs.getDate(8), rs.getString(9), horario, cargo, rs.getString(12), rs.getString(13), rs.getTimestamp(14)));
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

	for (int i = 0; i < arrayTrabajadores.size(); i++) {
	    String[] datosTrabajador = new String[11];

	    datosTrabajador[0] = Integer.toString(arrayTrabajadores.get(i).getIdCajero());
	    datosTrabajador[1] = arrayTrabajadores.get(i).getNombres();
	    datosTrabajador[2] = arrayTrabajadores.get(i).getPrimerApellido() + " " + arrayTrabajadores.get(i).getSegundoApellido();
	    datosTrabajador[3] = arrayTrabajadores.get(i).getIdSupervisor() == null ? "No tiene" : "" + arrayTrabajadores.get(i).getIdSupervisor();
	    datosTrabajador[4] = arrayTrabajadores.get(i).getDni();
	    datosTrabajador[5] = arrayTrabajadores.get(i).getCorreo();
	    datosTrabajador[6] = arrayTrabajadores.get(i).getFechaNacimiento().toString();
	    datosTrabajador[7] = arrayTrabajadores.get(i).getTelefono();
	    datosTrabajador[8] = arrayTrabajadores.get(i).getHorario().toString();
	    datosTrabajador[9] = arrayTrabajadores.get(i).getCargo().toString();
	    datosTrabajador[10] = arrayTrabajadores.get(i).getUltimaSesion() == null ? "-" : arrayTrabajadores.get(i).getUltimaSesion().toString();
	    DTM.addRow(datosTrabajador);
	}

	return DTM;
    }

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

    public Trabajador getSupToCajFromSQL(int idSupervisor) {
	Trabajador supervisor = null;
	try {
	    ResultSet rsSup = consulta.select("SELECT * FROM TRABAJADOR WHERE ID_CAJERO=" + idSupervisor);
	    while (rsSup.next()) {
		supervisor = new Trabajador(rsSup.getInt(1), rsSup.getString(3), rsSup.getString(4), rsSup.getString(5), rsSup.getString(6), rsSup.getString(7),
			rsSup.getDate(8), rsSup.getString(9), getHorarioFromSQL(idSupervisor), getCargoFromSQL(idSupervisor), rsSup.getString(12), rsSup.getString(13));
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
	return supervisor;
    }

    public void limpiarControles() {
	vista.cboCargo.setSelectedItem(null);
	vista.cboSupervisor.setSelectedItem(null);
	vista.panelAgergarTrabajador.txtNombres.setText("");
	vista.panelAgergarTrabajador.txtPrimerApellido.setText("");
	vista.panelAgergarTrabajador.txtSegundoApellido.setText("");
	vista.panelAgergarTrabajador.txtDNI.setText("");
	vista.panelAgergarTrabajador.txtCorreo.setText("");
	vista.panelAgergarTrabajador.txtTelefono.setText("");
	vista.panelAgergarTrabajador.txtUsuario.setText("");
	vista.panelAgergarTrabajador.txtPassword.setText("");
    }

    private void limpiarTablas(JTable t, DefaultTableModel d) {
	for (int i = t.getRowCount() - 1; i >= 0; i--) {
	    d.removeRow(i);
	}
    }

    public void cerrarSesion() {
	vista.dispose();
	Login login = new Login();
	FrmLogin f = new FrmLogin();
	ControllerLogin controlador = new ControllerLogin(f, login, consulta);

	f.setVisible(true);
    }

    public void temaClaro() {
	try {
	    javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
	    javax.swing.SwingUtilities.updateComponentTreeUI(vista);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    JOptionPane.showMessageDialog(null, "Fallo al iniciar la libreria FlatLaf" + ex);
	}
    }

    public void temaOscuro() {
	try {
	    javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
	    javax.swing.SwingUtilities.updateComponentTreeUI(vista);
	} catch (javax.swing.UnsupportedLookAndFeelException ex) {
	    JOptionPane.showMessageDialog(null, "Fallo al iniciar la libreria FlatLaf" + ex);
	}
    }

    public boolean isRegistrado() {
	try {
	    CallableStatement cstmt = consulta.getConexion().prepareCall("{call isUnico(?, ?)}");
	    cstmt.setString("dni", vista.panelAgergarTrabajador.txtDNI.getText());
	    cstmt.registerOutParameter(2, java.sql.Types.BIT);
	    cstmt.execute();
	    return cstmt.getBoolean(2);
	} catch (SQLException ex) {
	    JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	} finally {
	    try {
		consulta.getConexion().close();
	    } catch (SQLException ex) {
		JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	    }
	}
	return false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == vista.cboCargo) {
	    if (vista.cboCargo.getSelectedIndex() == 0) {
		vista.cboSupervisor.setVisible(true);
		vista.lblSupervisor.setVisible(true);
		vista.cboSupervisor.setModel(this.getListaSupervisores());
		vista.cboSupervisor.setSelectedItem(null);
	    } else {
		vista.cboSupervisor.setVisible(false);
		vista.lblSupervisor.setVisible(false);
	    }
	}
	if (e.getSource() == vista.btnAgregar && vista.cboCargo.getSelectedIndex() != -1) {
	    trabajador = new Trabajador();
	    trabajador.setNombres(vista.panelAgergarTrabajador.txtNombres.getText());
	    trabajador.setPrimerApellido(vista.panelAgergarTrabajador.txtPrimerApellido.getText());
	    trabajador.setSegundoApellido(vista.panelAgergarTrabajador.txtSegundoApellido.getText());

	    boolean isValido = isRegistrado();
	    if (isValido && vista.panelAgergarTrabajador.txtDNI.getText().length() == 8) {
		trabajador.setDni(vista.panelAgergarTrabajador.txtDNI.getText());
	    } else {
		JOptionPane.showMessageDialog(null, "El DNI ingresado ya se encuentra registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
	    }

	    trabajador.setCorreo(vista.panelAgergarTrabajador.txtCorreo.getText() + vista.panelAgergarTrabajador.cboDominios.getSelectedItem());

	    fecha = new Fecha();
	    fecha.setDia((Dia) vista.panelAgergarTrabajador.cboDia.getSelectedItem());
	    fecha.setMes((Mes) vista.panelAgergarTrabajador.cboMes.getSelectedItem());
	    fecha.setAnio((Anio) vista.panelAgergarTrabajador.cboAnio.getSelectedItem());

	    boolean f = fecha.validarFecha(Integer.parseInt(fecha.getDia().toString()), fecha.getMes().getMes(), fecha.getAnio().getAnio());

	    if (f) {
		trabajador.setFechaNacimiento(Date.valueOf(fecha.getAnio().toString() + "-" + fecha.getMes().getMes() + "-" + fecha.getDia().toString()));
	    } else {
		JOptionPane.showMessageDialog(null, "Fecha incorrecta", "Advertencia", JOptionPane.WARNING_MESSAGE);
	    }

	    trabajador.setTelefono(vista.panelAgergarTrabajador.txtTelefono.getText());
	    trabajador.setHorario((Horario) vista.panelAgergarTrabajador.cboHorarios.getSelectedItem());
	    trabajador.setUsuario(vista.panelAgergarTrabajador.txtDNI.getText());
	    trabajador.setPassword(Passwords.encriptar(vista.panelAgergarTrabajador.txtPassword.getText()));
	    trabajador.setCargo((Cargo) vista.cboCargo.getSelectedItem());
	    cargo = (Cargo) vista.cboCargo.getSelectedItem();

	    if (cargo.getCargo().compareTo("Cajero") == 0 && isValido && f) {
		trabajador.setIdSupervisor((Trabajador) vista.cboSupervisor.getSelectedItem());
		consulta.insert("INSERT INTO TRABAJADOR VALUES("
			+ "'" + trabajador.getIdSupervisor().getIdCajero() + "',"
			+ "'" + trabajador.getNombres() + "',"
			+ "'" + trabajador.getPrimerApellido() + "',"
			+ "'" + trabajador.getSegundoApellido() + "',"
			+ "'" + trabajador.getDni() + "',"
			+ "'" + trabajador.getCorreo() + "',"
			+ "'" + trabajador.getFechaNacimiento().toString() + "',"
			+ "'" + trabajador.getTelefono() + "',"
			+ trabajador.getHorario().getId() + ","
			+ trabajador.getCargo().getId() + ","
			+ "'" + trabajador.getUsuario() + "',"
			+ "'" + trabajador.getPassword() + "',"
			+ "NULL"
			+ ")");
		JOptionPane.showMessageDialog(null, "Registro correcto", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	    } else if (cargo.getCargo().compareTo("Supervisor") == 0 && isValido && f && vista.cboSupervisor.getSelectedIndex() != -1) {
		consulta.insert("INSERT INTO TRABAJADOR VALUES("
			+ "NULL,"
			+ "'" + trabajador.getNombres() + "',"
			+ "'" + trabajador.getPrimerApellido() + "',"
			+ "'" + trabajador.getSegundoApellido() + "',"
			+ "'" + trabajador.getDni() + "',"
			+ "'" + trabajador.getCorreo() + "',"
			+ "'" + trabajador.getFechaNacimiento().toString() + "',"
			+ "'" + trabajador.getTelefono() + "',"
			+ trabajador.getHorario().getId() + ","
			+ trabajador.getCargo().getId() + ","
			+ "'" + trabajador.getUsuario() + "',"
			+ "'" + trabajador.getPassword() + "',"
			+ "NULL"
			+ ")");
		JOptionPane.showMessageDialog(null, "Registro correcto", "Mensaje", JOptionPane.INFORMATION_MESSAGE);
	    }
	    limpiarControles();
	    limpiarTablas(vista.tablaTrabajadores, DTM);
	    vista.tablaTrabajadores.setModel(this.getTablaTrabajadores());
	}

	if (e.getSource() == vista.btnCerrarSesion) {
	    cerrarSesion();
	}

	if (e.getSource() == vista.btnTemaClaro) {
	    temaClaro();
	}

	if (e.getSource() == vista.btnTemaOscuro) {
	    temaOscuro();
	}
	if (e.getSource() == vista.btnEjecutarInsert) {
	    this.limpiarTablas(vista.tablaResultados, DTMR);
	    if (consulta.insert(vista.areaInsert.getText()))
		JOptionPane.showMessageDialog(vista, "Ejecución correcta", "Mensaje", JOptionPane.WARNING_MESSAGE);
	    else 
		JOptionPane.showMessageDialog(vista, "Ejecución incorrecta", "Mensaje", JOptionPane.WARNING_MESSAGE);
	}
	if (e.getSource() == vista.btnEjecutarSelect) {
	    this.limpiarTablas(vista.tablaResultados, DTMR);
	    ResultSet rs = consulta.select(vista.areaSelect.getText());
	    DTMR = new DefaultTableModel() {
		@Override
		public boolean isCellEditable(int row, int column) {
		    return false;
		}
	    };
	    try {
		ResultSetMetaData rsmd = rs.getMetaData();
		int columnsNumber = rsmd.getColumnCount();
		String[] valores = new String[columnsNumber];
		for (int i = 1; i <= columnsNumber; i++) {
		    DTMR.addColumn(rsmd.getColumnName(i));
		}
		while (rs.next()) {
		    for (int i = 1; i <= columnsNumber; i++) {
			String columnValue = rs.getString(i);
			valores[i - 1] = columnValue;
		    }
		    DTMR.addRow(valores);
		}
		vista.tablaResultados.setModel(DTMR);
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
	if (e.getSource() == vista.btnEjecutarUpdate) {
	    this.limpiarTablas(vista.tablaResultados, DTMR);
	    if (consulta.update(vista.areaUpdate.getText()))
		JOptionPane.showMessageDialog(vista, "Ejecución correcta", "Mensaje", JOptionPane.WARNING_MESSAGE);
	    else 
		JOptionPane.showMessageDialog(vista, "Ejecución incorrecta", "Mensaje", JOptionPane.WARNING_MESSAGE);
	}
	if (e.getSource() == vista.btnEjecutarDelete) {
	    this.limpiarTablas(vista.tablaResultados, DTMR);
	    if (consulta.delete(vista.areaDelete.getText()))
		JOptionPane.showMessageDialog(vista, "Ejecución correcta", "Mensaje", JOptionPane.WARNING_MESSAGE);
	    else 
		JOptionPane.showMessageDialog(vista, "Ejecución incorrecta", "Mensaje", JOptionPane.WARNING_MESSAGE);
	}
    }
}
