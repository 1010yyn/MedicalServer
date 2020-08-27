package server;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MyHttpServletRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request;
	
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		this.request=request;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String getParameter(String name)
	{
		String value=request.getParameter(name);
		
		if(value!=null)
		{
			try {
				value=new String(value.getBytes("ISO8859-1"),"UTF-8");
			}catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		
		return value;
	}
	

}
