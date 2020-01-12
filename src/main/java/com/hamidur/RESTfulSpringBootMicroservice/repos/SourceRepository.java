package com.hamidur.RESTfulSpringBootMicroservice.repos;

import com.hamidur.RESTfulSpringBootMicroservice.models.Source;
import org.springframework.data.repository.CrudRepository;

public interface SourceRepository extends CrudRepository<Source, Integer> {
}
