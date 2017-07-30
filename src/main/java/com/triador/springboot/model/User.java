package com.triador.springboot.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="users")
public class User implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;


	@NotNull
	@Column(name = "firstname", nullable = false, length = 30)
	private String firstname;

	@NotNull
	@Column(name = "lastname", nullable = false, length = 30)
	private String lastname;

	@NotNull
	@Column(name = "username", nullable = false, unique = true, length = 30)
	private String username;

	@NotNull
	@Column(name = "password", nullable = false, length = 50)
	private String password;

	@Column(name = "email", unique = true, length = 30)
	private String email;

	@Temporal(TemporalType.DATE)
	@Column(name = "birthday")
	private Date birthday;

	@Column(name = "isActive", nullable = false)
	private Boolean isActive;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createdTimestamp")
	private Date createdTimestamp;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastUpdatedTimestamp")
	private Date lastUpdatedTimestamp;

	@Column(name = "party", nullable = false, length = 30)
	private String party;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "address_id", nullable = false)
	private Address address;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Boolean getActive() {
		return isActive;
	}

	public void setActive(Boolean active) {
		isActive = active;
	}

	public Date getCreatedTimestamp() {
		return createdTimestamp;
	}

	public void setCreatedTimestamp(Date createdTimestamp) {
		this.createdTimestamp = createdTimestamp;
	}

	public Date getLastUpdatedTimestamp() {
		return lastUpdatedTimestamp;
	}

	public void setLastUpdatedTimestamp(Date lastUpdatedTimestamp) {
		this.lastUpdatedTimestamp = lastUpdatedTimestamp;
	}

	public String getParty() {
		return party;
	}

	public void setParty(String party) {
		this.party = party;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Address getAddress() {
		return address;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != null ? !id.equals(user.id) : user.id != null) return false;
		if (firstname != null ? !firstname.equals(user.firstname) : user.firstname != null) return false;
		if (lastname != null ? !lastname.equals(user.lastname) : user.lastname != null) return false;
		if (username != null ? !username.equals(user.username) : user.username != null) return false;
		if (password != null ? !password.equals(user.password) : user.password != null) return false;
		if (email != null ? !email.equals(user.email) : user.email != null) return false;
		if (birthday != null ? !birthday.equals(user.birthday) : user.birthday != null) return false;
		if (isActive != null ? !isActive.equals(user.isActive) : user.isActive != null) return false;
		if (createdTimestamp != null ? !createdTimestamp.equals(user.createdTimestamp) : user.createdTimestamp != null)
			return false;
		if (lastUpdatedTimestamp != null ? !lastUpdatedTimestamp.equals(user.lastUpdatedTimestamp) : user.lastUpdatedTimestamp != null)
			return false;
		if (party != null ? !party.equals(user.party) : user.party != null) return false;
		return address != null ? address.equals(user.address) : user.address == null;
	}

	@Override
	public int hashCode() {
		int result = id != null ? id.hashCode() : 0;
		result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
		result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
		result = 31 * result + (username != null ? username.hashCode() : 0);
		result = 31 * result + (password != null ? password.hashCode() : 0);
		result = 31 * result + (email != null ? email.hashCode() : 0);
		result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
		result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
		result = 31 * result + (createdTimestamp != null ? createdTimestamp.hashCode() : 0);
		result = 31 * result + (lastUpdatedTimestamp != null ? lastUpdatedTimestamp.hashCode() : 0);
		result = 31 * result + (party != null ? party.hashCode() : 0);
		result = 31 * result + (address != null ? address.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", firstname='" + firstname + '\'' +
				", lastname='" + lastname + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", email='" + email + '\'' +
				", birthday=" + birthday +
				", isActive=" + isActive +
				", createdTimestamp=" + createdTimestamp +
				", lastUpdatedTimestamp=" + lastUpdatedTimestamp +
				", party='" + party + '\'' +
				", address=" + address +
				'}';
	}

}