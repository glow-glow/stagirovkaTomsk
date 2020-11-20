package com.example.stagirovkaTomsk.service;

import com.example.stagirovkaTomsk.model.Pull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.Date;
import java.util.List;
import java.util.UUID;

public interface PullService {
    List<Pull> findAll();
    Pull add(Pull pull);
    Pull update(Pull pull);
    UUID changeStatus(UUID id);
    void deleteById(UUID id);
    Page findByParams(String name_pull, Date date_start, Date date_end, Boolean status,UUID id ,PageRequest paging);



    }
