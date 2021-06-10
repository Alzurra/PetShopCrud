package com.thiagocostafatec.petshop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagocostafatec.petshop.entities.Pet;

@Repository
public interface PetShopRepository extends JpaRepository<Pet, Long> {

}
