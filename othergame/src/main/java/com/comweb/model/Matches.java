package com.comweb.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity

@Table(name = "Matches")
public class Matches implements java.io.Serializable {

	public Matches(Users usr1, Users usr2, Ads ad1, Ads ad2, StatusMatchTxt statusMatchTxt, Date dateStart) {
		super();
		this.usr1 = usr1;
		this.usr2 = usr2;
		this.ad1 = ad1;
		this.ad2 = ad2;
		this.statusMatchTxt = statusMatchTxt;
		this.dateStart = dateStart;
	}

	public Matches() {
	}

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "usr1")
	private Users usr1;

	@ManyToOne
	@JoinColumn(name = "usr2")
	private Users usr2;

	@ManyToOne
	@JoinColumn(name = "ad1")
	private Ads ad1;

	@ManyToOne
	@JoinColumn(name = "ad2")
	private Ads ad2;

	@OneToOne
	@JoinColumn(name = "statusMatchNumber")
	private StatusMatchTxt statusMatchTxt;

	@Column
	private Date dateStart;

	@Column
	private Date datePreEnd;

	@Column
	private Date dateEnd;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Users getUsr1() {
		return usr1;
	}

	public void setUsr1(Users usr1) {
		this.usr1 = usr1;
	}

	public Users getUsr2() {
		return usr2;
	}

	public void setUsr2(Users usr2) {
		this.usr2 = usr2;
	}

	public Ads getAd1() {
		return ad1;
	}

	public void setAd1(Ads ad1) {
		this.ad1 = ad1;
	}

	public Ads getAd2() {
		return ad2;
	}

	public void setAd2(Ads ad2) {
		this.ad2 = ad2;
	}

	public StatusMatchTxt getStatusMatchTxt() {
		return statusMatchTxt;
	}

	public void setStatusMatchTxt(StatusMatchTxt statusMatchTxt) {
		this.statusMatchTxt = statusMatchTxt;
	}

	public Date getDateStart() {
		return dateStart;
	}

	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}

	public Date getDatePreEnd() {
		return datePreEnd;
	}

	public void setDatePreEnd(Date datePreEnd) {
		this.datePreEnd = datePreEnd;
	}

	public Date getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(Date dateEnd) {
		this.dateEnd = dateEnd;
	}

	@Override
	public String toString() {
		return "Matches [id=" + id + ", usr1=" + usr1 + ", usr2=" + usr2 + ", ad1=" + ad1 + ", ad2=" + ad2
				+ ", statusMatchTxt=" + statusMatchTxt + ", dateStart=" + dateStart + ", datePreEnd=" + datePreEnd
				+ ", dateEnd=" + dateEnd + "]";
	}

}
