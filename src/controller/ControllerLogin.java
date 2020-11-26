package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.swing.JOptionPane;
import model.database.Conexion;
import model.database.Login;
import view.FrmIniciarSesion;
import view.FrmSupervisor;

public class ControllerLogin implements ActionListener {

    private FrmIniciarSesion sesion;
    private Login login;
    private Conexion conexion;

    public ControllerLogin() {
    }

    public ControllerLogin(FrmIniciarSesion sesion, Login login, Conexion conexion) {
        this.sesion = sesion;
        this.login = login;
        this.conexion = conexion;
        sesion.btnLogin.addActionListener(this);
        sesion.txtUser.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
                    intentar();
                    nextForm(sesion.txtUser.getText(), new String(sesion.txtPassword.getPassword()));
                }
            }
        });
        sesion.txtPassword.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == com.sun.glass.events.KeyEvent.VK_ENTER) {
                    intentar();
                    nextForm(sesion.txtUser.getText(), new String(sesion.txtPassword.getPassword()));
                }
            }
        });
    }

    public void intentar() {
        login.setUser(sesion.txtUser.getText());
        login.setPassword(new String(sesion.txtPassword.getPassword()));

        conexion.setConexion(login.getUser(), login.getPassword());
    }

    public void nextForm(String txtUser, String txtPassword) {
//        if (txtUser.compareTo("administrador") == 0 && txtPassword.compareTo("querty123_") == 0) {
        String ruta = System.getProperty("user.dir");
        String salidaTxt = "";
        System.out.println(salidaTxt);
        try {
            FileWriter archivo = new FileWriter(ruta + "/src/others/cadena.txt", true);
            archivo.write(txtPassword);
            archivo.close();
            Process p = Runtime.getRuntime().exec(ruta + "/src/others/automata.exe ");
            InputStream is = p.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String salidaAutomata = br.readLine();

            if (salidaAutomata.compareTo("1") == 0) System.out.print("true\n");
            else System.out.print("false\n");
            
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error:" + e);
        }
//            FrmSupervisor form = new FrmSupervisor();
//            Conexion modelo = new Conexion();
//            ControllerSupervisor controlador = new ControllerSupervisor(form, modelo);
//            sesion.dispose();
//            form.setVisible(true);
//        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == sesion.btnLogin) {
            //intentar();
            nextForm(sesion.txtUser.getText(), new String(sesion.txtPassword.getPassword()));
        }
    }
}
