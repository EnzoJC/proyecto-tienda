package view;

public class FrmSupervisor extends javax.swing.JFrame {
    
    public FrmSupervisor() {
        initComponents();
        this.setLocationRelativeTo(null);
	this.setResizable(false);
        btnCerrarSesion.setToolTipText("Cerrar sesión");
        btnTemaClaro.setToolTipText("Cambiar a tema claro");
        btnTemaOscuro.setToolTipText("Cambiar a tema oscuro");
        btnAgregarCajero.setToolTipText("Agregar nuevo cajero");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregarCajero = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnTemaClaro = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnTemaOscuro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        btnAgregarCajero.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/usuario_mini.png"))); // NOI18N
        getContentPane().add(btnAgregarCajero);
        btnAgregarCajero.setBounds(10, 140, 30, 30);

        btnCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/encender_1.png"))); // NOI18N
        getContentPane().add(btnCerrarSesion);
        btnCerrarSesion.setBounds(10, 20, 30, 30);

        btnTemaClaro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/dom.png"))); // NOI18N
        getContentPane().add(btnTemaClaro);
        btnTemaClaro.setBounds(10, 60, 30, 30);

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(50, 20, 10, 680);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel3.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Código");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(20, 20, 60, 31);

        txtCodigo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(txtCodigo);
        txtCodigo.setBounds(110, 20, 200, 31);

        btnBuscar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnBuscar.setText("Buscar");
        jPanel3.add(btnBuscar);
        btnBuscar.setBounds(330, 20, 100, 31);

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jTable1.setRowHeight(30);
        jScrollPane1.setViewportView(jTable1);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(10, 70, 1180, 540);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1.setText("Imprimir");
        jPanel3.add(jButton1);
        jButton1.setBounds(10, 630, 90, 26);

        jTabbedPane1.addTab("Kardex", jPanel3);

        jPanel4.setLayout(null);
        jTabbedPane1.addTab("Cajas", jPanel4);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(60, 10, 1200, 700);

        btnTemaOscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/media-luna.png"))); // NOI18N
        getContentPane().add(btnTemaOscuro);
        btnTemaOscuro.setBounds(10, 100, 30, 30);

        setBounds(0, 0, 1296, 759);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarCajero;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnCerrarSesion;
    public javax.swing.JButton btnTemaClaro;
    public javax.swing.JButton btnTemaOscuro;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    public javax.swing.JTextField txtCodigo;
    // End of variables declaration//GEN-END:variables
}
