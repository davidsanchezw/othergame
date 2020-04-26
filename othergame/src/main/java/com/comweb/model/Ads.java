package com.comweb.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name = "Ads")
public class Ads implements java.io.Serializable {

	public Ads() {
	}

	public Ads(String nameAd, String explanation, Date datePublished, Date dateEnd, StatusItemTxt statusItemTxt,
			StatusPostTxt statusPostTxt, Users user) {
		super();
		this.nameAd = nameAd;
		this.explanation = explanation;
		this.datePublished = datePublished;
		this.dateEnd = dateEnd;
		this.statusItemTxt = statusItemTxt;
		this.statusPostTxt = statusPostTxt;
		this.user = user;
	}

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
	private Date dateEnd;

	@OneToOne
	@JoinColumn(name = "statusItemNumber")
	private StatusItemTxt statusItemTxt;

	@OneToOne
	@JoinColumn(name = "statusPostNumber")
	private StatusPostTxt statusPostTxt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user")
	private Users user;

	@OneToMany(mappedBy = "ad1")
	private List<Matches> matchesFisrt = new ArrayList<>();

	@OneToMany(mappedBy = "ad2")
	private List<Matches> matchesSecond = new ArrayList<>();

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

	public String getStatusItemTxt() {
		return statusItemTxt.getTxt();
	}

	public StatusPostTxt getStatusPostTxt() {
		return statusPostTxt;
	}

	public Date getDatePublished() {
		return datePublished;
	}

	public void setDatePublished(Date datePublished) {
		this.datePublished = datePublished;
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
				+ datePublished + ", dateEnd=" + dateEnd + ", statusItemTxt=" + statusItemTxt.getTxt()
				+ ", statusPostTxt=" + statusPostTxt.getTxt() + user.toString() + "]";
	}

	public Users getUser() {
		return user;
	}

	public List<Matches> getMatchesFirst() {
		return matchesFisrt;
	}

	public List<Matches> getMatchesSecond() {
		return matchesSecond;
	}

	public void setStatusPostTxt(StatusPostTxt statusPostTxt) {
		this.statusPostTxt = statusPostTxt;
	}
}
