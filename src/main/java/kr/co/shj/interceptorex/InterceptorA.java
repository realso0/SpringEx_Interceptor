package kr.co.shj.interceptorex;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//HandlerInterceptorAdapter 클래스 상속받기
public class InterceptorA extends HandlerInterceptorAdapter {
	
	//클라이언트가 실제 요청한 url(컨트롤러)로 접근 전에 실행됨.
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		System.out.println("doA 인터셉터 작동함. /doA 접근 전 입니다.");
		
		String param=request.getParameter("msg");
		if(param==null || param.equals("")) { //http://localhost:8088/s/doA 값이 없으므로 doB로 이동
			System.out.println("param이 올바르지 않아 doB로 이동함.");
			response.sendRedirect("/doB");
			return false;
		} else { 
			System.out.println("doA로 이동할 때 전달된 param : "+param); //http://localhost:8088/s/doA?msg=Hello
		}
		//원래 클라이언트가 요청한 곳으로 보내려면 true리턴, 로직에 따라 다른 곳으로 보내려면 false리턴
		return true;
	}
	
	//서버가 클라이언트에 응답하여 클라이언트에게 도달 전에 실행됨.
	//model and view --> 서버가 클라이언트에게 전달하는 attribute모델과 이동하고자 하는 view의 정보를 가지고 있음.
	//interceptor는 이 2가지를 모두 관리해야 하므로 modelandview를 사용함.
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception { //http://localhost:8088/s/doA?msg=Hello

		System.out.println("doA 인터셉터 작동함. /doA 응답 되었습니다.");
		
		//Model 을 꺼내기 위해서는 modelAndView 객체를 사용함.
		ModelMap modelMap=modelAndView.getModelMap();
		String  infovalue=(String)modelMap.get("info"); //info값 가로채기
		
		if(infovalue.equals("hello")) { //info가 hello 이면 doB로 redirect 하기
			System.out.println("/doA 응답 중 info값이 hello라서 doB로 이동합니다.");
			response.sendRedirect("/doB"); //redirect, forward 후에는 아래에 코드가 없는게 좋음.
		}
	}
	
}
