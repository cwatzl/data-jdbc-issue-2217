package com.example.data_jdbc_issue_2217;

import lombok.Data;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;

@Data
public class MyItem {
    @Id
    private Long id;

    @Nullable
    private Integer quantity;
}
