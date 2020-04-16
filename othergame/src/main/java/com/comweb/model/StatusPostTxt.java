package com.comweb.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "StatusPostTxt")
public class StatusPostTxt {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column
	private String txt;

	public String getTxt() {
		return txt;
	}

}
