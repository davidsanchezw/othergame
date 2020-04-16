package com.comweb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name = "Ads")
public class Ads implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String nameAd;

	@Column
	private String explanation;

	@Column
	private Date datePublished;

	@Column
	private int statusPostNumber;

	@Column
	private Date dateEnd;

	@OneToOne

	@JoinColumn(name = "id")
	private StatusPostTxt statusPostTxt;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNameAd() {
		return nameAd;
	}

	public void setNameAd(String nameAd) {
		this.nameAd = nameAd;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	public String getStatusPostTxt() {
		return statusPostTxt.getTxt();
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
	}

	public int getStatusPostNumber() {
		return statusPostNumber;
	}

	public void setStatusPostNumber(int statusPostNumber) {
		this.statusPostNumber = statusPostNumber;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString() {
		return "Ads [id=" + id + ", nameAd=" + nameAd + ", explanation=" + explanation + ", datePublished="
				+ datePublished + ", statusPostNumber=" + statusPostNumber + ", dateEnd=" + dateEnd + ", statusPostTxt="
				+ statusPostTxt.getTxt() + "]";
	}
}
