package ua.kiev.prog;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
	
	@Entity
	@Table(name="users")
	@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
	public class User {
	    @Id
	    @Column(name="id")
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private int id;

	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="group_id")
	    private Group group;

	    @Column(name="login")
	    private String username;

	    @Column(name="password")
	    private String password;
	    
	    @Column(name="email")
	    private String email;

		@Column(name = "password_salt")
	    private String salt;

	    @Column(name="phone")
	    private String phone;
	    
	    @Column(name="attempts", nullable = true)
	    private Integer loginAttempts;
	    
	    @Column(name="authorized")
	    private boolean authorized;
	    
	    @Column(name="registration_date", nullable = true)
	    private String registrationDate;

		public String getRegistrationDate() {
			return registrationDate;
		}

		public void setRegistrationDate(String registrationDate) {
			this.registrationDate = registrationDate;
		}

		public Integer getLoginAttempts() {
			return loginAttempts;
		}

		public void setLoginAttempts(Integer loginAttempts) {
			this.loginAttempts = loginAttempts;
		}

		public boolean isAuthorized() {
			return authorized;
		}

		public void setAuthorized(boolean authorized) {
			this.authorized = authorized;
		}

		public String getSalt() {
			return salt;
		}

		public void setSalt(String salt) {
			this.salt = salt;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public Group getGroup() {
			return group;
		}

		public void setGroup(Group group) {
			this.group = group;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

}
