package io.borlandfcsd.university.dao;

import io.borlandfcsd.university.entity.Professor;
import io.borlandfcsd.university.entity.Subject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class SubjectDao {
    private List<Subject> subjects;

    private SubjectDao() {
        this.subjects = new ArrayList<>();
        ProfessorDao professorDao = ProfessorDao.getInstance();
        List<Professor> professors = professorDao.getProfessors();
        subjects.add(new Subject(1,"Philosophy"));
        subjects.add(new Subject(2,"Marketing"));
        subjects.add(new Subject(3,"English"));
        subjects.add(new Subject(4,"Robotics"));
        subjects.add(new Subject(5, "Spanish"));
        subjects.add(new Subject(6, "Physic"));
        subjects.add(new Subject(7, "Anatomy"));

        for(Subject subject : subjects){
            int random = ThreadLocalRandom.current().nextInt(0, professors.size());
            subject.setProfessor(professors.get(random));
            professors.get(random).getSubjects().add(subject);
        }
    }

    private static class SubjectDaoHelper{
        private final static SubjectDao INSTANCE = new SubjectDao();
    }

    public static SubjectDao getInstance(){
        return SubjectDaoHelper.INSTANCE;
    }

    public List<Subject> getSubjects(){
        return subjects;
    }
}
