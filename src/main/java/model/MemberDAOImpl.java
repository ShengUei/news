package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class MemberDAOImpl implements GenericDAO<MemberBean>{
	private DataSource dataSource;
	private Connection conn;
	
	public MemberDAOImpl() {
		try {
			Context initialContext = new InitialContext();
			Context envContext = (Context) initialContext.lookup("java:/comp/env");
			dataSource = (DataSource) envContext.lookup("jdbc/SideProject1_News");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<MemberBean> queryAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MemberBean queryByName(String account) throws SQLException {
		conn = dataSource.getConnection();
		
		MemberBean member = new MemberBean();
		
		String sqlStr = "SELECT * FROM member WHERE account = ?;";
		
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, account);
		
		ResultSet rs = preState.executeQuery();
		
		while(rs.next()) {
			member.setUsername(rs.getString("username"));
			member.setAccount(rs.getString("account"));
			member.setHashed_pwd(rs.getString("hashed_pwd"));
			member.setSalt(rs.getString("salt"));
		}
		
		rs.close();
		preState.close();
		conn.close();
		
		return member;
	}

	@Override
	public void insertData(MemberBean member) throws SQLException {
		conn = dataSource.getConnection();
		
		String sqlStr = "INSERT INTO member(username, account, hashed_pwd, salt) VALUES(?, ?, ?, ?)";
		
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, member.getUsername());
		preState.setString(2, member.getAccount());
		preState.setString(3, member.getHashed_pwd());
		preState.setString(4, member.getSalt());
		
		preState.executeUpdate();
		
		preState.close();
		conn.close();
		
	}

	@Override
	public void updateData(MemberBean javaBean) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteData(String number) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	

}
