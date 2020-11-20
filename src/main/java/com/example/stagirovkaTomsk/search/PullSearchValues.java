package com.example.stagirovkaTomsk.search;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PullSearchValues {
    private String name_pull;
    private Date date_end;
    private Date date_start;
    private Boolean status;
    private UUID id;

    // постраничность
    private Integer pageNumber;
    private Integer pageSize;

    // сортировка
    private String sortColumn;
    private String sortDirection;




}

