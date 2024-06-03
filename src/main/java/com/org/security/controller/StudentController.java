package com.org.security.controller;

import com.org.security.model.Student;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    private  List<Student> students=new ArrayList<>();

    @GetMapping("/students")
    public List<Student> students(){
        Student st1=new Student();
        st1.setId(1);
        st1.setFirstName("Ammu");
        st1.setLastName("Guduru");

        Student st2=new Student();
        st2.setId(1);
        st2.setFirstName("Veera");
        st2.setLastName("Lavidi");

        Student st3=new Student();
        st3.setId(1);
        st3.setFirstName("Monu");
        st3.setLastName("Guduru");

        students.add(st1);
        students.add(st2);
        students.add(st3);
        return students;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public Student createStudent(@RequestBody Student student){
         students.add(student);
         return student;
    }

}
