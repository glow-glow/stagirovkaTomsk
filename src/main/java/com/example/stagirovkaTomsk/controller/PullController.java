package com.example.stagirovkaTomsk.controller;


import com.example.stagirovkaTomsk.model.Pull;
import com.example.stagirovkaTomsk.search.PullSearchValues;
import com.example.stagirovkaTomsk.service.PullServiceImpl;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pull")
@ComponentScan(basePackages = {"com.example.*"})
public class PullController {
    private final PullServiceImpl pullServiceImpl;

    public PullController(PullServiceImpl pullServiceImpl) {
        this.pullServiceImpl = pullServiceImpl;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Pull>> findAll(){
        return ResponseEntity.ok(pullServiceImpl.findAll());
    }
    @PostMapping("/add")
    public ResponseEntity<Pull> add(@RequestBody Pull pull){
        if(pull.getName_pull()==null || pull.getName_pull().trim().length()==0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(pullServiceImpl.add(pull));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Pull pull){
        if(pull.getName_pull()==null || pull.getName_pull().trim().length()==0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(pullServiceImpl.update(pull));

    }

    @PutMapping("/changStatus")
    public ResponseEntity<UUID> changeStatus(@PathVariable UUID id){
        return ResponseEntity.ok(pullServiceImpl.changeStatus(id));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        try {
            pullServiceImpl.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Pull>> search(@RequestBody PullSearchValues pullSearchValues){
        String name_pull =pullSearchValues.getName_pull() != null? pullSearchValues.getName_pull() : null;
        Date date_end =pullSearchValues.getDate_end() != null? pullSearchValues.getDate_end() : null;
        Date date_start =pullSearchValues.getDate_start() != null? pullSearchValues.getDate_start() : null;
        Boolean status = pullSearchValues.getStatus();
        UUID id =pullSearchValues.getId();

        String sortColumn = pullSearchValues.getSortColumn() != null ? pullSearchValues.getSortColumn() : null;
        String sortDirection = pullSearchValues.getSortDirection() != null ? pullSearchValues.getSortDirection() : null;

        Integer pageNumber = pullSearchValues.getPageNumber() != null ? pullSearchValues.getPageNumber() : null;
        Integer pageSize = pullSearchValues.getPageSize() != null ? pullSearchValues.getPageSize() : null;

        Sort.Direction direction = sortDirection == null || sortDirection.trim().length() == 0 || sortDirection.trim().equals("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction, sortColumn);

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
        Page result = pullServiceImpl.findByParams(name_pull,date_start,date_end,status,id,pageRequest);

        return ResponseEntity.ok(result);


    }

}
