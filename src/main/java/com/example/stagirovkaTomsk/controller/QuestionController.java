package com.example.stagirovkaTomsk.controller;


import com.example.stagirovkaTomsk.model.Pull;
import com.example.stagirovkaTomsk.model.Question;
import com.example.stagirovkaTomsk.search.PullSearchValues;
import com.example.stagirovkaTomsk.search.QuestionSearchValues;
import com.example.stagirovkaTomsk.service.QuestionService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/question")
@ComponentScan(basePackages = {"com.example.*"})
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Question>> findAll(){
        return ResponseEntity.ok(questionService.findAll());
    }
    @PostMapping("/add")
    public ResponseEntity<Question> add(@RequestBody Question question){
        if(question.getContent()==null || question.getContent().trim().length()==0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(questionService.add(question));
    }

    @PutMapping("/update")
    public ResponseEntity update(@RequestBody Question question){
        if(question.getContent()==null || question.getContent().trim().length()==0){
            return new ResponseEntity("missed param: title", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(questionService.update(question));

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id){
        try {
            questionService.deleteById(id);

        }catch (EmptyResultDataAccessException e){
            return new ResponseEntity("id="+id+" not found", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity(HttpStatus.OK);
    }



    @PostMapping("/search")
    public ResponseEntity<Page<Question>> search(@RequestBody QuestionSearchValues questionSearchValues){
        Integer pageNumber = questionSearchValues.getPageNumber() != null ? questionSearchValues.getPageNumber() : null;
        Integer pageSize = questionSearchValues.getPageSize() != null ?questionSearchValues.getPageSize() : null;
        UUID id =questionSearchValues.getId();

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        Page result = questionService.findByContent(id,pageRequest);
        return ResponseEntity.ok(result);



    }

}
