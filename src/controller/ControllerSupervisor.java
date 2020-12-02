package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import model.Categoria;
import model.Descuento;
import model.Ganancia;
import model.Genero;
import model.Marca;
import model.Producto;
import model.Talla;
import model.Trabajador;
import model.database.CRUD;
import model.database.Login;
import view.FrmLogin;
import view.FrmSupervisor;
import view.PanelAgergarTrabajador;

public class ControllerSupervisor implements ActionListener {

    private FrmSupervisor vista;
    private Trabajador supervisor;
    private CRUD consulta;
    private Producto Producto;
    private DefaultComboBoxModel dcmGanancia;
    private DefaultComboBoxModel dcmCategoria;
    private DefaultComboBoxModel dcmDescuento;
    private DefaultComboBoxModel dcmGenero;
    private DefaultComboBoxModel dcmMarca;
    private DefaultComboBoxModel dcmTalla;

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

	vista.cboGanancia.setModel(getListaGanancia());
	vista.cboCategoria.setModel(getListaCategoria());
	vista.cboDescuento.setModel(getListaDescuento());
	vista.cboGenero.setModel(getListaGenero());
	vista.cboMarca.setModel(getListaMarca());
	vista.cboTalla.setModel(getListaTalla());

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

    @Override
    public void actionPerformed(ActionEvent e) {
	if (e.getSource() == vista.btnAgregarCajero) {
	    JDialog vac = new JDialog(vista, true);
	    PanelAgergarTrabajador panel = new PanelAgergarTrabajador();

	    CRUD crud = new CRUD();
	    ControllerPanelAgregarTrabajdor c = new ControllerPanelAgregarTrabajdor(panel, crud);

	    vac.add(panel);
	    vac.setSize(470, 650);
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
	if (e.getSource() == vista.btnAgregarProducto){
	    
	}

    }
}
