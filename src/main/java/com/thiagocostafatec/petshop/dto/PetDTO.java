package com.thiagocostafatec.petshop.dto;

import java.io.Serializable;
import java.time.Instant;

import com.thiagocostafatec.petshop.entities.Pet;

public class PetDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	private String name;
	private Instant birthDate;
	private Double weight;

	public PetDTO() {

	}

	public PetDTO(Long id, String name, Instant birthDate, Double weight) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.weight = weight;
	}

	public PetDTO(Pet entity) {
		id = entity.getId();
		name = entity.getName();
		weight = entity.getWeight();
		birthDate = entity.getBirthDate();
	}

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

	public Instant getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Instant birthDate) {
		this.birthDate = birthDate;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}
}
