package com.example.stagirovkaTomsk.repos;

import com.example.stagirovkaTomsk.model.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface QuestionRepos extends JpaRepository<Question, UUID> {
    @Query("SELECT p FROM Pull p where " +"(:id is null or p.id=:id)")
    Page<Question> findByContent(  @Param("id")UUID id,
                                   Pageable pageable)
            ;
}
