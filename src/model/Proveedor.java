package model;

public class Proveedor {
    private int id;
    private String RUC;
    private String nombre;
    private String correo;
    private String telefono;

    public Proveedor() {
    }

    public Proveedor(int id, String RUC, String nombre, String correo, String telefono) {
	this.id = id;
	this.RUC = RUC;
	this.nombre = nombre;
	this.correo = correo;
	this.telefono = telefono;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getRUC() {
	return RUC;
    }

    public void setRUC(String RUC) {
	this.RUC = RUC;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getCorreo() {
	return correo;
    }

    public void setCorreo(String correo) {
	this.correo = correo;
    }

    public String getTelefono() {
	return telefono;
    }

    public void setTelefono(String telefono) {
	this.telefono = telefono;
    }

    @Override
    public String toString() {
	return nombre + " - " + RUC;
    }
    
}
