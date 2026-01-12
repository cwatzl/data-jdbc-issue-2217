package com.example.data_jdbc_issue_2217;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyItemRepository extends CrudRepository<MyItem, Long> {
}
