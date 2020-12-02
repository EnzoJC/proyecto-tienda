package controller;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
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
	vista.btnCerrarSesion.addActionListener(this);
	vista.btnTemaClaro.addActionListener(this);
	vista.btnTemaOscuro.addActionListener(this);
	vista.btnEjecutarInsert.addActionListener(this);
	vista.btnEjecutarSelect.addActionListener(this);
	vista.btnEjecutarUpdate.addActionListener(this);
	vista.btnEjecutarDelete.addActionListener(this);
	vista.tablaProductos.setModel(this.getTablaTrabajadores());
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
		try {
		    ResultSet rsHorario = consulta.select("SELECT h.id_horario, h.hora_inicial, h.hora_salida "
			    + "FROM HORARIOS h, TRABAJADOR t "
			    + "WHERE t.id_horario = h.id_horario AND t.id_cajero =" + auxId);
		    while (rsHorario.next()) {
			horario = new Horario(rsHorario.getInt(1), rsHorario.getTime(2), rsHorario.getTime(3));
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

		try {
		    ResultSet rsCargo = consulta.select("SELECT c.id_cargo, c.cargo "
			    + "FROM Cargos c, TRABAJADOR t "
			    + "WHERE t.id_cargo = c.id_cargo AND t.id_cajero =" + auxId);
		    while (rsCargo.next()) {
			cargo = new Cargo(rsCargo.getInt(1), rsCargo.getString(2));
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
	DTM = new DefaultTableModel();
	DTM.addColumn("ID");
	DTM.addColumn("Supervisor");
	DTM.addColumn("Nombres");
	DTM.addColumn("Apellidos");
	DTM.addColumn("DNI");
	DTM.addColumn("Correo");
	DTM.addColumn("Fecha Nac.");
	DTM.addColumn("Teléfono");
	DTM.addColumn("Horario");
	DTM.addColumn("Cargo");
	DTM.addColumn("Última sesión");

	ResultSet rs = consulta.select("SELECT * FROM TRABAJADOR");
	ArrayList<Trabajador> arrayTrabajadores = new ArrayList<>();
	try {
	    while (rs.next()) {
		int auxId = rs.getInt(1);
		try {
		    ResultSet rsHorario = consulta.select("SELECT h.id_horario, h.hora_inicial, h.hora_salida "
			    + "FROM HORARIOS h, TRABAJADOR t "
			    + "WHERE t.id_horario = h.id_horario AND t.id_cajero =" + auxId);
		    while (rsHorario.next()) {
			horario = new Horario(rsHorario.getInt(1), rsHorario.getTime(2), rsHorario.getTime(3));
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

		try {
		    ResultSet rsCargo = consulta.select("SELECT c.id_cargo, c.cargo "
			    + "FROM Cargos c, TRABAJADOR t "
			    + "WHERE t.id_cargo = c.id_cargo AND t.id_cajero =" + auxId);
		    while (rsCargo.next()) {
			cargo = new Cargo(rsCargo.getInt(1), rsCargo.getString(2));
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

		Trabajador sup = null;
		try {
		    ResultSet rsSup = consulta.select("SELECT * FROM TRABAJADOR WHERE ID_CAJERO=" + rs.getInt(2));
		    while (rsSup.next()) {
			sup = new Trabajador(auxId, rsSup.getString(3), rsSup.getString(4), rsSup.getString(5), rsSup.getString(6), rsSup.getString(7),
				rsSup.getDate(8), rsSup.getString(9), horario, cargo, rsSup.getString(12), rsSup.getString(13));
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
	    datosTrabajador[1] = arrayTrabajadores.get(i).getIdSupervisor() == null ? "No tiene" : "" + arrayTrabajadores.get(i).getIdSupervisor();
	    datosTrabajador[2] = arrayTrabajadores.get(i).getNombres();
	    datosTrabajador[3] = arrayTrabajadores.get(i).getPrimerApellido() + " " + arrayTrabajadores.get(i).getSegundoApellido();
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
	    boolean isValido = false;

	    try {
		CallableStatement cstmt = consulta.getConexion().prepareCall("{call isUnico(?, ?)}");
		cstmt.setString("dni", vista.panelAgergarTrabajador.txtDNI.getText());
		cstmt.registerOutParameter(2, java.sql.Types.BIT);
		cstmt.execute();
		isValido = cstmt.getBoolean(2);
	    } catch (SQLException ex) {
		JOptionPane.showMessageDialog(null, "Error:\n" + ex);
	    } finally {
		try {
		    consulta.getConexion().close();
		} catch (SQLException ex) {
		    JOptionPane.showMessageDialog(null, "Error:\n" + ex);
		}
	    }

	    if (isValido && vista.panelAgergarTrabajador.txtDNI.getText().length() == 8) {
		trabajador.setDni(vista.panelAgergarTrabajador.txtDNI.getText());
	    } else {
		JOptionPane.showMessageDialog(null, "El DNI ingresado ya se encuentra registrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
	    }

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
	    limpiarTablas(vista.tablaProductos, DTM);
	    vista.tablaProductos.setModel(this.getTablaTrabajadores());
	}

	if (e.getSource() == vista.btnCerrarSesion) {
	    vista.dispose();
	    Login login = new Login();
	    FrmLogin f = new FrmLogin();
	    ControllerLogin controlador = new ControllerLogin(f, login, consulta);

	    f.setVisible(true);
	}

	if (e.getSource() == vista.btnTemaClaro) {
	    try {
		javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatLightLaf());
		javax.swing.SwingUtilities.updateComponentTreeUI(vista);
	    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
		JOptionPane.showMessageDialog(null, "Fallo al iniciar la libreria FlatLaf" + ex);
	    }
	}

	if (e.getSource() == vista.btnTemaOscuro) {
	    try {
		javax.swing.UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf());
		javax.swing.SwingUtilities.updateComponentTreeUI(vista);
	    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
		JOptionPane.showMessageDialog(null, "Fallo al iniciar la libreria FlatLaf" + ex);
	    }
	}
    }
}
