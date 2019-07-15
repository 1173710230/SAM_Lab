package com.lsq.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lsq.dao.StudentDao;
import com.lsq.model.PageBean;
import com.lsq.model.Student;
import com.lsq.util.DbUtil;
import com.lsq.util.JsonUtil;
import com.lsq.util.ResponseUtil;
import com.lsq.util.StringUtil;

import com.alibaba.fastjson.*;

public class StudentListServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	StudentDao studentDao = new StudentDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stuNo = request.getParameter("stuNo");
		String stuName = request.getParameter("stuName");
		String sex = request.getParameter("sex");
		String bbirthday = request.getParameter("bbirthday");
		String ebirthday = request.getParameter("ebirthday");
		String facultyId = request.getParameter("facultyId");
		String gradeId = request.getParameter("gradeId");
		String dormitoryId = request.getParameter("dormitoryId");

		Student student = new Student();
		if (stuNo != null) {
			student.setStuNo(stuNo);
			student.setStuName(stuName);
			student.setSex(sex);
			if (StringUtil.isNotEmpty(facultyId)) {
				student.setFacultyId(Integer.parseInt(facultyId));
			}
			if (StringUtil.isNotEmpty(gradeId)) {
				student.setGradeId(Integer.parseInt(gradeId));
			}
			if (StringUtil.isNotEmpty(dormitoryId)) {
				student.setDormitoryId(Integer.parseInt(dormitoryId));
			}
		}

		String page = request.getParameter("page");
		String rows = request.getParameter("rows");

		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con1 = null;
		Connection con2 = null;
		Connection con3 = null;
		try {
			con1 = dbUtil.getCon1();
			con2 = dbUtil.getCon2();
			con3 = dbUtil.getCon3();
			JSONObject result = new JSONObject();
			JSONArray jsonArray1 = JsonUtil.formatRsToJsonArray(studentDao.studentList(con1, pageBean, student, bbirthday, ebirthday));
			JSONArray jsonArray2 = JsonUtil.formatRsToJsonArray(studentDao.studentList(con2, pageBean, student, bbirthday, ebirthday));
			JSONArray jsonArray3 = JsonUtil.formatRsToJsonArray(studentDao.studentList(con3, pageBean, student, bbirthday, ebirthday));
			jsonArray1.addAll(jsonArray2);
			jsonArray1.addAll(jsonArray3);
			int total1 = studentDao.studentCount(con1, student, bbirthday, ebirthday);
			int total2 = studentDao.studentCount(con2, student, bbirthday, ebirthday);
			int total3 = studentDao.studentCount(con3, student, bbirthday, ebirthday);
			int total = total1 + total2 + total3;
			result.put("rows", jsonArray1);
			//result.put("rows", jsonArray2);
			//result.put("rows", jsonArray3);
			result.put("total", total);
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
