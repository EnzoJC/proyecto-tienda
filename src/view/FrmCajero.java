package view;

public class FrmCajero extends javax.swing.JFrame {

    public FrmCajero() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        btnCerrarSesion.setToolTipText("Cerrar sesión");
        btnTemaClaro.setToolTipText("Cambiar a tema claro");
        btnConfiguracion.setToolTipText("Cambiar a tema oscuro");
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        txtCodigo = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        txtRecibido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        lblCambio = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        lblIgv = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lblImporte = new javax.swing.JLabel();
        btnProcesar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        btnConfiguracion = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnTemaClaro = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnTemaOscuro = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Caja");
        getContentPane().setLayout(null);

        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanel3.setLayout(null);

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
        jScrollPane1.setViewportView(tablaProductos);

        jPanel3.add(jScrollPane1);
        jScrollPane1.setBounds(370, 80, 810, 570);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel1.setText("Código");
        jPanel3.add(jLabel1);
        jLabel1.setBounds(370, 30, 60, 30);

        btnEliminar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnEliminar.setText("Eliminar");
        jPanel3.add(btnEliminar);
        btnEliminar.setBounds(1080, 30, 100, 30);

        txtCodigo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jPanel3.add(txtCodigo);
        txtCodigo.setBounds(440, 30, 200, 31);

        btnAgregar.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        btnAgregar.setText("Agregar");
        jPanel3.add(btnAgregar);
        btnAgregar.setBounds(660, 30, 100, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel2.setText("Caja ");
        jPanel3.add(jLabel2);
        jLabel2.setBounds(30, 10, 300, 50);

        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(209, 209, 209)));
        jPanel2.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Total a pagar");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(20, 190, 149, 32);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("Recibido");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(20, 280, 92, 32);

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("S/ 0.00");
        jPanel2.add(lblTotal);
        lblTotal.setBounds(130, 240, 149, 32);

        txtRecibido.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        txtRecibido.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel2.add(txtRecibido);
        txtRecibido.setBounds(20, 330, 260, 30);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Cambio");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(20, 380, 149, 32);

        lblCambio.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblCambio.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCambio.setText("S/ 0.00");
        jPanel2.add(lblCambio);
        lblCambio.setBounds(130, 420, 149, 32);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setText("IGV");
        jPanel2.add(jLabel8);
        jLabel8.setBounds(20, 10, 149, 32);

        lblIgv.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblIgv.setText("S/ 0.00");
        jPanel2.add(lblIgv);
        lblIgv.setBounds(130, 50, 149, 32);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel10.setText("Importe");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(20, 100, 149, 32);

        lblImporte.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lblImporte.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblImporte.setText("S/ 0.00");
        jPanel2.add(lblImporte);
        lblImporte.setBounds(130, 140, 149, 32);

        btnProcesar.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnProcesar.setText("Procesar");
        jPanel2.add(btnProcesar);
        btnProcesar.setBounds(70, 500, 150, 40);

        jPanel3.add(jPanel2);
        jPanel2.setBounds(30, 80, 300, 570);

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel3.add(jSeparator1);
        jSeparator1.setBounds(350, 80, 30, 570);

        jTabbedPane1.addTab("Caja", jPanel3);

        jPanel4.setLayout(null);

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(30);
        jScrollPane2.setViewportView(jTable1);

        jPanel4.add(jScrollPane2);
        jScrollPane2.setBounds(20, 20, 1200, 590);

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jButton1.setText("Imprimir reporte");
        jPanel4.add(jButton1);
        jButton1.setBounds(20, 630, 170, 31);

        jTabbedPane1.addTab("Ingresos de caja", jPanel4);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(60, 10, 1200, 700);

        btnConfiguracion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/configuraciones.png"))); // NOI18N
        getContentPane().add(btnConfiguracion);
        btnConfiguracion.setBounds(10, 140, 30, 30);

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
    public javax.swing.JButton btnAgregar;
    public javax.swing.JButton btnCerrarSesion;
    public javax.swing.JButton btnConfiguracion;
    public javax.swing.JButton btnEliminar;
    public javax.swing.JButton btnProcesar;
    public javax.swing.JButton btnTemaClaro;
    public javax.swing.JButton btnTemaOscuro;
    public javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    public javax.swing.JTable jTable1;
    public javax.swing.JLabel lblCambio;
    public javax.swing.JLabel lblIgv;
    public javax.swing.JLabel lblImporte;
    public javax.swing.JLabel lblTotal;
    public javax.swing.JTable tablaProductos;
    public javax.swing.JTextField txtCodigo;
    public javax.swing.JTextField txtRecibido;
    // End of variables declaration//GEN-END:variables
}
