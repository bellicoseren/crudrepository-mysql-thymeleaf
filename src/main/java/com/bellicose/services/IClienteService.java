package com.bellicose.services;

import java.util.List;
import java.util.Optional;

import com.bellicose.entity.Cliente;

public interface IClienteService {

	public List<Cliente> findAll();
	public void save(Cliente cliente);
	public Optional<Cliente> findOne(Long id);
	public void elimina(Long id);
}
