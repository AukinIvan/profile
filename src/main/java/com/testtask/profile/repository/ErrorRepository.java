package com.testtask.profile.repository;

import com.testtask.profile.model.Error;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ErrorRepository extends JpaRepository<Error, Integer> {
    Error findFirstByOrderByCreatedDesc();
}
