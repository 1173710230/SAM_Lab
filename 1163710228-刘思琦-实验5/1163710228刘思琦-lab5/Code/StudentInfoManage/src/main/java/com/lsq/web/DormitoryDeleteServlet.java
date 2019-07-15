package com.lsq.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.lsq.dao.DormitoryDao;
import com.lsq.dao.StudentDao;
import com.lsq.util.DbUtil;
import com.lsq.util.ResponseUtil;

public class DormitoryDeleteServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	DormitoryDao dormitoryDao = new DormitoryDao();
	StudentDao studentDao = new StudentDao();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String delIds = request.getParameter("delIds");
		Connection con1 = null;
		Connection con2 = null;
		Connection con3 = null;

		try {
			con1 = dbUtil.getCon1();
			con2 = dbUtil.getCon2();
			con3 = dbUtil.getCon3();
			JSONObject result = new JSONObject();
			String str[] = delIds.split(",");
			for (int i = 0; i < str.length; i++) {
				boolean f1 = studentDao.getStudentByDormitoryId(con1, str[i]);
				boolean f2 = studentDao.getStudentByDormitoryId(con2, str[i]);
				boolean f3 = studentDao.getStudentByDormitoryId(con3, str[i]);
				if (f1 || f2 || f3) {
					result.put("errorIndex", i);
					result.put("errorMsg", "宿舍下面有学生，不能删除！");
					ResponseUtil.write(response, result);
					return;
				}
			}
			int delNums1 = dormitoryDao.dormitoryDelete(con1, delIds);
			int delNums2 = dormitoryDao.dormitoryDelete(con2, delIds);
			int delNums3 = dormitoryDao.dormitoryDelete(con3, delIds);
			int delNums = delNums1 + delNums2 + delNums3;
			if (delNums > 0) {
				result.put("success", "true");
				result.put("delNums", delNums);
			} else {
				result.put("errorMsg", "删除失败");
			}
			ResponseUtil.write(response, result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				dbUtil.closeCon1(con1);
				dbUtil.closeCon2(con3);
				dbUtil.closeCon3(con3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
