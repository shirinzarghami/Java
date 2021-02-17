package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfig {
    @Bean
    CommandLineRunner commandLineRunner
            (StudentRepository studentRepository) {
        return  args -> {
           Student student1 = new Student(
                    "Shirin",
                    "shirin@gmail.com",
                    LocalDate.of(1985,05,06));
            Student student2 = new Student(
                    "Martijn",
                    "Martijn@gmail.com",
                    LocalDate.of(1985,05,06));
            studentRepository.saveAll(List.of(student1,student2));
        };
    };
}
