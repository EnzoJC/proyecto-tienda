package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.Anio;
import model.Categoria;
import model.Descuento;
import model.Dia;
import model.Ganancia;
import model.Genero;
import model.Marca;
import model.Producto;
import model.Talla;
import model.Trabajador;
import model.Impuesto;
import model.Suministro;
import model.Kardex;
import model.Mes;
import model.Proveedor;
import model.database.CRUD;
import model.database.Login;
import others.Passwords;
import view.FrmLogin;
import view.FrmSupervisor;
import view.PanelAgergarTrabajador;
import view.PanelAgregarProveedor;

public class ControllerSupervisor implements ActionListener {

    private FrmSupervisor vista;
    private Trabajador supervisor;
    private CRUD consulta;
    private Producto producto;
    private Suministro suministro;
    private Kardex kardex;
    private Proveedor proveedor;
    private DefaultComboBoxModel dcmGanancia;
    private DefaultComboBoxModel dcmCategoria;
    private DefaultComboBoxModel dcmDescuento;
    private DefaultComboBoxModel dcmGenero;
    private DefaultComboBoxModel dcmMarca;
    private DefaultComboBoxModel dcmTalla;
    private PanelAgregarProveedor panelProveedor = new PanelAgregarProveedor();
    private PanelAgergarTrabajador panelTrabajador = new PanelAgergarTrabajador();

    public ControllerSupervisor() {
    }

    public ControllerSupervisor(FrmSupervisor vista, Trabajador supervisor, CRUD consulta) {
	this.vista = vista;
	this.supervisor = supervisor;
	this.consulta = consulta;
	vista.btnAgregarCajero.addActionListener(this);
	vista.btnTemaClaro.addActionListener(this);
	vista.btnTemaOscuro.addActionListener(this);
	vista.btnCerrarSesion.addActionListener(this);
	this.panelProveedor.btnAgregarProveedor.addActionListener(this);
	this.vista.btnBuscarProveedor.addActionListener(this);
	this.vista.btnBuscarProducto.addActionListener(this);

	vista.cboGanancia.setModel(getListaGanancia());
	vista.cboCategoria.setModel(getListaCategoria());
	vista.cboDescuento.setModel(getListaDescuento());
	vista.cboGenero.setModel(getListaGenero());
	vista.cboMarca.setModel(getListaMarca());
	vista.cboTalla.setModel(getListaTalla());
	this.vista.cboDia.setModel(this.getListaFecha().get(2));
	this.vista.cboMes.setModel(this.getListaFecha().get(1));
	this.vista.cboAnio.setModel(this.getListaFecha().get(0));

	vista.cboGanancia.setSelectedItem(null);
	vista.cboCategoria.setSelectedItem(null);
	vista.cboDescuento.setSelectedItem(null);
	vista.cboGenero.setSelectedItem(null);
	vista.cboMarca.setSelectedItem(null);
	vista.cboTalla.setSelectedItem(null);

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

    public void cerrarSesion() {
	vista.dispose();
	Login login = new Login();
	FrmLogin f = new FrmLogin();
	ControllerLogin controlador = new ControllerLogin(f, login, consulta);
	f.setVisible(true);
    }

    public DefaultComboBoxModel getListaGanancia() {
	dcmGanancia = new DefaultComboBoxModel();
	ResultSet rs = consulta.select("SELECT * FROM GANANCIAS");
	try {
	    while (rs.next()) {
		dcmGanancia.addElement(new Ganancia(rs.getInt(1), rs.getDouble(2)));
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
	return dcmGanancia;
    }

    public DefaultComboBoxModel getListaCategoria() {
	dcmCategoria = new DefaultComboBoxModel();
	ResultSet rs = consulta.select("SELECT * FROM CATEGORIAS");
	try {
	    while (rs.next()) {
		dcmCategoria.addElement(new Categoria(rs.getInt(1), rs.getString(2)));
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
	return dcmCategoria;
    }

    public DefaultComboBoxModel getListaDescuento() {
	dcmDescuento = new DefaultComboBoxModel();
	ResultSet rs = consulta.select("SELECT * FROM DESCUENTOS");
	try {
	    while (rs.next()) {
		dcmDescuento.addElement(new Descuento(rs.getInt(1), rs.getString(2), rs.getDouble(3)));
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
	return dcmDescuento;
    }

    public DefaultComboBoxModel getListaGenero() {
	dcmGenero = new DefaultComboBoxModel();
	ResultSet rs = consulta.select("SELECT * FROM GENEROS");
	try {
	    while (rs.next()) {
		dcmGenero.addElement(new Genero(rs.getInt(1), rs.getString(2)));
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
	return dcmGenero;
    }

    public DefaultComboBoxModel getListaMarca() {
	dcmMarca = new DefaultComboBoxModel();
	ResultSet rs = consulta.select("SELECT * FROM MARCAS");
	try {
	    while (rs.next()) {
		dcmMarca.addElement(new Marca(rs.getInt(1), rs.getString(2)));
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
	return dcmMarca;
    }

    public DefaultComboBoxModel getListaTalla() {
	dcmTalla = new DefaultComboBoxModel();
	ResultSet rs = consulta.select("SELECT * FROM TALLAS");
	try {
	    while (rs.next()) {
		dcmTalla.addElement(new Talla(rs.getInt(1), rs.getString(2)));
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
	return dcmTalla;
    }

    public double getIGV() {
	Impuesto impuesto = null;
	try {
	    ResultSet rs = consulta.select("SELECT * FROM IMPUESTOS");
	    while (rs.next()) {
		impuesto = new Impuesto(rs.getInt(1), rs.getDouble(2));
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
	return impuesto != null ? impuesto.getPorcentaje() : 0;
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

    public void llenarProducto() {
	producto = new Producto();
	producto.setNombre(vista.txtNombre.getText());
	producto.setStock(Integer.parseInt(vista.txtCantidad.getText()));

	Ganancia ganancia = (Ganancia) vista.cboGanancia.getSelectedItem();
	double valorVenta = Double.parseDouble(vista.txtPrecioCompra.getText()) * (1 + ganancia.getPorcentaje());
	double precioVentaSD = valorVenta * getIGV();
	Descuento descuento = (Descuento) vista.cboDescuento.getSelectedItem();
	double precioVentaFinal = precioVentaSD * (1 - descuento.getPorcentaje());

	producto.setPrecioVenta(precioVentaFinal);
	producto.setGanancia((Ganancia) vista.cboGanancia.getSelectedItem());
	producto.setCategoria((Categoria) vista.cboCategoria.getSelectedItem());
	producto.setDescuento((Descuento) vista.cboDescuento.getSelectedItem());
	producto.setGenero((Genero) vista.cboGenero.getSelectedItem());
	producto.setMarca((Marca) vista.cboMarca.getSelectedItem());
	producto.setTalla((Talla) vista.cboTalla.getSelectedItem());
    }

    public void getProveedor(String RUC) {
	try {
	    ResultSet rs = consulta.select("SELECT * FROM PROVEEDORES WHERE RUC=" + RUC);
	    while (rs.next()) {
		proveedor = new Proveedor(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
	    }
	    vista.txtNombreProveedor.setText(proveedor.getNombre() + " - " + proveedor.getRUC());
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

    public void registrarProveedor() {
	JDialog vac = new JDialog(vista, true);
	panelProveedor = new PanelAgregarProveedor();
	vac.add(panelProveedor);
	vac.setSize(470, 290);
	vac.setResizable(false);
	vac.setLocationRelativeTo(null);
	vac.setVisible(true);
    }

    public void llenarProvedor() {
	try {
	    proveedor = new Proveedor();
	    CallableStatement cstmt = consulta.getConexion().prepareCall("{call buscarProveedor(?, ?)}");
	    cstmt.setString(1, vista.txtBuscarProveedor.getText());
	    cstmt.registerOutParameter(2, java.sql.Types.BIT);

	    cstmt.execute();
	    boolean isValido = cstmt.getBoolean(2);
	    if (isValido) {
		getProveedor(vista.txtBuscarProveedor.getText());
	    } else {
		JOptionPane.showMessageDialog(null, "Proveedor no encontrado", "Advertencia", JOptionPane.WARNING_MESSAGE);
		registrarProveedor();
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
    
    public ArrayList<DefaultComboBoxModel> getListaFecha() {
	ArrayList<DefaultComboBoxModel> arregloDCM = new ArrayList(3);
	int year = Year.now().getValue();
	DefaultComboBoxModel auxAnio = new DefaultComboBoxModel();
	DefaultComboBoxModel auxMes = new DefaultComboBoxModel();
	DefaultComboBoxModel auxDia = new DefaultComboBoxModel();
	
	auxAnio.addElement(new Anio(year));
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
    
    public void llenarSuministro() {
	suministro = new Suministro();
	suministro.setProveedor(proveedor);
	suministro.setCantidad(Integer.parseInt(vista.txtCantidad.getText()));
	suministro.setPrecioUnidad(Double.parseDouble(vista.txtPrecioCompra.getText()));
	suministro.setCantidad(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == vista.btnAgregarCajero) {
	    JDialog vac = new JDialog(vista, true);
	    PanelAgergarTrabajador panel = new PanelAgergarTrabajador();

	    CRUD crud = new CRUD();
	    ControllerPanelAgregarTrabajdor c = new ControllerPanelAgregarTrabajdor(panel, crud);

	    vac.add(panel);
	    vac.setSize(470, 630);
	    vac.setResizable(false);
	    vac.setLocationRelativeTo(null);
	    vac.setVisible(true);
	}
	if (e.getSource() == vista.btnTemaClaro) {
	    temaClaro();
	}
	if (e.getSource() == vista.btnTemaOscuro) {
	    temaOscuro();
	}
	if (e.getSource() == vista.btnCerrarSesion) {
	    cerrarSesion();
	}
	
	if (e.getSource() == vista.btnAgregarProducto && vista.cboGanancia.getSelectedIndex() != -1 && vista.cboCategoria.getSelectedIndex() != -1
		&& vista.cboDescuento.getSelectedIndex() != -1 && vista.cboGenero.getSelectedIndex() != -1 && vista.cboMarca.getSelectedIndex() != -1
		&& vista.cboTalla.getSelectedIndex() != -1) {

	    llenarProducto();
	    llenarSuministro();
	    consulta.insert("INSERT INTO PRODUCTOS VALUES ("
	    );
	} 
//	else {
//	    JOptionPane.showMessageDialog(null, "Datos insuficientes", "Advertencia", JOptionPane.WARNING_MESSAGE);
//	}
	if (e.getSource() == this.panelProveedor.btnAgregarProveedor) {
	    proveedor = new Proveedor();
	    proveedor.setRUC(this.panelProveedor.txtRuc.getText());
	    proveedor.setNombre(this.panelProveedor.txtNombre.getText());
	    proveedor.setCorreo(this.panelProveedor.txtCorreo.getText());
	    proveedor.setTelefono(this.panelProveedor.txtTelefono.getText());

	    consulta.insert("INSERT INTO PROVEEDORES VALUES("
		    + "'" + proveedor.getRUC() + "',"
		    + "'" + proveedor.getNombre() + "',"
		    + "'" + proveedor.getCorreo() + "',"
		    + "'" + proveedor.getTelefono() + "')"
	    );
	}
	if (e.getSource() == vista.btnBuscarProveedor){
	    llenarProvedor();
	}
    }
}
