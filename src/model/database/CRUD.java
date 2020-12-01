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
            int resultado = stmt.executeUpdate(query);
            return resultado > 0;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error:\n" + ex);
            return false;
        } finally {
            try {
                conexion.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error:\n" + ex);
            }
        }
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
        return false;
    }

    public boolean delete(String query) {
        return false;
    }
}
