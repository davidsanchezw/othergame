package com.comweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "StatusMatchTxt")
public class StatusMatchTxt {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String txt;

	public int getId() {
		return id;
	}

	public String getTxt() {
		return txt;
	}

}