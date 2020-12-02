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
	this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnAgregarCajero = new javax.swing.JButton();
        btnCerrarSesion = new javax.swing.JButton();
        btnTemaClaro = new javax.swing.JButton();
        btnTemaOscuro = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtCodigo = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtStock = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtPrecioVenta = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cboGenero = new javax.swing.JComboBox<>();
        cboGanancia = new javax.swing.JComboBox<>();
        cboDescuento = new javax.swing.JComboBox<>();
        cboCategoria = new javax.swing.JComboBox<>();
        cboMarca = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        cboTalla = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtStock1 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtNombre1 = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        btnAgregarProducto = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        txtNombreProveedor = new javax.swing.JTextField();
        jScrollPane6 = new javax.swing.JScrollPane();
        tablaProductos = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();

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

        btnTemaOscuro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/media-luna.png"))); // NOI18N
        getContentPane().add(btnTemaOscuro);
        btnTemaOscuro.setBounds(10, 100, 30, 30);

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

        jPanel1.setLayout(null);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Cantidad");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(20, 90, 140, 26);

        txtStock.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtStock);
        txtStock.setBounds(170, 530, 180, 26);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setText("Ganancia");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(20, 130, 140, 26);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("Precio de venta");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(20, 170, 140, 26);

        txtPrecioVenta.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtPrecioVenta);
        txtPrecioVenta.setBounds(170, 170, 260, 26);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel10.setText("Categoria");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(20, 210, 140, 26);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel11.setText("Descuento");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(20, 250, 140, 26);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel12.setText("Género");
        jPanel1.add(jLabel12);
        jLabel12.setBounds(20, 290, 140, 26);

        jPanel1.add(cboGenero);
        cboGenero.setBounds(170, 290, 260, 26);

        jPanel1.add(cboGanancia);
        cboGanancia.setBounds(170, 130, 260, 26);

        jPanel1.add(cboDescuento);
        cboDescuento.setBounds(170, 250, 260, 26);

        jPanel1.add(cboCategoria);
        cboCategoria.setBounds(170, 210, 260, 26);

        jPanel1.add(cboMarca);
        cboMarca.setBounds(170, 330, 260, 26);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel13.setText("Marca");
        jPanel1.add(jLabel13);
        jLabel13.setBounds(20, 330, 140, 26);

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel14.setText("Buscar proveedor");
        jPanel1.add(jLabel14);
        jLabel14.setBounds(20, 530, 130, 26);

        jPanel1.add(cboTalla);
        cboTalla.setBounds(170, 370, 260, 26);

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel15.setText("Datos del Producto");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(20, 20, 140, 20);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel16.setText("Talla");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(20, 370, 140, 26);

        txtStock1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtStock1);
        txtStock1.setBounds(170, 90, 260, 26);

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel17.setText("Nombre");
        jPanel1.add(jLabel17);
        jLabel17.setBounds(20, 50, 140, 26);

        txtNombre1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtNombre1);
        txtNombre1.setBounds(170, 50, 260, 26);

        jButton3.setText("Buscar");
        jPanel1.add(jButton3);
        jButton3.setBounds(360, 530, 72, 26);

        btnAgregarProducto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnAgregarProducto.setText("Agregar");
        jPanel1.add(btnAgregarProducto);
        btnAgregarProducto.setBounds(20, 620, 79, 26);

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel18.setText("Nombre");
        jPanel1.add(jLabel18);
        jLabel18.setBounds(20, 570, 140, 26);

        txtNombreProveedor.setEditable(false);
        txtNombreProveedor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPanel1.add(txtNombreProveedor);
        txtNombreProveedor.setBounds(170, 570, 260, 26);

        tablaProductos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tablaProductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaProductos.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        tablaProductos.setRowHeight(30);
        jScrollPane6.setViewportView(tablaProductos);

        jPanel1.add(jScrollPane6);
        jScrollPane6.setBounds(530, 30, 650, 620);

        jTabbedPane1.addTab("Registro de Productos", jPanel1);

        jPanel4.setLayout(null);
        jTabbedPane1.addTab("Cajas", jPanel4);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(60, 10, 1200, 700);

        setBounds(0, 0, 1296, 759);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnAgregarCajero;
    public javax.swing.JButton btnAgregarProducto;
    public javax.swing.JButton btnBuscar;
    public javax.swing.JButton btnCerrarSesion;
    public javax.swing.JButton btnTemaClaro;
    public javax.swing.JButton btnTemaOscuro;
    public javax.swing.JComboBox<String> cboCategoria;
    public javax.swing.JComboBox<String> cboDescuento;
    public javax.swing.JComboBox<String> cboGanancia;
    public javax.swing.JComboBox<String> cboGenero;
    public javax.swing.JComboBox<String> cboMarca;
    public javax.swing.JComboBox<String> cboTalla;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    public javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    public javax.swing.JTable tablaProductos;
    public javax.swing.JTextField txtCodigo;
    public javax.swing.JTextField txtNombre1;
    public javax.swing.JTextField txtNombreProveedor;
    public javax.swing.JTextField txtPrecioVenta;
    public javax.swing.JTextField txtStock;
    public javax.swing.JTextField txtStock1;
    // End of variables declaration//GEN-END:variables
}
