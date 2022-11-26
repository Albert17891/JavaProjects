package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;

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

    @Transactional
    public void updateStudent(Long studentId,
                              String name,
                              String email) {

        var student=studentRepository.findById(studentId)
                .orElseThrow(()->new IllegalStateException(
                        "student with id"+ studentId+"does not exist"));

        if(name !=null &&
                  name.length()>0 &&
                  !Objects.equals(student.getName(),name)){
            student.setName(name);
        }

        if(email !=null &&
                email.length()>0 &&
                !Objects.equals(student.getEmail(),email)){
            var studentOptional=studentRepository
                    .findByStudentEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email taken");
            }
        }
            student.setEmail(email);
    }
}
