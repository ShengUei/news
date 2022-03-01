package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import util.ConnectionFactory;

public class MemberDAOImpl implements GenericDAO<MemberBean>{
	
	private Connection conn;
	
	public MemberDAOImpl() {
		this.conn = ConnectionFactory.getConnection();
	}

	@Override
	public List<MemberBean> queryAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public MemberBean queryByName(String account) throws SQLException {
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
		
		return member;
	}

	@Override
	public void insertData(MemberBean member) throws SQLException {
		String sqlStr = "INSERT INTO member('username', 'account', 'hashed_pwd', 'salt') VALUES(?, ?, ?, ?)";
		
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, member.getUsername());
		preState.setString(2, member.getAccount());
		preState.setString(3, member.getHashed_pwd());
		preState.setString(4, member.getSalt());
		
		preState.executeUpdate();
		
		preState.close();
		
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
