package com.symbo.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "DCM_ROLES")
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ROLE_ID", unique = true, nullable = false)
	private Long id;

	@Enumerated(EnumType.STRING)
	@NaturalId
	@Column(length = 60 ,name = "ROLE_NAME")
	private RoleName name;

	@ManyToMany(targetEntity=Page.class)  
	private Set<Page> pages = new HashSet<Page>();

	public Role() {
	}

	public Role(RoleName roleUser) {
		this.name = roleUser;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}

	public Set<Page> getPages() {
		return pages;
	}

	public void setPages(Set<Page> pageSet) {
		this.pages = pageSet;

	}

	
}
