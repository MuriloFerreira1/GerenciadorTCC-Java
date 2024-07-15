package com.MFF.OrganizadorTCC.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MFF.OrganizadorTCC.Model.Area.Area;
import com.MFF.OrganizadorTCC.Model.Area.AreaRepository;

@Service
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
