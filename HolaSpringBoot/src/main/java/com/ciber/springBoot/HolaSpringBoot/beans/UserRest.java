/**
 * 
 */
package com.ciber.springBoot.HolaSpringBoot.beans;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author ciber
 *
 */
public class UserRest {
	@JsonIgnore
	@Id
	private String _id;
	@JsonIgnore
	private String _class;
	private String nombre;
	private String apellidos;

	
	public UserRest() {

	}
	
	
	/**
	 * @return the _id
	 */
	public String get_id() {
		return _id;
	}
	/**
	 * @param _id the _id to set
	 */
	public void set_id(String _id) {
		this._id = _id;
	}
	/**
	 * @return the _class
	 */
	public String get_class() {
		return _class;
	}
	/**
	 * @param _class the _class to set
	 */
	public void set_class(String _class) {
		this._class = _class;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	/**
	 * @return the apellidos
	 */
	public String getApellidos() {
		return apellidos;
	}
	/**
	 * @param apellidos the apellidos to set
	 */
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Usuario [_id=" + _id + ", _class=" + _class + ", nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}
	
	

	

}
