package com.thiagocostafatec.petshop.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thiagocostafatec.petshop.dto.PetDTO;
import com.thiagocostafatec.petshop.entities.Pet;
import com.thiagocostafatec.petshop.repositories.PetShopRepository;
import com.thiagocostafatec.petshop.services.exceptions.DataBaseException;
import com.thiagocostafatec.petshop.services.exceptions.ResourceNotFoundException;

@Service
public class PetShopService {

	@Autowired
	private PetShopRepository repository;

	@Transactional(readOnly = true)
	public Page<PetDTO> findAllPaged(PageRequest pageRequest) {
		Page<Pet> list = repository.findAll(pageRequest);
		return list.map(x -> new PetDTO(x));
	}

	@Transactional(readOnly = true)
	public PetDTO findById(Long id) {
		Optional<Pet> obj = repository.findById(id);
		Pet entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found"));
		return new PetDTO(entity);
	}

	@Transactional
	public PetDTO insert(PetDTO dto) {
		Pet entity = new Pet();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new PetDTO(entity);
	}

	@Transactional
	public PetDTO update(Long id, PetDTO dto) {
		try {
			Pet entity = repository.getOne(id);
			copyDtoToEntity(dto, entity);
			entity = repository.save(entity);
			return new PetDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id/Pet not found " + id);
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("Id/Pet not found: " + id);
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Integrity violation");
		}
	}

	private void copyDtoToEntity(PetDTO dto, Pet entity) {
		entity.setName(dto.getName());
		entity.setBirthDate(dto.getBirthDate());
		entity.setWeight(dto.getWeight());
	}
}