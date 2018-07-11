package model;

import java.util.concurrent.atomic.AtomicInteger;

/*
 * @autor: Daniel
 * @ web:  
 */

public class Estudiante {
	private int id;
	private String nombre;
	private String alias;
	private String apellidos;
	private String fechaNacimiento;
	private String telefono;
	private String email;
	
	private static final AtomicInteger contador = new AtomicInteger(100);
	
	public Estudiante(){
	}

	public Estudiante(int id, String nombre, String alias, String apellidos, String fechaNacimiento, String telefono, String email) {
		this.id = id;
		this.nombre = nombre;
		this.alias = alias;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.email = email;
	}

	public Estudiante(String nombre, String alias, String apellidos, String fechaNacimiento, String telefono, String email) {
		this.id = contador.incrementAndGet();
		this.nombre = nombre;
		this.alias = alias;
		this.apellidos = apellidos;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.email = email;
		System.out.println("id: "+ id);
	}

	public static AtomicInteger getContador() {
		return contador;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}