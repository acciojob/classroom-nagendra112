package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class StudentRepository {

    HashMap<String, Student> studentDb = new HashMap<>();
    HashMap<String, Teacher> teacherDb = new HashMap<>();

    HashMap<String, String> studentTeacher = new HashMap<>();

    HashMap<String, List<String>> teacherStudentDb = new HashMap<>();

    public void addStudent(Student student){
        studentDb.put(student.getName(), student);
    }

    public void addTeacher(Teacher teacher){
        teacherDb.put(teacher.getName(), teacher);
    }

    public void addStudentTeacherPair(String student, String teacher){
        studentTeacher.put(student, teacher);

        List<String> students = new ArrayList<>();
        if(teacherStudentDb.containsKey(teacher)){
            students = teacherStudentDb.get(teacher);
            students.add(student);
            teacherStudentDb.put(teacher, students);
        }
        else {
            students.add(student);
            teacherStudentDb.put(teacher, students);
        }
    }

    public Student getStudentByName(String name){
        return studentDb.get(name);
    }

    public Teacher getTeacherByName(String name){
        return teacherDb.get(name);
    }

    public List<String> getStudentsByTeacherName(String teacher){
        return teacherStudentDb.get(teacher);
    }

    public List<String> getAllStudents(){
        List<String > students = new ArrayList<>();
        for(String s : studentDb.keySet()){
            students.add(s);
        }
        return students;
    }

    public void deleteTeacherByName(String name){
        teacherDb.remove(name);

        List<String > students = teacherStudentDb.get(name);
        for(String s : students){
            studentDb.remove(s);
            studentTeacher.remove(s);
        }
        teacherStudentDb.remove(name);
    }

    public void deleteAllTeachers(){
        for(String student : studentTeacher.keySet()){
            studentDb.remove(student);
        }
        for(List<String > students : teacherStudentDb.values()){
            for (String student : students){
                studentDb.remove(student);
            }
        }

        teacherDb.clear();
        studentTeacher.clear();
        teacherStudentDb.clear();
    }

}




















