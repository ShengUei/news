package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConnectionFactory;

public class HouseInfoDAOImpl implements HouseInfoDAO<HouseInfoBean>{
	
	private Connection conn;
	
	public HouseInfoDAOImpl() {
		this.conn = ConnectionFactory.getConnection();
	}

	@Override
	public List<HouseInfoBean> queryHouseInfo() throws SQLException {
		String sqlStr = "SELECT * FROM houseInfo";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		ResultSet rs = preState.executeQuery();
		
		List<HouseInfoBean> list = new ArrayList<HouseInfoBean>();
		
		while (rs.next()) {
			HouseInfoBean houseInfo = new HouseInfoBean();
			
			houseInfo.setH_hosueNo(rs.getString("h_houseNo"));
			houseInfo.setH_title(rs.getString("h_title"));
			houseInfo.setH_address(rs.getString("h_address"));
			houseInfo.setH_type(rs.getInt("h_type"));
			houseInfo.setH_about(rs.getString("h_about"));
			
			list.add(houseInfo);
		}
		
		rs.close();
		preState.close();
		
		return list;
	}
	
	public List<HouseInfoBean> queryHouseInfoByH_type(String h_type) throws SQLException {
		String sqlStr = "SELECT * FROM houseInfo WHERE h_type = ?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, h_type);
		
		ResultSet rs = preState.executeQuery();
		
		List<HouseInfoBean> list = new ArrayList<HouseInfoBean>();
		
		while (rs.next()) {
			HouseInfoBean houseInfo = new HouseInfoBean();
			
			houseInfo.setH_hosueNo(rs.getString("h_hosueNo"));
			houseInfo.setH_title(rs.getString("h_title"));
			houseInfo.setH_address(rs.getString("h_address"));
			houseInfo.setH_type(rs.getInt("h_type"));
			houseInfo.setH_about(rs.getString("h_about"));
			
			list.add(houseInfo);
		}
		
		rs.close();
		preState.close();
		
		return list;
	}
	
	@Override
	public void insertHouseInfo(HouseInfoBean houseInfo) throws SQLException {
		String sqlStr = "INSERT INTO houseInfo VALUES(?, ?, ?, ?, ?, ?)";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, houseInfo.getH_hosueNo());
		preState.setString(2, "account");
		preState.setString(3, houseInfo.getH_title());
		preState.setString(4, houseInfo.getH_address());
		preState.setInt(5, houseInfo.getH_type());
		preState.setString(6, houseInfo.getH_about());
		
		preState.executeUpdate();
		
		preState.close();
		
	}
	
	@Override
	public void updateHouseInfo(HouseInfoBean houseInfo) throws SQLException {
		String sqlStr = "UPDATE houseInfo SET h_title = ?, h_address = ?, h_type = ?, h_about = ? WHERE h_hosueNo = ?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, houseInfo.getH_title());
		preState.setString(2, houseInfo.getH_address());
		preState.setInt(3, houseInfo.getH_type());
		preState.setString(4, houseInfo.getH_about());
		preState.setString(5, houseInfo.getH_hosueNo());
		
		preState.executeUpdate();
		
		preState.close();
		
	}
	
	@Override
	public void deleteHouseInfo(HouseInfoBean houseInfo) throws SQLException {
		String sqlStr = "DELETE FROM houseInfo WHERE h_hosueNo = ?";
		PreparedStatement preState = conn.prepareStatement(sqlStr);
		
		preState.setString(1, houseInfo.getH_hosueNo());
		
		preState.execute();
		
		preState.close();
		
	}

}
