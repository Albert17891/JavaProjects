package com.example.demo.student;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return  args -> {

            Student mariam=new Student(
                    "Mariam",
                    "Mariam@gmail.com",
                    LocalDate.of(2000, Month.JANUARY,1)

            );
            Student alex=new Student(
                    "alex",
                    "alex@gmail.com",
                    LocalDate.of(2000, Month.JANUARY,1)

            );

            studentRepository.saveAll(List.of(mariam,alex));
        };
    }

}
