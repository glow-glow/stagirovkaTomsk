package com.example.stagirovkaTomsk.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "pull")
public class Pull {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Basic
    @Column(name = "id")
    private UUID id;

    @Basic
    @Column(name = "name_pull")
    private  String name_pull;

    @Basic
    @Column(name = "date_start")
    private Date date_start;

    @Basic
    @Column(name = "date_end")
    private Date date_end;

    @Basic
    @Column(name = "status")
    private Boolean status;



}
