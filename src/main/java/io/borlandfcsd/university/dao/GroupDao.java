package io.borlandfcsd.university.dao;

import io.borlandfcsd.university.entity.Group;
import io.borlandfcsd.university.entity.Student;

import java.util.ArrayList;
import java.util.List;

public class GroupDao {
    private List<Group> groups;

    private GroupDao(){
        groups = new ArrayList<>();
        Group group1 = new Group(1,"1B-18",new ArrayList<>(), new ArrayList<>());
        Group group2 = new Group(1,"2B-18",new ArrayList<>(), new ArrayList<>());

        StudentDao studentDao = StudentDao.getInstance();
        List<Student> students = studentDao.getStudents();

        for (Student student : students){
            if(student.getId() % 2 == 0) {
              student.setGroup(group1);
              group1.getStudents().add(student);
            } else {
                student.setGroup(group2);
                group2.getStudents().add(student);
            }
        }

        groups.add(group1);
        groups.add(group2);
    }

    private static class GroupDaoHelper{
        private final static GroupDao INSTANCE = new GroupDao();
    }

    public static GroupDao getInstance(){
        return GroupDaoHelper.INSTANCE;
    }
    public List<Group> getGroups(){
        return groups;
    }
    public Group getGroupWithId( int id){
        for (Group group : groups){
            if(group.getId() == id){
                return group;
            }
        }
        return null;
    }
}
