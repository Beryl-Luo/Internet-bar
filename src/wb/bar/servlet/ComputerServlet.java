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
import wb.bar.entity.ComputerArea;
import wb.bar.service.ComputerService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

/**
 * Servlet implementation class Computer
 */
@WebServlet("/Computer")
public class ComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ComputerService computerService = new ComputerService();
	private Computer computer  = new Computer();
	private ComputerArea computerArea = new ComputerArea();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ComputerServlet() {
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
		case "addComputer": addComputer(request, result);break;
		case "getAllComputer": getAllComputer(request,result);break;
		case "getComputerTotal": getTotalCount(request,result);break;
		case "editComputer": editComputer(request,result);break;
		case "deleteComputer": deleteComputer(request,result);break;
		case "getComputerById": getComputerById(request,result);break;
		default : result.put("message", "未找到方法");result.put("status", false);break;
		}
		
		PrintWriter out = response.getWriter();
		out.write(JSON.toJSONString(result));
		out.flush();
		out.close();
		
	}
	
	private void addComputer(HttpServletRequest request, Map<String, Object> result){
		computerArea.setId(Integer.parseInt(request.getParameter("computerAreasId")));
		computer.setComputerArea(computerArea);
		computer.setNumber(Integer.parseInt(request.getParameter("number").toString()));
		boolean status = computerService.addComputer(computer);
		if(status){
			result.put("message", "新增成功");
			result.put("status", true);
		}else{
			result.put("message", "新增失败");
			result.put("status", false);
		}
	
	}
	
	private void getAllComputer(HttpServletRequest request, Map<String, Object> result){
		List<Map<String, Object>> map = computerService.selectAll(request.getParameter("page"));
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
	
	private void getTotalCount(HttpServletRequest request, Map<String, Object> result){
		List<Map<String, Object>> map = computerService.getTotalCount();
		if(map != null&& map.size() >0&&!map.get(0).isEmpty()){
			result.put("totalCount", map.get(0).get("totalCount"));
			result.put("message", "获取机器总数成功");
			result.put("status", true);
		}
		else{
			result.put("message", "获取机器总数失败");
			result.put("status", false);
		}
		
	}
	
	
	private void editComputer(HttpServletRequest request, Map<String, Object> result){
		computer.setId(Integer.parseInt(request.getParameter("id")));
		computer.setNumber(Integer.parseInt(request.getParameter("number")));
		computerArea.setId(Integer.parseInt(request.getParameter("type_id")));
		computer.setComputerArea(computerArea);
		boolean status = computerService.editComputer(computer);
		if(status){
			result.put("message", "编辑成功");
			result.put("status", true);
		}
		else{
			result.put("message", "编辑失败");
			result.put("status", false);
		}
	}
	
	private void deleteComputer(HttpServletRequest request, Map<String, Object> result){
		computer.setId(Integer.parseInt(request.getParameter("id")));
		boolean status = computerService.deleteComputer(computer);
		if(status){
			result.put("message", "删除成功");
			result.put("status", true);
		}
		else{
			result.put("message", "删除失败");
			result.put("status", false);
		}
	}
	
	private void getComputerById(HttpServletRequest request, Map<String, Object> result){
		computer.setId(Integer.parseInt(request.getParameter("id")));
		List<Map<String, Object>> map = computerService.selectComputerById(computer);
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
	
	

}
