package wb.bar.service;

import java.util.List;

import wb.bar.dao.MemberDao;
import wb.bar.entity.Member;

public class MemberService {
	MemberDao memberDao = new MemberDao();
	public List<Member> selectAll(){
		return memberDao.selectAll();
	}

}
