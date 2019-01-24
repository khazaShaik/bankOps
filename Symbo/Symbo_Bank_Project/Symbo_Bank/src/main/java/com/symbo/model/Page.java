package com.symbo.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
@Table(name = "DCM_PAGE")
public class Page {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PAGE_ID")
	private Long id;

	@NotBlank
	@Size(min = 3, max = 150)
	private String pageName;

	@NotBlank
	private String pageUrl;

	private int pageLevel;

	@NotBlank
	@Size(min = 3, max = 150)
	private String pageSf;

	private String pageDec;

	@ManyToMany(targetEntity=Role.class)  
    private Set<Role> roles = new HashSet<>();
	
	public Page() {	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getPageUrl() {
		return pageUrl;
	}

	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}

	public String getPageSf() {
		return pageSf;
	}

	public void setPageSf(String pageSf) {
		this.pageSf = pageSf;
	}

	public String getPageDec() {
		return pageDec;
	}

	public void setPageDec(String pageDec) {
		this.pageDec = pageDec;
	}

	public int getPageLevel() {
		return pageLevel;
	}

	public void setPageLevel(int pageLevel) {
		this.pageLevel = pageLevel;
	}

	public Page(String pageName, String pageUrl, int pageLevel, String pageSf, String pageDec) {
		super();
		this.pageName = pageName;
		this.pageUrl = pageUrl;
		this.pageLevel = pageLevel;
		this.pageSf = pageSf;
		this.pageDec = pageDec;
	}

}
