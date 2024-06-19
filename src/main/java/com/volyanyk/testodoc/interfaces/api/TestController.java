package com.volyanyk.testodoc.interfaces.api;

import com.volyanyk.testodoc.data.entity.TestTable;
import com.volyanyk.testodoc.data.entity.TestView;
import com.volyanyk.testodoc.services.TestService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/test")
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @PostMapping("/table")
    public ResponseEntity<TestTable> createEntity() {
        return ResponseEntity.ok(testService.createEntity());
    }

    @GetMapping("/table/{id}")
    public ResponseEntity<TestTable> getEntityTable(@PathVariable UUID id) {
        return testService.getEntityTable(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/view/{page}/{size}")
    public ResponseEntity<Page<TestView>> getEntityViews(@PathVariable int page, @PathVariable int size) {
        Pageable pageable = PageRequest.of(page,size, Sort.by("createDate").descending());
        return ResponseEntity.ok(testService.getEntityViews(pageable));
    }
}