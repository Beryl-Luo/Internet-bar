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

import wb.bar.entity.Admin;
import wb.bar.entity.Computer;
import wb.bar.entity.Member;
import wb.bar.entity.User;
import wb.bar.service.ComputerService;
import wb.bar.service.LoginService;

import com.alibaba.fastjson.JSON;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginService loginService = new LoginService();
	private Admin admin = new Admin();
	private User user = new User();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		case "clientLogin": selectClientById(request, result);break;
		case "adminLogin": selectAdminById(request, result);break;
		default : result.put("message", "未找到方法");result.put("status", false);break;
		}
		
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(result));
		out.flush();
		out.close();
	}
	
	private void selectClientById(HttpServletRequest request, Map<String, Object> result){
		String computerId = request.getParameter("computerId");
		ComputerService computerService = new ComputerService();
		Computer computer = new Computer();
		computer.setId(Integer.parseInt(computerId));
		List<Map<String, Object>> mapList = computerService.selectComputerById(computer);
		if(mapList.size()>0 && mapList != null){
			if(mapList.get(0).get("user_id") != null){
				result.put("message", "慢了一步，机器已经被使用！");
				result.put("status", false);
			}else{
				user.setIdcard(request.getParameter("username"));
				user.setPassword(request.getParameter("password"));
				Map<String, Object> map = loginService.selectClientById(user, computerId);
				result.put("message", map.get("message"));
				result.put("status", map.get("status"));
			}
			
		}else{
			result.put("message", "机器不存在");
			result.put("status", false);
		}
		
	}
	
	private void selectAdminById(HttpServletRequest request, Map<String, Object> result){
		admin.setName(request.getParameter("username"));
		admin.setPassword(request.getParameter("password"));
		Map<String, Object> map = loginService.selectAdminById(admin);
		result.put("message", map.get("message"));
		result.put("status", map.get("status"));
	}

}
