package com.example.stagirovkaTomsk.service;

import com.example.stagirovkaTomsk.model.Pull;
import com.example.stagirovkaTomsk.repos.PullRepos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class PullServiceImpl implements PullService  {
    private final PullRepos repos;


    public PullServiceImpl(PullRepos repos) {
        this.repos = repos;
    }

    @Override
    public List<Pull> findAll(){
        return repos.findAll();
    }

    @Override
    public Pull add(Pull pull){
        return repos.save(pull);

    }

    @Override
    public Pull update(Pull pull){
        return repos.save(pull);

    }

    @Override
    public UUID changeStatus(UUID id) {
        Pull pull = repos.findById(id).orElseThrow(()->new EntityNotFoundException());
        pull.setStatus(!pull.getStatus());
        repos.save(pull);
        return pull.getId();

    }

    @Override
    public void deleteById(UUID id){
        repos.deleteById(id);
    }

    @Override
    public Page findByParams(String name_pull, Date date_start, Date date_end, Boolean status,UUID id ,PageRequest paging){


        return repos.findByParams(name_pull,date_start,date_end,status,id,paging);

    }

}
