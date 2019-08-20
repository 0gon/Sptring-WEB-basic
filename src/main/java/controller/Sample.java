package controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import dao.GradeDAO;
import dao.UserDAO;
import model.GradeVO;
import model.UserVO;
import service.AdminService;
import service.UserService;

@Controller
@RequestMapping("/sample")
@SessionAttributes("userVO")
public class Sample {

	private static final Logger logger = LoggerFactory.getLogger(Sample.class);
	@Autowired
	UserService user;
	@Autowired
	AdminService admin;
	@Autowired
	UserDAO userDAO;
	@Autowired
	GradeDAO gradeDAO;

	

	@RequestMapping(value = "/insertForm", method = RequestMethod.GET)
	public String insertGradeForm(Model model) {
		return "admin/insertGradeForm";
	}

	@RequestMapping(value = "/insertForm", method = RequestMethod.POST)
	public String insertGradeFormPro(GradeVO gradeVO) {
		gradeDAO.insertGrade(gradeVO);
		logger.info(gradeVO.getName()+" 등급 추가");
		return "redirect:/admin/gradeList/1";
	}
	
	@RequestMapping(value = "/ListView/{pageNum}", method = RequestMethod.GET)
	public String ListView(Model model,
			@PathVariable("pageNum") int pageNum ) {
		user.getUserList(model,pageNum);
		return "admin/userList";
	}
	
	@RequestMapping(value = "/_detail", method = RequestMethod.GET)
	public String _detail(Model model, UserVO userVO) {
		return "user/userInfo";
	}
	
	
	@RequestMapping(value = "/updateForm/{no}", method = RequestMethod.GET)
	public String updateForm(Model model, @PathVariable("no") String no ) {
		GradeVO gradeVO=gradeDAO.selectGradeInfo(Integer.parseInt(no));
		model.addAttribute("gradeVO",gradeVO);
		return "admin/updateGradeForm";
	}

	@RequestMapping(value = "/updateForm", method = RequestMethod.POST)
	public String updateForm(Model model, GradeVO gradeVO) {
		gradeDAO.updateGrade(gradeVO);
		return "redirect:/admin/gradeList/1";
	}
	
	@RequestMapping(value = "/deletePro/{no}", method = RequestMethod.GET)
	public String deletePro(Model model,@PathVariable("no") String no ) {
		gradeDAO.deleteGrade(Integer.parseInt(no));
		return "redirect:/admin/gradeList/1";
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
	
	@RequestMapping(value = "/addHamburger", method = RequestMethod.POST)
	public String addHamburgerPro(@RequestParam("file") MultipartFile multipartFile,
			 HttpServletRequest request,Model model) throws IOException {
		String uploadPath = request.getRealPath("/") + "fileSave";
		if (!multipartFile.isEmpty()) {
			File file = new File(uploadPath, multipartFile.getOriginalFilename());
				multipartFile.transferTo(file);
			//	hamburgerVO.setFileName(multipartFile.getOriginalFilename());
			}
	//	hamburgerDAO.insertHamburger(hamburgerVO);
	//	logger.info(hamburgerVO.getName()+" 햄버거 추가");
		return "redirect:/page/hamburgerList/1";
	}
	

}
