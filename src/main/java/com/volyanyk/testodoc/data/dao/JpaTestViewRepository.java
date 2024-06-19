package com.volyanyk.testodoc.data.dao;

import com.volyanyk.testodoc.data.entity.TestView;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaTestViewRepository extends PagingAndSortingRepository<TestView, UUID> {
}
