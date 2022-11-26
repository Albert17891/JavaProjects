package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
public class StudentService {

    private  final  StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
             var studentOptional=studentRepository
                     .findByStudentEmail(student.getEmail());
             if(studentOptional.isPresent()){
                 throw new IllegalStateException("Email taken");
             }

             studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        var exist=studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException("Student with this"+studentId+"does not exist");
        }
        studentRepository.deleteById(studentId);
    }
}
