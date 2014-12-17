package com.example.osmeuslugares.modelo;

import android.content.ContentValues;
import android.os.Bundle;

public class Categoria {
	private Long id;
	private String nombre;
	private String icon;
	
	/* Mapeo BBDD */
	// Campos Base de Datos Tabla Lugar
	public static final String C_ID = "cat_id";
	public static final String C_NOMBRE = "cat_nombre";
	public static final String C_ICON = "cat_icon";

	public Categoria(Long id, String nombre, String icon) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.icon = icon;
	}
	
	
	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}


	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}


	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * 
	 */
	public Categoria() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * @param id
	 * @param nombre
	 */



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}
	/**
	 * @param nombre the Nombre to set
	 */
	public void setCategoria(String nombre) {
		this.nombre = nombre;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nombre=" + nombre + ", icon=" + icon
				+ "]";
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		if (o instanceof Categoria) {
			Categoria tmpCategoria = (Categoria)o;
			if (getId()==tmpCategoria.getId()) {
				return true;
			} else{
				return false;
			}
		}
		return false;
	}

	/*
	public static final String C_ID = "cat_id";
	public static final String C_NOMBRE = "cat_nombre";
	public static final String C_ICON = "cat_icon";
	 * */
	
	public ContentValues getContentValues() {
		ContentValues reg = new ContentValues();
		reg.put(C_NOMBRE, nombre); 
		reg.put(C_ID, id);		  
		reg.put(C_ICON, icon);
		return reg;
	}
	
	public Bundle getBundle() {
		Bundle bundle = new Bundle();
		bundle.putLong(C_ID, id);
		bundle.putString(C_NOMBRE, nombre);
		bundle.putString(C_ICON, icon);
		return bundle;
	}
	
	void setBundle(Bundle bundle) {
		id=bundle.getLong(C_ID);
		nombre=bundle.getString(C_NOMBRE);
		icon=bundle.getString(C_ICON);
	}	

	
}