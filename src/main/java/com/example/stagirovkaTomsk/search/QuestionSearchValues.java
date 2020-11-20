package com.example.stagirovkaTomsk.search;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionSearchValues {
    private Integer pageNumber;
    private Integer pageSize;
    private UUID id;
}
