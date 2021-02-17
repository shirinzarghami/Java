package com.example.demo.student;

import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return  studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email is taken");
        }
        studentRepository.save(student);

    }

    public void deleteStudent(Long id) {
       boolean exist =  studentRepository.existsById(id);
       if(!exist) {
           throw new IllegalStateException("Student with id"+ id+ " does not exist!");
       } else {
           studentRepository.deleteById(id);
       }


    }

    @Transient
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId).orElseThrow(()-> new IllegalStateException("Can not find student with id"+ studentId));
        if(checkStudentPropertieseBeforeEdit(student.getName(),name)) {
            student.setName(name);
        }
        if (checkStudentPropertieseBeforeEdit(student.getEmail(), email)) {
            student.setEmail(email);
        }
    }
    private boolean checkStudentPropertieseBeforeEdit( String currentValue, String newValue) {
       return   (newValue != null &&
               newValue.length() > 0 &&
                !Objects.equals(currentValue,newValue));
    }
}
