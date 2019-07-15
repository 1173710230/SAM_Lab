package com.lsq.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.lsq.dao.FacultyDao;
import com.lsq.model.Faculty;
import com.lsq.util.DbUtil;
import com.lsq.util.ResponseUtil;
import com.lsq.util.StringUtil;

public class FacultySaveServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	FacultyDao facultyDao = new FacultyDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String facultyName = request.getParameter("facultyName");
		String facultyDesc = request.getParameter("facultyDesc");
		String id = request.getParameter("id");
		Faculty faculty = new Faculty(facultyName, facultyDesc);
		if (StringUtil.isNotEmpty(id)) {
			faculty.setId(Integer.parseInt(id));
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
				saveNums1 = facultyDao.facultyModify(con1, faculty);
				saveNums2 = facultyDao.facultyModify(con2, faculty);
				saveNums3 = facultyDao.facultyModify(con3, faculty);
				saveNums = saveNums1 + saveNums2 + saveNums3;
			} else {
				saveNums1 = facultyDao.facultyAdd(con1, faculty);
				saveNums2 = facultyDao.facultyModify(con2, faculty);
				saveNums3 = facultyDao.facultyModify(con3, faculty);
				saveNums = saveNums1 + saveNums2 + saveNums3;
			}
			if (saveNums > 0) {
				result.put("success", "true");
			} else {
				result.put("success", "true");
				result.put("errorMsg", "����ʧ��");
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
