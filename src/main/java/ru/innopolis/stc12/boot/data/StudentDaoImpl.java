package ru.innopolis.stc12.boot.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.innopolis.stc12.boot.data.mapper.StudentMapper;
import ru.innopolis.stc12.boot.data.pojo.Student;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {
    private JdbcTemplate jdbcTemplate;

    public StudentDaoImpl() {
    }

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> getStudentsList() {
        String getStudentsQuery = "select * from students";
        return jdbcTemplate.query(getStudentsQuery, new StudentMapper());
    }

    @Override
    public void addStudent(Student student) {
        String addStudentQuery = "insert into students (name, family_name, age, contact)" +
                " values (?,?,?,?)";
        jdbcTemplate.update(addStudentQuery, student.getName(), student.getFamilyName(),
                student.getAge(), student.getGroup());
    }

    @Override
    public Student getStudent(Integer id) {
        String getStudentQuery = "select * from students where id=?";
        return jdbcTemplate.queryForObject(getStudentQuery,
                new Object[]{id}, new StudentMapper());
    }

    @Override
    public void deleteStudent(Integer id) {
        String deleteStudentQuery = "delete from students where id=?";
        jdbcTemplate.update(deleteStudentQuery, id);
    }

    @Override
    public void update(Integer id, Integer age, String name, String familyName) {
        String updateStudentQuery = "update students set name=?, family_name=?, age=? " +
                "where id=?";
        jdbcTemplate.update(updateStudentQuery, name, familyName, age, id);
    }
}
