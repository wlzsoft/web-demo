package com.demo.entity;

import java.io.Serializable;

public class DeviceEntity implements Serializable {

	private static final long serialVersionUID = -2835894113478135470L;

	public Integer id ;
    
	public String displayId;
	
	public String manufacturer;
	
	public String producModel;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDisplayId() {
		return displayId;
	}

	public void setDisplayId(String displayId) {
		this.displayId = displayId;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getProducModel() {
		return producModel;
	}

	public void setProducModel(String producModel) {
		this.producModel = producModel;
	}

}

