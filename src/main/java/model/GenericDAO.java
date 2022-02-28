package model;

import java.sql.SQLException;
import java.util.List;

public interface GenericDAO<T> {
	
	public List<T> queryAll() throws SQLException;
	
	public T queryByNumber(String number) throws SQLException;
	
	public void insertData(T javaBean) throws SQLException;
	
	public void updateData(T javaBean) throws SQLException;
	
	public void deleteData(String number) throws SQLException;

}
