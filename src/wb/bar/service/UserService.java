package wb.bar.service;

import java.util.List;
import java.util.Map;

import wb.bar.dao.UserDao;
import wb.bar.entity.User;

public class UserService {
	UserDao userDao = new UserDao();
	
	public boolean addUser(User user){
		return userDao.addUser(user);
	}
	
	public List<Map<String, Object>> selectAll(String page){
		return userDao.selectAll(page);
	}
	
	public boolean editUser(User user){
		return userDao.editUser(user);
	}
	
	public boolean deleteUser(User user){
		return userDao.deleteUser(user);
	}
	
	public List<Map<String, Object>> selectUserById(User user){
		return userDao.selectUserById(user);
	}
	
	public List<Map<String, Object>> getTotalCount(){
		return userDao.getTotalCount();
	}

	public boolean updateStatus(User user){
		return userDao.updateStatus(user);
	}
	
	public List<Map<String, Object>> selectMessageByComputerId(int computerId){
		return userDao.selectMessageByComputerId(computerId);
	}
	
	public boolean updataBalance(int userId, String balance){
		return userDao.updataBalance(userId, balance);
	}
}
