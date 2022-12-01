package com.enigmacamp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "m_student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id",unique = true)
    @Getter     @Setter
    private Long studentId;
    @Column(name = "first_name", nullable = false,length = 50)
    @Getter     @Setter
    private String firstName;
    @Column(name = "last_name", nullable = false,length = 50)
    @Getter     @Setter
    private String lastName;
    @Email(message = "That was not an email address")
    @Column(name = "email", nullable = false, unique = true)
    @Getter     @Setter
    private String email;
    
    @Override
    public String toString() {
        return "Student{" + "studentId=" + studentId + ", firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", email='" + email + '\'' + '}';
    }
}
