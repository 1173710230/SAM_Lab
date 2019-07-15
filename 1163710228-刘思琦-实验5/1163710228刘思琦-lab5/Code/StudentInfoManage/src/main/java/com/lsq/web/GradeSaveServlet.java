package com.lsq.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.lsq.dao.GradeDao;
import com.lsq.model.Grade;
import com.lsq.util.DbUtil;
import com.lsq.util.ResponseUtil;
import com.lsq.util.StringUtil;

public class GradeSaveServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	GradeDao gradeDao = new GradeDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String gradeName = request.getParameter("gradeName");
		String gradeDesc = request.getParameter("gradeDesc");
		String id = request.getParameter("id");
		Grade grade = new Grade(gradeName, gradeDesc);
		if (StringUtil.isNotEmpty(id)) {
			grade.setId(Integer.parseInt(id));
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
			if (StringUtil.isNotEmpty(id)) {
				saveNums1 = gradeDao.gradeModify(con1, grade);
				saveNums2 = gradeDao.gradeModify(con2, grade);
				saveNums3 = gradeDao.gradeModify(con3, grade);
				saveNums = saveNums1 + saveNums2 + saveNums3;
			} else {
				saveNums1 = gradeDao.gradeAdd(con1, grade);
				saveNums2 = gradeDao.gradeModify(con2, grade);
				saveNums3 = gradeDao.gradeModify(con3, grade);
				saveNums = saveNums1 + saveNums2 + saveNums3;
			}
			if (saveNums > 0) {
				result.put("success", "true");
			} else {
				result.put("success", "true");
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
