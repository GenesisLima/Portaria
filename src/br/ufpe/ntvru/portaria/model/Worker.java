package br.ufpe.ntvru.portaria.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="p_worker")
public class Worker {

	
	   @Id
	   @SequenceGenerator(name="pk_seq_worker",sequenceName="p_seq_worker",allocationSize=1)
	   @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_seq_worker")
	   @Column(name="id_worker",unique=true,nullable=false)
	   private int id;
	   
	   @Basic(optional=false)
	   private String name;
	   private Department department;
	   private String phone;
	   private String additionalInfo;
	   @Column(columnDefinition="char(1) default 'A'")
		private String status = "A";
	   
	   
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
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	   
	   
	   
	   
	   
	   
	   
	
	
	
}
