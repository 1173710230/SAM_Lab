package com.lsq.dao;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lsq.model.PageBean;
import com.lsq.model.Student;
import com.lsq.util.DateUtil;
import com.lsq.util.StringUtil;
import io.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;

import javax.sql.DataSource;

public class StudentDao {

    public ResultSet studentList(Connection con, PageBean pageBean, Student student, String bbirthday, String ebirthday)
            throws Exception {
        StringBuffer sb = new StringBuffer(
                "select * from t_student s,t_grade g,t_faculty f,t_dormitory d where s.gradeId=g.id and s.facultyId=f.id and s.dormitoryId=d.id");
        if (StringUtil.isNotEmpty(student.getStuNo())) {
            sb.append(" and s.stuNo like '%" + student.getStuNo() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getStuName())) {
            sb.append(" and s.stuName like '%" + student.getStuName() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getSex())) {
            sb.append(" and s.sex ='" + student.getSex() + "'");
        }
        if (student.getFacultyId() != -1) {
            sb.append(" and s.facultyId ='" + student.getFacultyId() + "'");
        }
        if (student.getGradeId() != -1) {
            sb.append(" and s.gradeId ='" + student.getGradeId() + "'");
        }
        if (student.getDormitoryId() != -1) {
            sb.append(" and s.dormitoryId ='" + student.getDormitoryId() + "'");
        }
        if (StringUtil.isNotEmpty(bbirthday)) {
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
        }
        if (StringUtil.isNotEmpty(ebirthday)) {
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
        }
        if (pageBean != null) {
            sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
        }

        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        return pstmt.executeQuery();
    }

    public int studentCount(Connection con, Student student, String bbirthday, String ebirthday) throws Exception {
        StringBuffer sb = new StringBuffer(
                "select count(*) as total from t_student s,t_grade g,t_faculty f,t_dormitory d where s.gradeId=g.id and s.facultyId=f.id and s.dormitoryId=d.id");
        if (StringUtil.isNotEmpty(student.getStuNo())) {
            sb.append(" and s.stuNo like '%" + student.getStuNo() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getStuName())) {
            sb.append(" and s.stuName like '%" + student.getStuName() + "%'");
        }
        if (StringUtil.isNotEmpty(student.getSex())) {
            sb.append(" and s.sex ='" + student.getSex() + "'");
        }
        if (student.getFacultyId() != -1) {
            sb.append(" and s.facultyId ='" + student.getFacultyId() + "'");
        }
        if (student.getGradeId() != -1) {
            sb.append(" and s.gradeId ='" + student.getGradeId() + "'");
        }
        if (student.getDormitoryId() != -1) {
            sb.append(" and s.dormitoryId ='" + student.getDormitoryId() + "'");
        }
        if (StringUtil.isNotEmpty(bbirthday)) {
            sb.append(" and TO_DAYS(s.birthday)>=TO_DAYS('" + bbirthday + "')");
        }
        if (StringUtil.isNotEmpty(ebirthday)) {
            sb.append(" and TO_DAYS(s.birthday)<=TO_DAYS('" + ebirthday + "')");
        }
        PreparedStatement pstmt = con.prepareStatement(sb.toString());
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }

    public int studentDelete(Connection con, String delIds) throws Exception {
        String sql = "delete from t_student where stuId in(" + delIds + ")";
        PreparedStatement pstmt = con.prepareStatement(sql);
        return pstmt.executeUpdate();
    }

    public int studentAdd(Connection con, Student student) throws Exception {
        String sql = "insert into t_student values(null,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, student.getStuNo());
        pstmt.setString(2, student.getStuName());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, DateUtil.formatDate(student.getBirthday(), "yyyy-MM-dd"));
        pstmt.setInt(5, student.getFacultyId());
        pstmt.setInt(6, student.getGradeId());
        pstmt.setInt(7, student.getDormitoryId());
        pstmt.setString(8, student.getEmail());
        pstmt.setString(9, student.getStuDesc());
        return pstmt.executeUpdate();

    }

    public int studentModify(Connection con, Student student) throws Exception {
        String sql = "update t_student set stuNo=?,stuName=?,sex=?,birthday=?,facultyId=?,gradeId=?,dormitoryId=?,email=?,stuDesc=? where stuId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, student.getStuNo());
        pstmt.setString(2, student.getStuName());
        pstmt.setString(3, student.getSex());
        pstmt.setString(4, DateUtil.formatDate(student.getBirthday(), "yyyy-MM-dd"));
        pstmt.setInt(5, student.getFacultyId());
        pstmt.setInt(6, student.getGradeId());
        pstmt.setInt(7, student.getDormitoryId());
        pstmt.setString(8, student.getEmail());
        pstmt.setString(9, student.getStuDesc());
        pstmt.setInt(10, student.getStuId());
        return pstmt.executeUpdate();
    }

    public boolean getStudentByGradeId(Connection con, String gradeId) throws Exception {
        String sql = "select * from t_student where gradeId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, gradeId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getStudentByFacultyId(Connection con, String facultyId) throws Exception {
        String sql = "select * from t_student where facultyId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, facultyId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean getStudentByDormitoryId(Connection con, String dormitoryId) throws Exception {
        String sql = "select * from t_student where dormitoryId=?";
        PreparedStatement pstmt = con.prepareStatement(sql);
        pstmt.setString(1, dormitoryId);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            return true;
        } else {
            return false;
        }
    }
}
