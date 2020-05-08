package com.masivian.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.masivian.models.*;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {}
