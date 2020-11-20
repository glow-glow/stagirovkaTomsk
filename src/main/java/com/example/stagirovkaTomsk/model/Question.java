package com.example.stagirovkaTomsk.model;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="question")
@NoArgsConstructor
@EqualsAndHashCode
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Basic
    @Column(name = "id")
    private UUID id;

    @Basic
    @Column(name = "content")
    private String content;


    @ManyToOne
    @JoinColumn(name = "pull_id", referencedColumnName = "id")
    private  Pull pull;
    }






