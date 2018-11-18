package io.borlandfcsd.university.dao;

import io.borlandfcsd.university.entity.Lecture;

import java.util.ArrayList;
import java.util.List;

public class LectureDao {
    private List<Lecture> lectures;

    private LectureDao() {
        this.lectures = new ArrayList<>();
    }

    public void addLecture(Lecture lecture) {
        lectures.add(lecture);
    }

    public Lecture getLectureWithId(int id) {
        return lectures.stream()
                .filter(lecture -> lecture.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Lecture> getLectures() {
        return lectures;
    }

    private static class LectureHelper {
        private static final LectureDao INSTANCE = new LectureDao();
    }

    public static LectureDao getInstance() {
        return LectureHelper.INSTANCE;
    }

}
