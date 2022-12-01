package com.enigmacamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "m_lecturer")
public class Lecturer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lecturer_id", unique = true)
    @Getter     @Setter
    private Long lecturerId;
    @Column(name = "first_name", nullable = false,length = 50)
    @Getter     @Setter
    private String firstName;
    @Column(name = "last_name",nullable = false,length = 50)
    @Getter     @Setter
    private String lastName;
    @Email(message="That was not an email address!")
    @Column(name = "email", nullable = false, unique = true)
    @Getter     @Setter
    private String email;
    
    @Override
    public String toString() {
        return "Lecturer{" + "lecturerId=" + lecturerId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + '}';
    }
}
