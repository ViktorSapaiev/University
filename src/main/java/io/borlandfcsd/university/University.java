package io.borlandfcsd.university;

import io.borlandfcsd.university.dao.GroupDao;
import io.borlandfcsd.university.dao.ProfessorDao;
import io.borlandfcsd.university.dao.SubjectDao;
import io.borlandfcsd.university.entity.Person;
import io.borlandfcsd.university.entity.Professor;
import io.borlandfcsd.university.entity.Student;
import io.borlandfcsd.university.menu.Menu;

public class University {
    private Person user;

    public static void main(String[] args) {
       University university = UniversityHelper.INSTANCE;
       GroupDao groupDao = GroupDao.getInstance();
       ProfessorDao professorDao = ProfessorDao.getInstance();
       SubjectDao subjectDao = SubjectDao.getInstance();

       Menu menu = Menu.getInstance();
       menu.run();
    }

    public static Student getCurrentStudent(){
        Person person = University.getInstance().getUser();
        if (person instanceof Student) {
            return (Student) person;
        } else {
            System.err.println("Current user is not student");
            return null;
        }
    }

    public static Professor getCurrentProfessor() {
        Person person = University.getInstance().getUser();
        if (person instanceof Professor) {
            return (Professor) person;
        } else {
            System.err.println("Current user is not professor");
            return null;
        }
    }

    private static class UniversityHelper{
        private static final University INSTANCE  = new University();
    }

    public static University getInstance(){
        return UniversityHelper.INSTANCE;
    }

    public Person getUser() {
        return user;
    }

    public void setUser(Person user) {
        this.user = user;
    }
}
