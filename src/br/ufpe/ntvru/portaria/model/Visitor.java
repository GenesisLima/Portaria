package br.ufpe.ntvru.portaria.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity(name="p_visitor")
public class Visitor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6421214647194045137L;

	@Id
	   @SequenceGenerator(name="pk_seq_visitor",sequenceName="p_seq_visitor",allocationSize=1)
	   @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_seq_visitor")
	   @Column(name="id_visitor",unique=true,nullable=false)
	private int id;
	
	private String name;
	
	private String cpf;
	
	private String product;
	
	private String accountable;
	
	private String additionalInfo;

	private Department department;
	
	@OneToMany
	private List<Vehicle> vehicles;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getAccountable() {
		return accountable;
	}

	public void setAccountable(String accountable) {
		this.accountable = accountable;
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		Visitor other = (Visitor) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Visitor [id=" + id + ", name=" + name + ", cpf=" + cpf + ", product=" + product + ", accountable="
				+ accountable + ", additionalInfo=" + additionalInfo + ", department=" + department + "]";
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}

	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	
	
	
	
	
}
