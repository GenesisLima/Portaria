package br.ufpe.ntvru.portaria.model;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity(name="p_user")
public class User {

   @Id
   @SequenceGenerator(name="pk_seq_user",sequenceName="p_seq_user",allocationSize=1)
   @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="pk_seq_user")
   @Column(name="id_user",unique=true,nullable=false)
   private int id;
   
   @Column(name="password")
   @Basic(optional=false)
   private String password;

   @Column(name="username")
   @Basic(optional=false)
   private String username;  
	    
   @Column(columnDefinition="char(1) default 'A'")
	private String status = "A";

	    public String getUsername() {
	        return username;
	    }

	    public void setUsername(String username) {
	        this.username = username;
	    }

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + id;
			result = prime * result + ((password == null) ? 0 : password.hashCode());
			result = prime * result + ((username == null) ? 0 : username.hashCode());
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
			User other = (User) obj;
			if (id != other.id)
				return false;
			if (password == null) {
				if (other.password != null)
					return false;
			} else if (!password.equals(other.password))
				return false;
			if (username == null) {
				if (other.username != null)
					return false;
			} else if (!username.equals(other.username))
				return false;
			return true;
		}
	   
	
	    
}
