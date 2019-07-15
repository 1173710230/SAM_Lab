package com.lsq.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lsq.dao.GradeDao;
import com.lsq.model.Grade;
import com.lsq.model.PageBean;
import com.lsq.util.DbUtil;
import com.lsq.util.JsonUtil;
import com.lsq.util.ResponseUtil;

public class GradeListServlet extends HttpServlet {
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
		String page = request.getParameter("page");
		String rows = request.getParameter("rows");
		String gradeName = request.getParameter("gradeName");
		if (gradeName == null) {
			gradeName = "";
		}
		Grade grade = new Grade();
		grade.setGradeName(gradeName);
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con1 = null;
		Connection con2 = null;
		Connection con3 = null;
		try {
			con1 = dbUtil.getCon1();
			con2 = dbUtil.getCon2();
			con3 = dbUtil.getCon3();
			JSONObject result = new JSONObject();
			JSONArray jsonArray1 = JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con1, pageBean, grade));
			JSONArray jsonArray2 = JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con2, pageBean, grade));
			JSONArray jsonArray3 = JsonUtil.formatRsToJsonArray(gradeDao.gradeList(con3, pageBean, grade));
			int total1 = gradeDao.gradeCount(con1, grade);
			int total2 = gradeDao.gradeCount(con2, grade);
			int total3 = gradeDao.gradeCount(con3, grade);
			//int total = total1 + total2 + total3;
			//jsonArray1.addAll(jsonArray2);
			//jsonArray1.addAll(jsonArray3);
			result.put("rows", jsonArray1);
			result.put("total", total1);
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon1(con1);
				//dbUtil.closeCon2(con2);
				//dbUtil.closeCon3(con3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
