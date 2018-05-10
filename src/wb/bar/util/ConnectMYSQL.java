package wb.bar.util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectMYSQL {
	//路径
	private static final String url = "jdbc:mysql://localhost:3306/internet-bar?Unicode=true&characterEncoding=UTF-8";
	
	// 用户名
	private static final String username = "root";

	// 密码
	private static final String password = "root";
	
	// 数据库驱动
	private static final String jdbcDriver = "com.mysql.jdbc.Driver";
	
	public Connection getConnection(){
		Connection con = null ;
		try{
			Class.forName(jdbcDriver);
			con = DriverManager.getConnection(url, username, password);
		}catch(Exception ce){
			System.out.println(ce);
		}
		return con;
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>>  excuteQuery(String sql, Object[] o) throws Exception {
		ResultSet resultSet = null;
		Connection connection = null;
		Statement s = null;
		try{
			connection = getConnection();
			s = connection.createStatement();
			System.out.println("sql:"+sql);
			resultSet = s.executeQuery(sql);
			return (List<Map<String, Object>>) doHander(resultSet);
		}catch(SQLException ce){
			System.out.println(ce);
		}finally {
			s.close();
			connection.close();
			resultSet.close();
			
		}
		return null;
	}
	
	public boolean  executeUpdate(String sql, Object[] o) throws Exception {
		Connection connection = null;
		Statement s = null;
		try{
			connection = getConnection();
			s = connection.createStatement();
			System.out.println("sql:"+sql);
			int result = s.executeUpdate(sql);
			return (result==0)?false:true;
		}catch(SQLException ce){
			System.out.println(ce);
		}finally {
			s.close();
			connection.close();
			
		}
		return false;
	}

	private Object  doHander(ResultSet rs) throws Exception {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		ResultSetMetaData resultSetMetaData = rs.getMetaData();
		int cols = resultSetMetaData.getColumnCount();
		Map<String, Object> hashMap = null;
		while (rs.next()) {
			hashMap = new HashMap<String, Object>();
			for (int i = 1; i <= cols; i++) {
				hashMap.put(resultSetMetaData.getColumnLabel(i),
						rs.getObject(i));

			}

			result.add(hashMap);

		}
		return result;
	}
}
