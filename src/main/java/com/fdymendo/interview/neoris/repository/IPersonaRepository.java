package com.fdymendo.interview.neoris.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fdymendo.interview.neoris.entity.PersonaEntity;

public interface IPersonaRepository extends JpaRepository<PersonaEntity, Integer> {

}
