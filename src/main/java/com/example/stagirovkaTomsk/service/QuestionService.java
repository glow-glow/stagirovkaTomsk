package com.example.stagirovkaTomsk.service;

import com.example.stagirovkaTomsk.model.Pull;
import com.example.stagirovkaTomsk.model.Question;
import com.example.stagirovkaTomsk.repos.QuestionRepos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class QuestionService {
    private final QuestionRepos questionRepos;

    public QuestionService(QuestionRepos questionRepos) {
        this.questionRepos = questionRepos;

    }
    public List<Question> findAll(){
        return questionRepos.findAll();
    }

    public Question add(Question question){
        return questionRepos.save(question);

    }
    public Question update(Question question){
        return questionRepos.save(question);

    }

    public void deleteById(UUID id){
        questionRepos.deleteById(id);
    }
    public Page findByContent(UUID id,PageRequest paging){
        return questionRepos.findByContent(id,paging);

    }

}
