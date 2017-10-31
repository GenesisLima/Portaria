package br.ufpe.ntvru.portaria.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity(name="p_vehicle")
public class Vehicle implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1790147896234906301L;

	@Id
	   @SequenceGenerator(name="pk_seq_vehicle",sequenceName="p_seq_vehicle",allocationSize=1)
	   @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_seq_vehicle")
	   @Column(name="id_vehicle",unique=true,nullable=false)
	private int id;
	
	private String brand;
	
	private String model;
	
	private String vehicleRegistrationPlate;
	@ManyToOne
	@JoinColumn(referencedColumnName="id_visitor")
	private Visitor visitor;
	
	

	public Visitor getVisitor() {
		return visitor;
	}

	public void setVisitor(Visitor visitor) {
		this.visitor = visitor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVehicleRegistrationPlate() {
		return vehicleRegistrationPlate;
	}

	public void setVehicleRegistrationPlate(String vehicleRegistrationPlate) {
		this.vehicleRegistrationPlate = vehicleRegistrationPlate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Vehicle other = (Vehicle) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Vehicle [id=" + id + ", brand=" + brand + ", model=" + model + ", vehicleRegistrationPlate="
				+ vehicleRegistrationPlate + "]";
	}
	
	
	
	
	
	
}
