package interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import model.UserVO;



public class MeasuringInterceptor extends HandlerInterceptorAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(MeasuringInterceptor.class);
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		logger.warn("로그인 정보 없음");
		UserVO userVO = (UserVO) session.getAttribute("userVO");
		if(userVO==null||userVO.getEmail()==null) {
		   response.sendRedirect("/springPro/user/login?isLog=false");
           return false;
		}
		/*		System.out.println("AI: preHandle()");
		HttpSession session = request.getSession(false);
		if (session == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
		if (session.getAttribute("auth") == null) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
			return false;
		}
		return true;*/
	        return true;


	}

/*	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		System.out.println("MI: afterCompletion()");
		Long beginTime = (Long) request.getAttribute("mi.beginTime");
		long endTime = System.currentTimeMillis();
		System.out.println(request.getRequestURI() + " 실행 시간: " + (endTime - beginTime));
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		System.out.println("MI: postHandle()");
	}*/
}
