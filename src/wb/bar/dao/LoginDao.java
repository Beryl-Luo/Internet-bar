package wb.bar.dao;

import java.util.List;
import java.util.Map;

import wb.bar.entity.Admin;
import wb.bar.entity.User;
import wb.bar.util.BeanToMapUtil;
import wb.bar.util.ConnectMYSQL;

public class LoginDao {
	ConnectMYSQL connectMYSQL = new ConnectMYSQL();
	BeanToMapUtil beanUtils = new BeanToMapUtil();

	public Admin selectAdminById(Admin admin){
		Admin ad = new Admin();
		try {
			String sql = "select *from admin where name='"+admin.getName()+"'";
			if(connectMYSQL.excuteQuery(sql, null).size()>0 && connectMYSQL.excuteQuery(sql, null)!=null){
				return (Admin) beanUtils.mapToBean(connectMYSQL.excuteQuery(sql, null).get(0), Admin.class);
			}else{
				return ad;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return null;
	}
	
	public User selectClientById(User user){
		User u = new User();
		try {
			String sql = "select *from user where idcard='"+user.getIdcard()+"'";
			List<Map<String, Object>>  list = connectMYSQL.excuteQuery(sql, null);
			if(list.size()>0 && list!=null){
				Map<String, Object> map = list.get(0);
				u.setId(Integer.parseInt(map.get("id").toString()));
				u.setIdcard(map.get("idcard").toString());
				u.setPassword(map.get("password").toString());
				return u;
			}else{
				return u;
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return null;
	}

}
