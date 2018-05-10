package wb.bar.service;

import java.util.List;
import java.util.Map;

import wb.bar.dao.ComputerDao;
import wb.bar.entity.Computer;

public class ComputerService {
	ComputerDao computerDao = new ComputerDao();
	
	public boolean addComputer(Computer computer){
		return computerDao.addComputer(computer);
	}
	
	public List<Map<String, Object>> selectAll(String page){
		return computerDao.selectAll(page);
	}
	
	public boolean editComputer(Computer computer){
		return computerDao.editComputer(computer);
	}
	
	public boolean deleteComputer(Computer computer){
		return computerDao.deleteComputer(computer);
	}
	
	public List<Map<String, Object>> selectComputerById(Computer computer){
		return computerDao.selectComputerById(computer);
	}
	
	public List<Map<String, Object>> getTotalCount(){
		return computerDao.getTotalCount();
	}
	
	public boolean addUser(Computer computer){
		return computerDao.addUser(computer);
	}
	
	public boolean checkoutComputer(int computerId){
		return computerDao.checkoutComputer(computerId);
	}
	

}
