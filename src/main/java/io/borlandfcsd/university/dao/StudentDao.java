package io.borlandfcsd.university.dao;

import io.borlandfcsd.university.entity.Role;
import io.borlandfcsd.university.entity.Student;
import io.borlandfcsd.university.entity.Traits;

import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private List<Student> students;


    private StudentDao() {
        this.students = new ArrayList<>();
        students.add(new Student(1,"Dmitri","Koshevoi","dkoshev","12345678",Role.STUDENT, Traits.getRandomTraits()));
        students.add(new Student(2,"Olga","Kravcova","okravc","1",Role.STUDENT, Traits.getRandomTraits()));
        students.add(new Student(3,"Sergei","Danchenko","sdanchen","12345678",Role.STUDENT, Traits.getRandomTraits()));
        students.add(new Student(4,"Viktor","Kuznecov","vkuznec","1",Role.STUDENT, Traits.getRandomTraits()));
        students.add(new Student(5,"Oleg","Lujkov","olujko","12345678",Role.STUDENT, Traits.getRandomTraits()));
        students.add(new Student(6,"Olga","Kravchenko","okravch","1",Role.STUDENT, Traits.getRandomTraits()));
        students.add(new Student(7,"Lilia","Samoilova","lsamoi","12345678",Role.STUDENT, Traits.getRandomTraits()));
        students.add(new Student(8,"Lubov","Zagoretska","lzagor","1",Role.STUDENT, Traits.getRandomTraits()));
    }

    private static class StudentDaoHelper{
        private static final StudentDao INSTANCE = new StudentDao();
    }

    public static StudentDao getInstance(){
        return StudentDaoHelper.INSTANCE;
    }

    public List<Student> getStudents(){
        return students;
    }

    public Student getStudentsWithId(int id){
        for (Student student : students){
            if(student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    public Student getStudentWithUsername(String username) {
        for (Student student : students){
            if(student.getUsername().equals(username)) {
                return student;
            }
        }
        return null;
    }
}
