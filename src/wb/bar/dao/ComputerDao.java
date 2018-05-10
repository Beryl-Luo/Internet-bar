package wb.bar.dao;

import java.util.List;
import java.util.Map;

import wb.bar.entity.Computer;
import wb.bar.util.BeanToMapUtil;
import wb.bar.util.ConnectMYSQL;



public class ComputerDao {
	ConnectMYSQL connectMYSQL = new ConnectMYSQL();
	BeanToMapUtil<Computer> beanUtils = new BeanToMapUtil<Computer>();
	
	public boolean addComputer(Computer computer){
		try {
			String sql = "insert into computer (number, type_id)"+
					"values('"+computer.getNumber()+"','"+computer.getComputerArea().getId()+"')";
			return connectMYSQL.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return false;
	}
	
	public List<Map<String, Object>> selectAll(String page){
		try {
			String sql = "SELECT *,computer.id id FROM computer LEFT JOIN user ON computer.user_id = user .id LEFT JOIN computer_area ON computer.type_id = computer_area.id "
					+ "ORDER BY number LIMIT "
					+ (Integer.parseInt(page)-1)*10
					+ ",10";
			return connectMYSQL.excuteQuery(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return null;
	}
	
	
	public boolean editComputer(Computer computer){
		try {
			String sql = "UPDATE computer set number="+computer.getNumber()+
					",type_id="+computer.getComputerArea().getId()+" where id="+computer.getId();
			return connectMYSQL.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return false;
	}
	
	public boolean deleteComputer(Computer computer){
		try {
			String sql = "DELETE FROM computer where id="+computer.getId();
			return connectMYSQL.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return false;
	}
	
	public List<Map<String, Object>> selectComputerById(Computer computer){
		try {
			String sql = "SELECT *FROM computer LEFT JOIN user ON computer.user_id = user .id LEFT JOIN computer_area ON computer.type_id = computer_area.id"
					+ " where computer.id="+computer.getId();
			return connectMYSQL.excuteQuery(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return null;
	}
	
	
	public List<Map<String, Object>> getTotalCount(){
		try {
			String sql = "SELECT count(*) totalCount FROM computer";
			return connectMYSQL.excuteQuery(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return null;
	}
	
	public boolean addUser(Computer computer){
		try {
			String sql = "UPDATE computer set "+
					"user_id="+computer.getUser().getId()+" where id="+computer.getId();
			return connectMYSQL.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return false;
	}
	
	public boolean checkoutComputer(int computerId){
		try {
			String sql = "UPDATE computer set "+
					"user_id=null"+" where id="+computerId;
			return connectMYSQL.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return false;
	}
	

}
