package wb.bar.dao;

import java.util.List;

import wb.bar.entity.Member;
import wb.bar.util.BeanToMapUtil;
import wb.bar.util.ConnectMYSQL;

public class MemberDao {
	ConnectMYSQL connectMYSQL = new ConnectMYSQL();
	BeanToMapUtil<Member> beanUtils = new BeanToMapUtil<Member>();

	public List<Member> selectAll(){
		try {
			String sql = "select *from member";
			return beanUtils.ListMap2JavaBean(connectMYSQL.excuteQuery(sql, null), Member.class);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("操作数据库出错！");
		}
		return null;
	}

}
