package com.lsq.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lsq.model.Dormitory;
import com.lsq.model.PageBean;
import com.lsq.util.StringUtil;

public class DormitoryDao {

	public ResultSet dormitoryList(Connection con, PageBean pageBean, Dormitory dormitory) throws Exception {
		StringBuffer sb = new StringBuffer("select * from t_dormitory");
		if (dormitory != null && StringUtil.isNotEmpty(dormitory.getDormitoryName())) {
			sb.append(" and dormitoryName like '%" + dormitory.getDormitoryName() + "%'");
		}
		if (pageBean != null) {
			sb.append(" limit " + pageBean.getStart() + "," + pageBean.getRows());
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}

	public int dormitoryCount(Connection con, Dormitory dormitory) throws Exception {
		StringBuffer sb = new StringBuffer("select count(*) as total from t_dormitory");
		if (StringUtil.isNotEmpty(dormitory.getDormitoryName())) {
			sb.append(" and dormitoryName like '%" + dormitory.getDormitoryName() + "%'");
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
	public int dormitoryDelete(Connection con, String delIds) throws Exception {
		String sql = "delete from t_dormitory where id in(" + delIds + ")";
		PreparedStatement pstmt = con.prepareStatement(sql);
		return pstmt.executeUpdate();
	}

	public int dormitoryAdd(Connection con, Dormitory dormitory) throws Exception {
		String sql = "insert into t_dormitory values(null,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dormitory.getDormitoryName());
		pstmt.setString(2, dormitory.getDormitoryDesc());
		return pstmt.executeUpdate();
	}

	public int dormitoryModify(Connection con, Dormitory dormitory) throws Exception {
		String sql = "update t_dormitory set dormitoryName=?,dormitoryDesc=? where id=?";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, dormitory.getDormitoryName());
		pstmt.setString(2, dormitory.getDormitoryDesc());
		pstmt.setInt(3, dormitory.getId());
		return pstmt.executeUpdate();
	}

}
