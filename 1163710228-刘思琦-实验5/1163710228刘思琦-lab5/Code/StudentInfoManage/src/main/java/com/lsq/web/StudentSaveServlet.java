package com.lsq.web;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import io.shardingsphere.shardingjdbc.api.yaml.YamlShardingDataSourceFactory;
import net.sf.json.JSONObject;

import com.lsq.dao.StudentDao;
import com.lsq.model.Student;
import com.lsq.util.DateUtil;
import com.lsq.util.DbUtil;
import com.lsq.util.ResponseUtil;
import com.lsq.util.StringUtil;

public class StudentSaveServlet extends HttpServlet {

    int count = 0;

    DbUtil dbUtil = new DbUtil();
    StudentDao studentDao = new StudentDao();

    public StudentSaveServlet() throws IOException, SQLException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        String stuNo = request.getParameter("stuNo");
        String stuName = request.getParameter("stuName");
        String sex = request.getParameter("sex");
        String birthday = request.getParameter("birthday");
        String facultyId = request.getParameter("facultyId");
        String gradeId = request.getParameter("gradeId");
        String dormitoryId = request.getParameter("dormitoryId");
        String email = request.getParameter("email");
        String stuDesc = request.getParameter("stuDesc");
        String stuId = request.getParameter("stuId");

        Student student = null;
        try {
            student = new Student(stuNo, stuName, sex, DateUtil.formatString(birthday, "yyyy-MM-dd"),
                    Integer.parseInt(facultyId), Integer.parseInt(gradeId), Integer.parseInt(dormitoryId), email, stuDesc);
        } catch (Exception e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        if (StringUtil.isNotEmpty(stuId)) {
            student.setStuId(Integer.parseInt(stuId));
        }
        Connection con1 = null;
        Connection con2 = null;
        Connection con3 = null;
        try {
            con1 = dbUtil.getCon1();
            con2 = dbUtil.getCon2();
            con3 = dbUtil.getCon3();
            int saveNums1 = 0;
            int saveNums2 = 0;
            int saveNums3 = 0;
            int saveNums = 0;
            JSONObject result = new JSONObject();
            //System.out.println(stuId);
            if (StringUtil.isNotEmpty(stuId)) {
                saveNums1 = studentDao.studentModify(con1, student);
                saveNums2 = studentDao.studentModify(con2, student);
                saveNums3 = studentDao.studentModify(con3, student);
                saveNums = saveNums1 + saveNums2 + saveNums3;
            } else {
				count++;
				if (count % 3 == 0) {
                    saveNums1 = studentDao.studentAdd(con1, student);
                } else if (count % 3 == 1) {
                    saveNums2 = studentDao.studentAdd(con2, student);
                } else {
                    saveNums3 = studentDao.studentAdd(con3, student);
                }
                saveNums = saveNums1 + saveNums2 + saveNums3;
				System.out.println(saveNums);
            }
            if (saveNums > 0) {
                result.put("success", "true");
            } else {
                result.put("errorMsg", "±£¥Ê ß∞‹");
            }
            ResponseUtil.write(response, result);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                dbUtil.closeCon1(con1);
                dbUtil.closeCon2(con2);
                dbUtil.closeCon3(con3);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

}
