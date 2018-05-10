package wb.bar.service;

import java.util.List;

import wb.bar.dao.ComputerAreaDao;
import wb.bar.entity.Computer;
import wb.bar.entity.ComputerArea;

public class ComputerAreaService {
	ComputerAreaDao computerAreaDao = new ComputerAreaDao();
	public List<ComputerArea> selectAll(){
		return computerAreaDao.selectAll();
	}

}
