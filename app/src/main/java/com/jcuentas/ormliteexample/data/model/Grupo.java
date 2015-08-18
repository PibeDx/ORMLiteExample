package com.jcuentas.ormliteexample.data.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Grupo {

	public static final String ID = "_idGrupo";
	public static final String NOMBRE = "deNombre";

	@DatabaseField(generatedId = true, columnName = ID)
	private int id;
	@DatabaseField(columnName = NOMBRE)
	private String nombre;
	
	
	public Grupo() {
		super();
	}

	public Grupo(int id, String nombre) {
		super();
		this.id = id;
		this.nombre = nombre;
	}

	public int getId() {
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

}