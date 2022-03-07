package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public class MemberDAOImpl implements GenericDAO<MemberBean>{
	private DataSource dataSource;
	private Connection conn;
	
	public MemberDAOImpl(DataSource dataSource) {
		this.dataSource = dataSource;
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
			member.setEncrypt_pwd(rs.getString("encrypt_pwd"));
			member.setSalt(rs.getString("salt"));
		}
		
		rs.close();
		preState.close();
		conn.close();
		
		return member;
	}
	
	public Boolean isOnline(String account) throws SQLException {
		conn = dataSource.getConnection();
		
		String sqlStr = "SELECT is_online FROM member WHERE account = ?;";
		Boolean isOnline;
		
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, account);
		
		ResultSet rs = preState.executeQuery();
		
		rs.next();
		isOnline = rs.getBoolean("is_online");
		
		rs.close();
		preState.close();
		conn.close();
		
		return isOnline;
		
	}

	@Override
	public void insertData(MemberBean member) throws SQLException {
		conn = dataSource.getConnection();
		
		String sqlStr = "INSERT INTO member(username, account, encrypt_pwd, salt, is_online) VALUES(?, ?, ?, ?, ?)";
		
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, member.getUsername());
		preState.setString(2, member.getAccount());
		preState.setString(3, member.getEncrypt_pwd());
		preState.setString(4, member.getSalt());
		preState.setBoolean(5, true);
		
		preState.executeUpdate();
		
		preState.close();
		conn.close();
		
	}

	@Override
	public void updateData(MemberBean javaBean) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	public void updateOnline(String account, Boolean isOnline) throws SQLException {
		conn = dataSource.getConnection();
		
		String sqlStr = "UPDATE member SET is_online = ? WHERE account = ?;";
		
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setBoolean(1, isOnline);
		preState.setString(2, account);
		
		preState.executeUpdate();
		
		preState.close();
		conn.close();
		
	}

	@Override
	public void deleteData(String number) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	

}
