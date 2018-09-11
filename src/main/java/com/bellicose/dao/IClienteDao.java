package com.bellicose.dao;

import org.springframework.data.repository.CrudRepository;

import com.bellicose.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long>{

}
