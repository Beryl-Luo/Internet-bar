package wb.bar.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import wb.bar.entity.Member;
import wb.bar.service.MemberService;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class MemberServlet
 */
@WebServlet("/MemberServlet")
public class MemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	MemberService memberService = new MemberService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("application/json;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		String method =  request.getParameter("method");
		switch(method){
		case "getAllMember": getAllMember(request, result);break;
		default : result.put("message", "未找到方法");result.put("status", false);break;
		}
		
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(result));
		out.flush();
		out.close();
	}
	
	private void getAllMember(HttpServletRequest request, Map<String, Object> result){
		List<Member> map = memberService.selectAll();
		if(map != null&& map.size() >0){
			result.put("message", "获取列表成功");
			result.put("status", true);
		}else{
			result.put("message", "获取列表失败");
			result.put("status", false);
		}
		String resultJson1 = JSON.toJSONString(map);
		result.put("data", resultJson1);
	}

}
