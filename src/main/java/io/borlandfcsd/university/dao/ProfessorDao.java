package io.borlandfcsd.university.dao;

import io.borlandfcsd.university.entity.Professor;
import io.borlandfcsd.university.entity.Role;
import io.borlandfcsd.university.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class ProfessorDao {
    private List<Professor> professors;

    private ProfessorDao(){
        professors = new ArrayList<>();
        professors.add(new Professor(1,"Olga","Golovenko",Role.PROFESSOR,"ogolov","12345678",new ArrayList<>()));
        professors.add(new Professor(2,"Alexander","Pertrov",Role.PROFESSOR,"apetr","12345678",new ArrayList<>()));
        professors.add(new Professor(3,"Artur","Drujko",Role.PROFESSOR,"adrujko","12345678",new ArrayList<>()));
    }

    private static class ProfessorDaoHelper{
        private final static ProfessorDao INSTANCE = new ProfessorDao();
    }

    public static ProfessorDao getInstance(){
        return ProfessorDaoHelper.INSTANCE;
    }

    public List<Professor> getProfessors(){
        return professors;
    }

    public Professor getProfessorWithId(int id){
        for (Professor professor : professors){
            if(professor.getId() == id) {
                return professor;
            }
        }
        return null;
    }

    public Professor getProfessorWithUsername(String username) {
        for (Professor professor : professors){
            if(professor.getUsername().equals(username)) {
                return professor;
            }
        }
        return null;
    }
}
