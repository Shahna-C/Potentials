package com.sc.Nerve.Entity;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sc.Nerve.Util.ProcedureStatus;

@Entity
public class Procedure {
	
	
	private Long id;
	private String name;
	private ProcedureStatus status;
	
	private Set<Nerve> nerves;
	
	@JsonIgnore
	private User user;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
    @ManyToMany(mappedBy = "procedures")
	public Set<Nerve> getNerves() {
		return nerves;
	}
	public void setNerves(Set<Nerve> nerves) {
		this.nerves = nerves;
	
	}
	
	@ManyToOne
	@JoinColumn(name = "userId")
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public ProcedureStatus getStatus() {
		return status;
	}
	public void setStatus(ProcedureStatus status) {
		this.status = status;
	}
	
	

}
