package view;

public class FrmAdmin extends javax.swing.JFrame {
    public FrmAdmin() {
        initComponents();
	this.setLocationRelativeTo(null);
	this.setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEjecutarInsert = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        btnEjecutarSelect = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnEjecutarUpdate = new javax.swing.JButton();
        btnEjecutarDelete = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        lblSupervisor = new javax.swing.JLabel();
        cboSupervisor = new javax.swing.JComboBox<>();
        jSeparator3 = new javax.swing.JSeparator();
        cboCargo = new javax.swing.JComboBox<>();
        panelAgergarTrabajador = new view.PanelAgergarTrabajador();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        btnCerrarSesion = new javax.swing.JButton();
        btnTemaClaro = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnTemaOscuro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel3.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Insert");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(20, 20, 80, 30);

        btnEjecutarInsert.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEjecutarInsert.setText("Execute");
        jPanel3.add(btnEjecutarInsert);
        btnEjecutarInsert.setBounds(440, 190, 100, 30);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(630, 60, 550, 110);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("Select");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(630, 20, 110, 30);

        btnEjecutarSelect.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEjecutarSelect.setText("Execute");
        jPanel3.add(btnEjecutarSelect);
        btnEjecutarSelect.setBounds(1080, 190, 100, 30);

        jTextArea2.setColumns(20);
        jTextArea2.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextArea2.setRows(5);
        jScrollPane2.setViewportView(jTextArea2);

        jPanel3.add(jScrollPane2);
        jScrollPane2.setBounds(20, 60, 550, 110);

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jPanel3.add(jScrollPane3);
        jScrollPane3.setBounds(20, 460, 1160, 190);

        jTextArea4.setColumns(20);
        jTextArea4.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextArea4.setRows(5);
        jScrollPane4.setViewportView(jTextArea4);

        jPanel3.add(jScrollPane4);
        jScrollPane4.setBounds(630, 270, 550, 110);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Delete");
        jPanel3.add(jLabel3);
        jLabel3.setBounds(630, 230, 80, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Results");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(20, 420, 80, 30);

        btnEjecutarUpdate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEjecutarUpdate.setText("Execute");
        jPanel3.add(btnEjecutarUpdate);
        btnEjecutarUpdate.setBounds(440, 400, 100, 30);

        btnEjecutarDelete.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEjecutarDelete.setText("Execute");
        jPanel3.add(btnEjecutarDelete);
        btnEjecutarDelete.setBounds(1080, 400, 100, 30);

        jTextArea5.setColumns(20);
        jTextArea5.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextArea5.setRows(5);
        jScrollPane5.setViewportView(jTextArea5);

        jPanel3.add(jScrollPane5);
        jScrollPane5.setBounds(20, 270, 550, 110);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Update");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(20, 230, 80, 30);

        jTabbedPane1.addTab("Consutlas SQL", jPanel3);

        jPanel4.setLayout(null);

        lblSupervisor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblSupervisor.setText("Asignado a");
        jPanel4.add(lblSupervisor);
        lblSupervisor.setBounds(160, 580, 70, 26);

        cboSupervisor.setToolTipText("");
        jPanel4.add(cboSupervisor);
        cboSupervisor.setBounds(240, 580, 200, 26);

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel4.add(jSeparator3);
        jSeparator3.setBounds(490, 30, 10, 610);

        jPanel4.add(cboCargo);
        cboCargo.setBounds(110, 30, 100, 26);
        jPanel4.add(panelAgergarTrabajador);
        panelAgergarTrabajador.setBounds(10, 20, 450, 610);

        tablaProductos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ));
        tablaProductos.setRowHeight(30);
        jScrollPane6.setViewportView(tablaProductos);

        jPanel4.add(jScrollPane6);
        jScrollPane6.setBounds(530, 30, 650, 620);

        jTabbedPane1.addTab("Registrar trabajadores", jPanel4);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(60, 20, 1200, 700);

        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/encender_1.png"))); // NOI18N
        getContentPane().add(btnCerrarSesion);
        btnCerrarSesion.setBounds(10, 20, 30, 30);

        btnTemaClaro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dom.png"))); // NOI18N
        getContentPane().add(btnTemaClaro);
        btnTemaClaro.setBounds(10, 60, 30, 30);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(50, 20, 10, 680);

        btnTemaOscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/media-luna.png"))); // NOI18N
        getContentPane().add(btnTemaOscuro);
        btnTemaOscuro.setBounds(10, 100, 30, 30);

        setBounds(0, 0, 1296, 759);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnCerrarSesion;
    public javax.swing.JButton btnEjecutarDelete;
    public javax.swing.JButton btnEjecutarInsert;
    public javax.swing.JButton btnEjecutarSelect;
    public javax.swing.JButton btnEjecutarUpdate;
    public javax.swing.JButton btnTemaClaro;
    public javax.swing.JButton btnTemaOscuro;
    public javax.swing.JComboBox<String> cboCargo;
    public javax.swing.JComboBox<String> cboSupervisor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTextArea jTextArea1;
    public javax.swing.JTextArea jTextArea2;
    public javax.swing.JTextArea jTextArea3;
    public javax.swing.JTextArea jTextArea4;
    public javax.swing.JTextArea jTextArea5;
    public javax.swing.JLabel lblSupervisor;
    public view.PanelAgergarTrabajador panelAgergarTrabajador;
    public javax.swing.JTable tablaProductos;
    // End of variables declaration//GEN-END:variables
}
