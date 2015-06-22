package com.miweb.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "nota")
public class Nota {
	private int id;
	private String descripcion;
	
	public Nota() {
		super();
		id = 0;
		descripcion = "";
	}
	
	public Nota(String descripcion) {
		super();
		this.descripcion = descripcion;
	}

	public Nota(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	@XmlElement
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	
}
