package com.sc.Nerve.Entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Nerve {
	
		
        private Long id;
        private String name;
        private String muscle;
        private String innervations;
        private String nervePictureUrl;
        
        @JsonIgnore
        private Set<Procedure> procedures;
        
        		
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

		public String getMuscle() {
			return muscle;
		}

		public void setMuscle(String muscle) {
			this.muscle = muscle;
		}

		public String getInnervations() {
			return innervations;
		}

		public void setInnervations(String innervations) {
			this.innervations = innervations;
		}
		
		@ManyToMany(cascade = CascadeType.ALL)
		@JoinTable(name = "nerve_procedure",
			joinColumns = @JoinColumn(name = "nerveId", referencedColumnName = "id"),
			inverseJoinColumns = @JoinColumn(name = "procedureId", referencedColumnName = "id"))
		public Set<Procedure> getProcedures() {
			return procedures;
		}

		public void setProcedures(Set<Procedure> procedures) {
			this.procedures = procedures;
		}

		public String getNervePictureUrl() {
			return nervePictureUrl;
		}

		public void setNervePictureUrl(String nervePictureUrl) {
			this.nervePictureUrl = nervePictureUrl;
		}
		
}
