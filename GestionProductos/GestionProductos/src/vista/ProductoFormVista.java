package vista;

import modelo.dto.TiposProducto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ProductoFormVista extends JFrame implements IProductoFormVista {

    private JTextField txtId;
    private JTextField txtNombre;
    private JComboBox<TiposProducto> cmbTipo;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JTextField txtProveedor;
    private JSpinner spnDia;
    private JSpinner spnMes;
    private JSpinner spnAnio;
    private JButton btnCrear;
    private JButton btnLimpiar;
    private String accionEjecutada = "";

    public ProductoFormVista() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Gestión de Productos — Formulario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(680, 440);
        setLocationRelativeTo(null);
        setResizable(false);


        txtId        = new JTextField();
        txtNombre    = new JTextField();
        cmbTipo      = new JComboBox<>(TiposProducto.values());
        txtCantidad  = new JTextField();
        txtPrecio    = new JTextField();
        txtProveedor = new JTextField();

        spnDia  = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        spnMes  = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        spnAnio = new JSpinner(new SpinnerNumberModel(LocalDate.now().getYear(), 2000, 2100, 1));
        spnAnio.setEditor(new JSpinner.NumberEditor(spnAnio, "#"));

        JPanel pnlFecha = new JPanel(new GridLayout(1, 6, 4, 0));
        pnlFecha.add(new JLabel("Día")); pnlFecha.add(spnDia);
        pnlFecha.add(new JLabel("Mes")); pnlFecha.add(spnMes);
        pnlFecha.add(new JLabel("Año")); pnlFecha.add(spnAnio);

        JPanel pnlCampos = new JPanel(new GridLayout(7, 2, 6, 6));
        pnlCampos.setBorder(BorderFactory.createEmptyBorder(12, 12, 4, 12));
        pnlCampos.add(new JLabel("ID:"));         pnlCampos.add(txtId);
        pnlCampos.add(new JLabel("Nombre:"));     pnlCampos.add(txtNombre);
        pnlCampos.add(new JLabel("Tipo:"));       pnlCampos.add(cmbTipo);
        pnlCampos.add(new JLabel("Cantidad:"));   pnlCampos.add(txtCantidad);
        pnlCampos.add(new JLabel("Precio:"));     pnlCampos.add(txtPrecio);
        pnlCampos.add(new JLabel("Proveedor:"));  pnlCampos.add(txtProveedor);
        pnlCampos.add(new JLabel("Caducidad:"));  pnlCampos.add(pnlFecha);
        add(pnlCampos, BorderLayout.CENTER);

        btnCrear   = boton("Crear");
        btnLimpiar = boton("Limpiar");

        JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 8, 8));
        pnlBotones.add(btnCrear);
        pnlBotones.add(btnLimpiar);
        add(pnlBotones, BorderLayout.SOUTH);

        btnCrear.addActionListener(e   -> accionEjecutada = "CREAR");
        btnLimpiar.addActionListener(e -> limpiarCampos());
    }

    private JButton boton(String texto) {
        JButton b = new JButton(texto);

        return b;
    }

    @Override public int getId() {
        try { return Integer.parseInt(txtId.getText().trim()); } catch (NumberFormatException e) { return 0; }
    }
    @Override public String getNombre()       { return txtNombre.getText().trim(); }
    @Override public TiposProducto getTipo()  { return (TiposProducto) cmbTipo.getSelectedItem(); }
    @Override public int getCantidad() {
        try { return Integer.parseInt(txtCantidad.getText().trim()); } catch (NumberFormatException e) { return 0; }
    }
    @Override public double getPrecio() {
        try { return Double.parseDouble(txtPrecio.getText().trim()); } catch (NumberFormatException e) { return 0.0; }
    }
    @Override public String getProveedor()    { return txtProveedor.getText().trim(); }

    @Override
    public LocalDate getFechaCaducidad() {
        try {
            return LocalDate.of((int) spnAnio.getValue(), (int) spnMes.getValue(), (int) spnDia.getValue());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Fecha inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    @Override
    public void limpiarCampos() {
        txtId.setText(""); txtNombre.setText(""); cmbTipo.setSelectedIndex(0);
        txtCantidad.setText(""); txtPrecio.setText(""); txtProveedor.setText("");
        spnDia.setValue(1); spnMes.setValue(1); spnAnio.setValue(LocalDate.now().getYear());
        accionEjecutada = "";
    }

    @Override
    public void agregarListener(ActionListener listener) {
        btnCrear.addActionListener(listener);
    }

    @Override public String getAccionEjecutada() { return accionEjecutada; }
}
