package com.volyanyk.testodoc.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.volyanyk.testodoc.data.dao.JpaTestTableRepository;
import com.volyanyk.testodoc.data.dao.JpaTestViewRepository;
import com.volyanyk.testodoc.data.entity.TestTable;
import com.volyanyk.testodoc.data.entity.TestView;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Data
public class TestService {

    private final JpaTestViewRepository jpaTestViewRepository;
    private final JpaTestTableRepository jpaTestTableRepository;
    private final ObjectMapper objectMapper;

    public TestTable createEntity() {
        TestTable entity = new TestTable();
        ObjectNode data = objectMapper.createObjectNode();
        data.put("first_id", 1);
        data.put("secondary_id", 2);
        data.put("third_id", 3);
        data.put("loading_city", "Los Angeles");
        data.put("unloading_city", "Toronto");
        data.put("loading_country_code", "US");
        data.put("unloading_country_code", "CA");
        data.put("type", "INTERNAL");
        data.put("status", "INITIAL");
        data.put("source", "EXTERNAL");
        for(int i = 0; i < 10; i++) {
            data.put("custom_field_" + i, i);
        }


        entity.setData(data);
        entity.setId(UUID.randomUUID());
        return jpaTestTableRepository.save(entity);
    }

    public Optional<TestTable> getEntityTable(UUID id) {
        return jpaTestTableRepository.findById(id);
    }

    public Page<TestView> getEntityViews(Pageable pageable) {
        return jpaTestViewRepository.findAll(pageable);
    }

}

