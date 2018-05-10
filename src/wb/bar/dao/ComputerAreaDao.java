package wb.bar.dao;

import java.util.List;
import wb.bar.entity.ComputerArea;
import wb.bar.util.BeanToMapUtil;
import wb.bar.util.ConnectMYSQL;

public class ComputerAreaDao {
	ConnectMYSQL connectMYSQL = new ConnectMYSQL();
	BeanToMapUtil<ComputerArea> beanUtils = new BeanToMapUtil<ComputerArea>();

	public List<ComputerArea> selectAll(){
		try {
			String sql = "select *from computer_area";
			return beanUtils.ListMap2JavaBean(connectMYSQL.excuteQuery(sql, null), ComputerArea.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return null;
	}

}
