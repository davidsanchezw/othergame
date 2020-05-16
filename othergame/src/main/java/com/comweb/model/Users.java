package com.comweb.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Users implements java.io.Serializable {
	public Users() {
	}

	public Users(int id, String publicName, String explanation) {
		super();
		this.id = id;
		this.publicName = publicName;
		this.explanation = explanation;
	}

	public Users(String email, String salt, String pass, String publicName, String explanation) {
		super();
		this.email = email;
		this.salt = salt;
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
	@Column(unique = true)
	private String email;
	@Column
	private String salt;
	@Column
	private String pass;
	@Column(unique = true)
	private String publicName;
	@Column
	private String explanation;

	@OneToMany(mappedBy = "user")
	private List<Ads> ads = new ArrayList<>();

	@OneToMany(mappedBy = "usr1")
	private List<Matches> matchesFirst = new ArrayList<>();

	@OneToMany(mappedBy = "usr2")
	private List<Matches> matchesSecond = new ArrayList<>();

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSalt() {
		return salt;
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

	public List<Ads> getAds() {
		return ads;
	}

	public List<Matches> getMatchesFirst() {
		return matchesFirst;
	}

	public List<Matches> getMatchesSecond() {
		return matchesSecond;
	}
}