package com.suyang.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.suyang.domain.enums.AttchementType;

@Entity
public class Attchement {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private AttchementType attchementType = AttchementType.File;
	@Lob
	@Column(nullable = false)
	private byte[] data;
	@Column(nullable = false)
	private long articleId;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public AttchementType getAttchementType() {
		return attchementType;
	}

	public void setAttchementType(AttchementType attchementType) {
		this.attchementType = attchementType;
	}
}
