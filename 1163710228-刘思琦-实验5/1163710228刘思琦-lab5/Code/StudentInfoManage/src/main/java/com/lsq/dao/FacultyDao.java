package com.lsq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lsq.model.Faculty;
import com.lsq.model.PageBean;
import com.lsq.util.StringUtil;

public class FacultyDao {
	public ResultSet facultyList(Connection con, PageBean pageBean, Faculty faculty) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_faculty");
		if (faculty != null && StringUtil.isNotEmpty(faculty.getFacultyName())) {
			sb.append(" and facultyName like '%" + faculty.getFacultyName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	public int facultyCount(Connection con, Faculty faculty) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_faculty");
		if (StringUtil.isNotEmpty(faculty.getFacultyName())) {
			sb.append(" and facultyName like '%" + faculty.getFacultyName() + "%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		ResultSet rs = pstmt.executeQuery();
		if (rs.next()) {
			return rs.getInt("total");
		} else {
			return 0;
		}
	}

	/**
	 * delete from tableName where field in (1,3,5)
	 * 
	 * @param con
	 * @param delIds
	 * @return
	 * @throws Exception
	 */
	public int facultyDelete(Connection con, String delIds) throws Exception {
		String sql = "delete from t_faculty where id in(" + delIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int facultyAdd(Connection con, Faculty faculty) throws Exception {
		String sql = "insert into t_faculty values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, faculty.getFacultyName());
		pstmt.setString(2, faculty.getFacultyDesc());
		return pstmt.executeUpdate();
	}

	public int facultyModify(Connection con, Faculty faculty) throws Exception {
		String sql = "update t_faculty set facultyName=?,facultyDesc=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, faculty.getFacultyName());
		pstmt.setString(2, faculty.getFacultyDesc());
		pstmt.setInt(3, faculty.getId());
		return pstmt.executeUpdate();
	}
}
