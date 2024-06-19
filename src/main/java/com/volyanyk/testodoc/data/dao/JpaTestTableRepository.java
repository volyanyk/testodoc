package com.volyanyk.testodoc.data.dao;

import com.volyanyk.testodoc.data.entity.TestTable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaTestTableRepository extends CrudRepository<TestTable, UUID> {
}
