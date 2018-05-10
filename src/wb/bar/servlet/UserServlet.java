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

import wb.bar.entity.Computer;
import wb.bar.entity.Member;
import wb.bar.entity.User;
import wb.bar.service.ComputerService;
import wb.bar.service.UserService;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();
	private Member member = new Member();
	private User user  = new User();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
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
		response.setHeader("Access-Control-Allow-Methods", "POST");
		response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		response.addHeader("Access-Control-Allow-Credentials", "true");
		response.setContentType("application/json;charset=UTF-8");
		Map<String, Object> result = new HashMap<String, Object>();
		
		
		String method =  request.getParameter("method");
		switch(method){
		case "addUser": addUser(request, result);break;
		case "getAllUser": getAllUser(request,result);break;
		case "getUserTotal": getUserTotal(request,result);break;
		case "editUser": editUser(request,result);break;
		case "deleteUser": deleteUser(request,result);break;
		case "getUserById": selectUserById(request,result);break;
		case "updateStatus" : updateStatus(request,result);break;
		case "selectMessageByComputerId": selectMessageByComputerId(request,result);break;
		case "updataBalance": updataBalance(request,result); break;
		case "checkout": checkout(request,result); break;
		default : result.put("message", "未找到方法");result.put("status", false);break;
		}
		
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(result));
		out.flush();
		out.close();
		
	}
	
	private void addUser(HttpServletRequest request, Map<String, Object> result){
		member.setId(Integer.parseInt(request.getParameter("member_id")));
		user.setMember(member);
		user.setIdname(request.getParameter("idname"));
		user.setIdcard(request.getParameter("idcard"));
		user.setPassword(request.getParameter("password"));
		user.setStatus(0);
		user.setBalance(Integer.parseInt(request.getParameter("balance")));
		boolean status = userService.addUser(user);
		if(status){
			result.put("message", "新增成功");
			result.put("status", true);
		}else{
			result.put("message", "新增失败");
			result.put("status", false);
		}
	
	}
	
	private void getAllUser(HttpServletRequest request, Map<String, Object> result){
		List<Map<String, Object>> map = userService.selectAll(request.getParameter("page"));
		if(map != null&& map.size() >0&&!map.get(0).isEmpty()){
			result.put("message", "获取列表成功");
			result.put("status", true);
		}else{
			result.put("message", "获取列表失败");
			result.put("status", false);
		}
		String resultJson1 = JSON.toJSONString(map);
		result.put("data", resultJson1);
	}
	
	private void getUserTotal(HttpServletRequest request, Map<String, Object> result){
		List<Map<String, Object>> map = userService.getTotalCount();
		if(map != null&& map.size() >0&&!map.get(0).isEmpty()){
			result.put("totalCount", map.get(0).get("totalCount"));
			result.put("message", "获取用户总数成功");
			result.put("status", true);
		}
		else{
			result.put("message", "获取用户总数失败");
			result.put("status", false);
		}
		
	}
	
	
	private void editUser(HttpServletRequest request, Map<String, Object> result){
		user.setId(Integer.parseInt(request.getParameter("id")));
		user.setIdname(request.getParameter("idname"));
		user.setIdcard(request.getParameter("idcard"));
		user.setPassword(request.getParameter("password"));
		user.setBalance(Integer.parseInt(request.getParameter("balance")));
		member.setId(Integer.parseInt(request.getParameter("member_id")));
		user.setMember(member);
		boolean status = userService.editUser(user);
		if(status){
			result.put("message", "编辑成功");
			result.put("status", true);
		}
		else{
			result.put("message", "编辑失败");
			result.put("status", false);
		}
	}
	
	private void deleteUser(HttpServletRequest request, Map<String, Object> result){
		user.setId(Integer.parseInt(request.getParameter("id")));
		boolean status = userService.deleteUser(user);
		if(status){
			result.put("message", "删除成功");
			result.put("status", true);
		}
		else{
			result.put("message", "删除失败");
			result.put("status", false);
		}
	}
	
	private void selectUserById(HttpServletRequest request, Map<String, Object> result){
		user.setId(Integer.parseInt(request.getParameter("id")));
		List<Map<String, Object>> map = userService.selectUserById(user);
		if(map != null&& map.size() >0&&!map.get(0).isEmpty()){
			result.put("message", "获取成功");
			result.put("status", true);
		}else{
			result.put("message", "获取失败");
			result.put("status", false);
		}
		String resultJson1 = JSON.toJSONString(map);
		result.put("data", resultJson1);
	}
	
	private void updateStatus(HttpServletRequest request, Map<String, Object> result){
		user.setId(Integer.parseInt(request.getParameter("id")));
		user.setStatus(Integer.parseInt(request.getParameter("status")));
		boolean status = userService.updateStatus(user);
		if(status){
			result.put("message", "获取成功");
			result.put("status", true);
		}else{
			result.put("message", "获取失败");
			result.put("status", false);
		}
	}
	
	private void selectMessageByComputerId(HttpServletRequest request, Map<String, Object> result){
		int computerId = Integer.parseInt(request.getParameter("computerId"));
		List<Map<String, Object>> map = userService.selectMessageByComputerId(computerId);
		if(map != null&& map.size() >0&&!map.get(0).isEmpty()){
			result.put("message", "获取成功");
			result.put("status", true);
			String resultJson1 = JSON.toJSONString(map.get(0));
			result.put("data", resultJson1);
		}else{
			result.put("message", "获取失败");
			result.put("status", false);
		}
	}
	
	private void updataBalance(HttpServletRequest request, Map<String, Object> result){
		int userId = Integer.parseInt(request.getParameter("userId"));
		String balance = request.getParameter("balance");
		boolean status = userService.updataBalance(userId, balance);
		if(status){
			result.put("message", "扣费成功");
			result.put("status", true);
		}else{
			result.put("message", "扣费失败");
			result.put("status", false);
		}
	}
	
	private void checkout(HttpServletRequest request, Map<String, Object> result){
		user.setId(Integer.parseInt(request.getParameter("userId")));
		user.setStatus(1);
		boolean status1 = userService.updateStatus(user);
		
		ComputerService computerS = new ComputerService();
		int computerId = Integer.parseInt(request.getParameter("computerId"));
		boolean status2 = computerS.checkoutComputer(computerId);
		
		if(status1 && status2){
			result.put("message", "下机成功");
			result.put("status", true);
		}else{
			result.put("message", "下机失败");
			result.put("status", false);
		}
	}

}
