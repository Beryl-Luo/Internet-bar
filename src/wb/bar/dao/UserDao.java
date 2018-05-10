package wb.bar.dao;

import java.util.List;
import java.util.Map;
import wb.bar.entity.User;
import wb.bar.util.ConnectMYSQL;

public class UserDao {
	ConnectMYSQL connectMYSQL = new ConnectMYSQL();

	public boolean addUser(User user){
		try {
			String sql = "insert into user (idname, idcard, password, status, balance, member_id)"+
					"values('"+user.getIdname()+"','"+user.getIdcard()+"','"+user.getPassword()+"','"+user.getStatus()+"','"+user.getBalance()+"','"+user.getMember().getId()+"')";
			return connectMYSQL.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return false;
	}
	
	public List<Map<String, Object>> selectAll(String page){
		try {
			String sql = "SELECT *,user.id id FROM user LEFT JOIN member ON user.member_id = member .id "
					+ "ORDER BY status DESC, member_id DESC LIMIT "
					+ (Integer.parseInt(page)-1)*10
					+ ",10";
			return connectMYSQL.excuteQuery(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return null;
	}
	
	
	public boolean editUser(User user){
		try {
			String sql = "UPDATE user set idcard="+user.getIdcard()+
					",password="+user.getPassword()+",balance="+user.getBalance()+",idname='"+user.getIdname()+
					"',member_id="+user.getMember().getId()+" where id="+user.getId();
			return connectMYSQL.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return false;
	}
	
	public boolean deleteUser(User user){
		try {
			String sql = "DELETE FROM user where id="+user.getId();
			return connectMYSQL.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return false;
	}
	
	public List<Map<String, Object>> selectUserById(User user){
		try {
			String sql = "SELECT *FROM user LEFT JOIN member ON user.member_id = member.id"
					+ " where user.id="+user.getId();
			return connectMYSQL.excuteQuery(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return null;
	}
	
	
	public List<Map<String, Object>> getTotalCount(){
		try {
			String sql = "SELECT count(*) totalCount FROM user";
			return connectMYSQL.excuteQuery(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return null;
	}
	
	public boolean updateStatus(User user){
		try {
			if(user.getStatus() == 0){
				user.setStatus(1);
			}else{
				user.setStatus(0);
			}
			String sql = "UPDATE user set status="+user.getStatus()+",start_at="+"NOW()"+
					" where id="+user.getId();
			return connectMYSQL.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return false;
	}
	
	public List<Map<String, Object>> selectMessageByComputerId(int computerId){
		try {
			String sql = "SELECT "+
					"computer.id computerId,"+
					"computer.number number,"+
					"computer.user_id userId,"+
					"user.idcard idcard,"+
					"user.idname idname,"+
					"user.start_at start_at,"+
					"computer_area.charging charging,"+
					"member. NAME memberName,"+
					"user.balance balance,"+
					"member.favourable favourable "+
					"FROM "+
					"computer "+
					"LEFT JOIN user "+
					"LEFT JOIN member ON user.member_id = member.id ON user.id = computer.user_id "+
					"LEFT JOIN computer_area ON computer_area.id = computer.type_id "+
					"WHERE "+
					"computer.id = "+computerId;
			return connectMYSQL.excuteQuery(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return null;
	}
	
	public boolean updataBalance(int userId, String balance){
		try {
			String sql = "UPDATE user set balance='"+balance+"'"+
					" where id="+userId;
			return connectMYSQL.executeUpdate(sql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return false;
	}
}
