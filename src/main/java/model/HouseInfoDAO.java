package model;

import java.sql.SQLException;
import java.util.List;

public interface HouseInfoDAO<T> {
	
	public List<T> queryHouseInfo() throws SQLException;
	
	public void insertHouseInfo(T houseInfo) throws SQLException;
	
	public void updateHouseInfo(T houseInfo) throws SQLException;
	
	public void deleteHouseInfo(T houseInfo) throws SQLException;

}
