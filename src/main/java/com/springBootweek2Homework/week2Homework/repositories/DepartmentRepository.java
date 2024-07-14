package com.springBootweek2Homework.week2Homework.repositories;

import com.springBootweek2Homework.week2Homework.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {
}
