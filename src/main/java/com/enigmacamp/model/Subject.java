package com.enigmacamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "m_subject")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id", unique = true)
    @Getter     @Setter
    private Long subjectId;
    @Column(name = "subject_name", nullable = false,length = 50,unique = true)
    @Getter     @Setter
    private String subjectName;
    
    @Override
    public String toString() {
        return "Subject{" + "subjectId=" + subjectId + ", subjectName='" + subjectName + '\'' + '}';
    }
}
