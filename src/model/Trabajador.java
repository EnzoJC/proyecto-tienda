package model;

import java.sql.Date;
import java.sql.Timestamp;

public class Trabajador {

    private int idCajero;
    private Trabajador idSupervisor;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String dni;
    private String correo;
    private Date fechaNacimiento;
    private String Telefono;
    private Horario horario;
    private String cargo;
    private String usuario;
    private String password;
    private Timestamp ultimaSesion;

    public Trabajador() {
    }

    public Trabajador(int idCajero, Trabajador idSupervisor, String nombres, String primerApellido, String segundoApellido, 
			String dni, String correo, Date fechaNacimiento, String Telefono, Horario horario, String cargo, 
								String usuario, String password, Timestamp ultimaSesion) {
	this.idCajero = idCajero;
	this.idSupervisor = idSupervisor;
	this.nombres = nombres;
	this.primerApellido = primerApellido;
	this.segundoApellido = segundoApellido;
	this.dni = dni;
	this.correo = correo;
	this.fechaNacimiento = fechaNacimiento;
	this.Telefono = Telefono;
	this.horario = horario;
	this.cargo = cargo;
	this.usuario = usuario;
	this.password = password;
	this.ultimaSesion = ultimaSesion;
    }

    public int getIdCajero() {
	return idCajero;
    }

    public void setIdCajero(int idCajero) {
	this.idCajero = idCajero;
    }

    public Trabajador getIdSupervisor() {
	return idSupervisor;
    }

    public void setIdSupervisor(Trabajador idSupervisor) {
	this.idSupervisor = idSupervisor;
    }

    public String getNombres() {
	return nombres;
    }

    public void setNombres(String nombres) {
	this.nombres = nombres;
    }

    public String getPrimerApellido() {
	return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
	this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
	return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
	this.segundoApellido = segundoApellido;
    }

    public String getDni() {
	return dni;
    }

    public void setDni(String dni) {
	this.dni = dni;
    }

    public String getCorreo() {
	return correo;
    }

    public void setCorreo(String correo) {
	this.correo = correo;
    }

    public Date getFechaNacimiento() {
	return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
	return Telefono;
    }

    public void setTelefono(String Telefono) {
	this.Telefono = Telefono;
    }

    public Horario getHorario() {
	return horario;
    }

    public void setHorario(Horario horario) {
	this.horario = horario;
    }

    public String getCargo() {
	return cargo;
    }

    public void setCargo(String cargo) {
	this.cargo = cargo;
    }

    public String getUsuario() {
	return usuario;
    }

    public void setUsuario(String usuario) {
	this.usuario = usuario;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public Timestamp getUltimaSesion() {
	return ultimaSesion;
    }

    public void setUltimaSesion(Timestamp ultimaSesion) {
	this.ultimaSesion = ultimaSesion;
    }
    
    @Override
    public String toString() {
        return "" + this.idCajero;
    }
}
