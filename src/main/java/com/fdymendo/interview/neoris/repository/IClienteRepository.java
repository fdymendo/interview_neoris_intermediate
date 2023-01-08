package com.fdymendo.interview.neoris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fdymendo.interview.neoris.entity.ClienteEntity;

public interface IClienteRepository extends JpaRepository<ClienteEntity, Integer> {

}
