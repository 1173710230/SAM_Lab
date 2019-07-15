package com.lsq.web;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.lsq.dao.DormitoryDao;
import com.lsq.model.Dormitory;
import com.lsq.model.PageBean;
import com.lsq.util.DbUtil;
import com.lsq.util.JsonUtil;
import com.lsq.util.ResponseUtil;

public class DormitoryListServlet extends HttpServlet {
	DbUtil dbUtil = new DbUtil();
	DormitoryDao dormitoryDao = new DormitoryDao();

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
		String dormitoryName = request.getParameter("dormitoryName");
		if (dormitoryName == null) {
			dormitoryName = "";
		}
		Dormitory dormitory = new Dormitory();
		dormitory.setDormitoryName(dormitoryName);
		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
		Connection con1 = null;
		Connection con2 = null;
		Connection con3 = null;
		try {
			con1 = dbUtil.getCon1();
			con2 = dbUtil.getCon2();
			con3 = dbUtil.getCon3();
			JSONObject result = new JSONObject();
			JSONArray jsonArray1 = JsonUtil.formatRsToJsonArray(dormitoryDao.dormitoryList(con1, pageBean, dormitory));
			JSONArray jsonArray2 = JsonUtil.formatRsToJsonArray(dormitoryDao.dormitoryList(con2, pageBean, dormitory));
			JSONArray jsonArray3 = JsonUtil.formatRsToJsonArray(dormitoryDao.dormitoryList(con3, pageBean, dormitory));
			int total1 = dormitoryDao.dormitoryCount(con1, dormitory);
			int total2 = dormitoryDao.dormitoryCount(con2, dormitory);
			int total3 = dormitoryDao.dormitoryCount(con3, dormitory);
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
				dbUtil.closeCon2(con2);
				dbUtil.closeCon3(con3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
