package controller;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import dao.UserDAO;
import model.GradeVO;
import model.UserVO;
import service.UserService;

@Controller
@RequestMapping("/user")
@SessionAttributes("userVO")
public class UserController {

	@Autowired
	UserService user;
	@Autowired
	UserDAO userDAO;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String joinForm() {
		return "user/join";
	}

	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public String joinPro(Model model,@Valid UserVO userVO, Errors errors, SessionStatus sessionStatus) {
		if (errors.hasErrors()) {
			return "user/join";
		}
		userDAO.insertUser(userVO);
		logger.info(userVO.getNickname()+"님 등록 완료");
		return "redirect:/user/login";
	}

	@RequestMapping(value = "/checkNickname", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> checkNickname(@RequestParam("nickname") String nickname) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		UserVO userVO = userDAO.selectNickname(nickname);
		if (userVO == null) {
			map.put("result", true);
			return map;
		} else {
			map.put("result", false);
			return map;
		}
	}

	@RequestMapping(value = "/checkEmail", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Boolean> checkEmail(@RequestParam("email") String email) {
		Map<String, Boolean> map = new HashMap<String, Boolean>();
		UserVO userVO = user.getUserInfo(email,false);
		if (userVO == null) {
			map.put("result", true);
			return map;
		} else {
			map.put("result", false);
			return map;
		}
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest req,
			@RequestParam(value = "isLog", defaultValue = "") String isLog) throws UnsupportedEncodingException {
		if (isLog.equals("false")) {
			model.addAttribute("isLog", isLog);
		}
		HttpSession session = req.getSession();
		String clientId = "6aRI5wmFjVe4vTZDOSBe";// 애플리케이션 클라이언트 아이디값";
		String redirectURI = URLEncoder.encode("http://localhost:8080/springPro/page/main", "UTF-8");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = "https://nid.naver.com/oauth2.0/authorize?response_type=code";
		apiURL += "&client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + state;
		session.setAttribute("state", state);

		model.addAttribute("apiURL", apiURL);

		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcess(Model model, @RequestParam(value = "email", defaultValue = "") String email,
			@RequestParam(value = "password", defaultValue = "") String input_pwd) {
		UserVO userVO = user.getUserInfo(email,false);
		if(userVO==null || !input_pwd.equals(userVO.getPassword())) {
			//저장된 정보가 없거나 비밀번호가 틀린 경우
			return "redirect:/user/login";
		}
		else {
			logger.info(userVO.getNickname()+"님 로그인 성공");
			model.addAttribute("userVO", userVO);
			return "page/main";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(SessionStatus sessionStatus) {
		sessionStatus.setComplete();
		return "redirect:/page/main";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public String edit(Model model, UserVO userVO) {
		return "user/edit";
	}

	@RequestMapping(value = "/editForm", method = RequestMethod.GET)
	public String editForm(Model model, UserVO userVO) {
		return "user/editForm";
	}

	@RequestMapping(value = "/editForm", method = RequestMethod.POST)
	public String editFormPro(Model model, UserVO userVO) {
		user.updateUserInfo(userVO);
		logger.info("no."+userVO.getNo()+": 회원정보 수정 완료");
		return "redirect:/page/main";
	}

	@RequestMapping(value = "/delete/{no}", method = RequestMethod.GET)
	public String delete(Model model, @PathVariable("no") int no,
		SessionStatus sessionStatus) {
		userDAO.deleteUser(no);
		sessionStatus.setComplete();
		return "redirect:/page/main";
	}

}
