package wb.bar.service;

import java.util.HashMap;
import java.util.Map;

import wb.bar.dao.ComputerDao;
import wb.bar.dao.LoginDao;
import wb.bar.dao.UserDao;
import wb.bar.entity.Admin;
import wb.bar.entity.Computer;
import wb.bar.entity.User;

public class LoginService {
	LoginDao loginDao = new LoginDao();
	
	public Map<String, Object> selectAdminById(Admin admin){
		Map<String, Object> result = new HashMap<String, Object>();
		Admin ad = loginDao.selectAdminById(admin);
		if(ad.getPassword() == "" || ad.getPassword() == null){
			result.put("message", "ÓÃ»§Ãû´íÎó!");
			result.put("status", false);
		}else if(ad.getPassword().equals(admin.getPassword())){
			result.put("message", "µÇÂ¼³É¹¦£¡");
			result.put("status", true);
			
		}else{
			result.put("message", "ÃÜÂë´íÎó£¡");
			result.put("status", false);
		}
		return result;
	}
	
	public Map<String, Object> selectClientById(User user, String computerId){
		Map<String, Object> result = new HashMap<String, Object>();
		User cli = loginDao.selectClientById(user);
		if(cli.getPassword() == "" || cli.getPassword() == null){
			result.put("message", "ÓÃ»§´íÎó!");
			result.put("status", false);
		}else if(cli.getPassword().equals(user.getPassword())){
			result.put("message", "µÇÂ¼³É¹¦£¡");
			result.put("status", true);
			ComputerDao computerDao = new ComputerDao();
			UserDao userDao = new UserDao();
			Computer computer = new Computer();
			computer.setUser(cli);
			computer.setId(Integer.parseInt(computerId));
			computerDao.addUser(computer);
			
			userDao.updateStatus(cli);
		}else{
			result.put("message", "ÃÜÂë´íÎó£¡");
			result.put("status", false);
		}
		return result;
	}

}
