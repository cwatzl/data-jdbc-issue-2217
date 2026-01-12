package com.example.data_jdbc_issue_2217;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jdbc.test.autoconfigure.DataJdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@DataJdbcTest
class MyItemRepositoryTests {
    @Autowired
    private MyItemRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test // Fails
    void convertsBlankToNull() {
        jdbcTemplate.execute("INSERT INTO my_item (id, quantity) VALUES (1L, '')");
        var item = repository.findById(1L).orElseThrow();
        assertThat(item.getQuantity()).isNull();
    }

    @Test // OK
    void treatsNullAsIs() {
        jdbcTemplate.execute("INSERT INTO my_item (id, quantity) VALUES (1L, null)");
        var item = repository.findById(1L).orElseThrow();
        assertThat(item.getQuantity()).isNull();
    }

    @Test // OK
    void convertsNumericString() {
        jdbcTemplate.execute("INSERT INTO my_item (id, quantity) VALUES (1L, '42')");
        var item = repository.findById(1L).orElseThrow();
        assertThat(item.getQuantity()).isEqualTo(42);
    }
}