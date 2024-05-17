package com.MFF.OrganizadorTCC.Area;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

public class AreaService {
	@Autowired
	private AreaRepository repository;
	
	public List<Area> getAll(){
		return repository.findAll(Sort.by("nome").ascending());
	}
	
	public Area getById(long id) {
		return repository.getReferenceById(id);
	}
}
