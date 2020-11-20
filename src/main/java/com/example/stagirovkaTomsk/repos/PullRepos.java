package com.example.stagirovkaTomsk.repos;

import com.example.stagirovkaTomsk.model.Pull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.UUID;

@Repository
public interface PullRepos extends JpaRepository<Pull, UUID> {


    @Query("SELECT p FROM Pull p where " +
            "(:name_pull is null or lower(p.name_pull) like lower(concat('%', :name_pull,'%'))) and" +
            "(:date_start is null or p.date_start=:date_start) and " +
            "(:date_end is null or p.date_end=:date_end) and" +
            "(:status is null or p.status=:status) and"+
            "(:id is null or p.id=:id)"
    )

    Page<Pull> findByParams(@Param("name_pull") String name_pull,
                            @Param("date_start") Date date_start,
                            @Param("date_end") Date date_end,
                            @Param("status") Boolean status,
                            @Param("id")UUID id,
                            Pageable pageable
    );



}
