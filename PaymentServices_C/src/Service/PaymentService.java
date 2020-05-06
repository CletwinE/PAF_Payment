package Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Beans.PaymentBean;
import Model.Payment;

/**
 * Servlet implementation class PaymentAPI
 */
@WebServlet("/PaymentService")
public class PaymentService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Payment payObj = new Payment();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PaymentService() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PaymentBean doc = null;
		// TODO Auto-generated method stub
		String output = payObj.insertPayment(request.getParameter("payCardNo"), request.getParameter("payName"),
				request.getParameter("payDate"), request.getParameter("payAmount"));
		response.getWriter().write(output);
	}

	

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = payObj.updatePayment(paras.get("hidPaymentIDSave").toString(), paras.get("payCardNo").toString(), paras.get("payName").toString(), paras.get("payDate").toString(), paras.get("payAmount").toString());
		response.getWriter().write(output); 
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = payObj.deletePayment(paras.get("PayID").toString());
		response.getWriter().write(output);
	}
	
	// Covert request parameters to a map
	private static Map getParasMap(HttpServletRequest request)
	{
	 Map<String, String> map = new HashMap<String, String>();
	try
	 {
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
	 String queryString = scanner.hasNext() ?
	 scanner.useDelimiter("\\A").next() : "";
	 scanner.close();
	 String[] params = queryString.split("&");
	 for (String param : params)
	 { 
	
	String[] p = param.split("=");
	 map.put(p[0], p[1]);
	 }
	 }
	catch (Exception e)
	 {
	 }
	return map;
	}

}
