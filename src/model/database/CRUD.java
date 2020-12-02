package model.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class CRUD extends Conexion {

    Statement stmt = null;
    ResultSet rs = null;

    public boolean insert(String query) {
        Connection conexion = getConexion();
        try {
            return stmt.executeUpdate(query) > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en la inserción:\n" + ex);
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error:\n" + ex);
            }
        }
	return false;
    }
    
    public ResultSet select(String query) {
        Connection conexion = getConexion();
        try {
            stmt = conexion.createStatement();
            rs = conexion.createStatement().executeQuery(query);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:\n" + ex);
        }
        return rs;
    }

    public boolean update(String query) {
	return this.insert(query);
    }

    public boolean delete(String query) {
	return this.insert(query);
    }
}
