package com.suyang.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class WebSite {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Column(nullable = false)
	private String webSiteName;
	@Column(nullable = false)
	private String urlTemplate;
	@Column(nullable = false)
	private String totalSelector;
	@Column(nullable = false)
	private String listSelector;
	@Column(nullable = false)
	private String titleSelector;
	@Column(nullable = false)
	private String contentSelector;
	private Date lastUpdateTime;
	private Date lastExtractTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWebSiteName() {
		return webSiteName;
	}

	public void setWebSiteName(String webSiteName) {
		this.webSiteName = webSiteName;
	}

	public String getUrlTemplate() {
		return urlTemplate;
	}

	public void setUrlTemplate(String urlTemplate) {
		this.urlTemplate = urlTemplate;
	}

	public String getTotalSelector() {
		return totalSelector;
	}

	public void setTotalSelector(String totalSelector) {
		this.totalSelector = totalSelector;
	}

	public String getListSelector() {
		return listSelector;
	}

	public void setListSelector(String listSelector) {
		this.listSelector = listSelector;
	}

	public String getTitleSelector() {
		return titleSelector;
	}

	public void setTitleSelector(String titleSelector) {
		this.titleSelector = titleSelector;
	}

	public String getContentSelector() {
		return contentSelector;
	}

	public void setContentSelector(String contentSelector) {
		this.contentSelector = contentSelector;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public Date getLastExtractTime() {
		return lastExtractTime;
	}

	public void setLastExtractTime(Date lastExtractTime) {
		this.lastExtractTime = lastExtractTime;
	}

}
