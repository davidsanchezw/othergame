package com.comweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Users implements java.io.Serializable {
	public Users() {
	}

	public Users(String email, String pass, String publicName, String explanation) {
		super();
		this.email = email;
		this.pass = pass;
		this.publicName = publicName;
		this.explanation = explanation;
	}

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String email;
	@Column
	private String pass;
	@Column
	private String publicName;
	@Column
	private String explanation;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPublicName() {
		return publicName;
	}

	public void setPublicName(String publicName) {
		this.publicName = publicName;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "User [explanation=" + explanation + ", email=" + email + ", id=" + id + ", pass=" + pass
				+ ", publicName=" + publicName + "]";
	}

}