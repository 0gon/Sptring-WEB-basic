package controller;

import java.io.File;
import java.io.IOException;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;

import dao.BeverageDAO;
import dao.GradeDAO;
import dao.UserDAO;
import model.BeverageVO;
import model.GradeVO;
import model.UserVO;
import service.AdminService;
import service.PageService;
import service.UserService;

@Controller
@RequestMapping("/admin")
@SessionAttributes("userVO")
public class AdminController {
private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	UserService user;
	@Autowired
	PageService page;
	@Autowired
	AdminService admin;
	@Autowired
	UserDAO userDAO;
	@Autowired
	GradeDAO gradeDAO;
	@Autowired
	BeverageDAO beverageDAO;
	@Autowired
	private JavaMailSender mailSender;
	 
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index(Model model) {
		return "admin/sample";
	}
	
	@RequestMapping(value = "/userList/{pageNum}", method = RequestMethod.GET)
	public String userList(Model model,
			@PathVariable("pageNum") int pageNum ) {
		logger.info("회원관리 페이지 진입 성공");
		user.getUserList(model,pageNum);
		return "admin/userList";
	}
	@RequestMapping(value = "/gradeList/{pageNum}", method = RequestMethod.GET)
	public String gradeList(Model model,
			@PathVariable("pageNum") String pageNum ) {
		logger.info("등급관리 진입");
		admin.getGradeList(model, pageNum);
		return "admin/gradeList";
	}
	
	@RequestMapping(value = "/updateGradeForm/{no}", method = RequestMethod.GET)
	public String updateGradeForm(Model model, @PathVariable("no") String no ) {
		GradeVO gradeVO=gradeDAO.selectGradeInfo(Integer.parseInt(no));
		model.addAttribute("gradeVO",gradeVO);
		return "admin/updateGradeForm";
	}

	@RequestMapping(value = "/updateGradeForm", method = RequestMethod.POST)
	public String updateGradeFormPro(Model model, GradeVO gradeVO) {
		System.out.println(gradeVO);
		gradeDAO.updateGrade(gradeVO);
		return "redirect:/admin/gradeList/1";
	}

	@RequestMapping(value = "/gradeDelete/{no}", method = RequestMethod.GET)
	public String gradeDelete(Model model,@PathVariable("no") String no ) {
		gradeDAO.deleteGrade(Integer.parseInt(no));
		return "redirect:/admin/gradeList/1";
	}

	@RequestMapping(value = "/insertGradeForm", method = RequestMethod.GET)
	public String insertGradeForm(Model model) {
		return "admin/insertGradeForm";
	}

	@RequestMapping(value = "/insertGradeForm", method = RequestMethod.POST)
	public String insertGradeFormPro(GradeVO gradeVO) {
		gradeDAO.insertGrade(gradeVO);
		logger.info(gradeVO.getName()+" 등급 추가");
		return "redirect:/admin/gradeList/1";
	}
	
	@RequestMapping(value = "/updateUserForm/{targetNo}/{sessionNo}", method = RequestMethod.GET)
	public String updateUserForm(Model model, @PathVariable("targetNo") int targetNo,
			@PathVariable("sessionNo") int sessionNo) {
		UserVO targetVO= user.getUserInfoByPK(targetNo);
		UserVO sessionVO=user.getUserInfoByPK(sessionNo);
		GradeVO gradeVO = gradeDAO.selectGradeInfo(targetVO.getGrade());
		targetVO.setGradeVO(gradeVO);
		admin.getGradeList(model, "1");
		
		model.addAttribute("targetVO",targetVO)
			.addAttribute("userVO",sessionVO);
		return "admin/updateUserForm";
	}

	@RequestMapping(value = "/updateUserForm/{sessionNo}", method = RequestMethod.POST)
	public String updateUserFormPro(Model model, UserVO targetVO,
			@PathVariable("sessionNo") int sessionNo) {
		user.updateUserInfo(targetVO);
		logger.info("no."+targetVO.getNo()+": 회원정보 수정 완료");
		UserVO sessionVO=user.getUserInfoByPK(sessionNo);
		model.addAttribute("userVO",sessionVO);
		return "redirect:/admin/userList/1";
	}

	@RequestMapping(value = "/deleteUser/{no}", method = RequestMethod.GET)
	public String deleteUser(Model model, @PathVariable("no") int no) {
			userDAO.deleteUser(no);
			return "redirect:/admin/userList/1";
	}
	@RequestMapping(value = "/addBeverage", method = RequestMethod.GET)
	public String addBeverage(Model model) {
		return "admin/addBeverage";
	}

	@RequestMapping(value = "/addBeverage", method = RequestMethod.POST)
	public String addBeveragePro(@RequestParam("file") MultipartFile multipartFile,
			 HttpServletRequest request,Model model,BeverageVO beverageVO)throws IOException  {
		String uploadPath = request.getRealPath("/") + "fileSave";
		if (!multipartFile.isEmpty()) {
			File file = new File(uploadPath, multipartFile.getOriginalFilename());
				multipartFile.transferTo(file);
				beverageVO.setFileName(multipartFile.getOriginalFilename());
		}
		beverageDAO.insertBeverage(beverageVO);
		logger.info(beverageVO.getName()+"음료 추가");
		return "redirect:/page/beverageList/1";
	}
	
	
	@RequestMapping(value = "/sellList/{pageNum}", method = RequestMethod.GET)
	public String sellList(Model model,
			@PathVariable("pageNum") int pageNum ) {
		logger.info("판매내역 페이지 진입");
		page.getBeverageList(model, pageNum);
		return "admin/sellList";
	}
	
	@RequestMapping(value = "/updateBeverageForm/{no}", method = RequestMethod.GET)
	public String updateBeverageForm(Model model, @PathVariable("no") int no) {
		BeverageVO beverageVO = beverageDAO.selectBeverageByPK(no);
		model.addAttribute("beverageVO", beverageVO);
		return "admin/updateBeverageForm";
	}
	
	
	
	@RequestMapping(value = "/updateBeveragePro", method = RequestMethod.POST)
	public String updateBeveragePro(@RequestParam("file") MultipartFile multipartFile,
			 HttpServletRequest request,Model model,BeverageVO beverageVO)throws IOException  {
		String uploadPath = request.getRealPath("/") + "fileSave";
		if (!multipartFile.isEmpty()) {
			File file = new File(uploadPath, multipartFile.getOriginalFilename());
				multipartFile.transferTo(file);
				beverageVO.setFileName(multipartFile.getOriginalFilename());
		}
		beverageDAO.updateBeverage(beverageVO);
		logger.info(beverageVO.getName()+"음료 수정");
		return "redirect:/admin/sellList/1";
	}
	
	@RequestMapping(value = "/deleteBeverage/{no}", method = RequestMethod.GET)
	public String deleteBeverage(Model model, @PathVariable("no") int no) {
			beverageDAO.deleteBeverage(no);
			return "redirect:/admin/sellList/1";
	}
	@RequestMapping(value = "/mailSending", method = RequestMethod.POST)
	public String mailSending(Model model) {
		 String setfrom = "syg184@gmail.com";         
		    String tomail  = "safoma@naver.com";     // 받는 사람 이메일
		    String title   = "test";      // 제목
		    String content = "sd";    // 내용
		   
		    try {
		      MimeMessage message = mailSender.createMimeMessage();
		      MimeMessageHelper messageHelper 
		                        = new MimeMessageHelper(message, true, "UTF-8");
		 
		      messageHelper.setFrom(setfrom);  
		      messageHelper.setTo(tomail);     
		      messageHelper.setSubject(title);
		      messageHelper.setText(content);
		     
		      mailSender.send(message);
		    } catch(Exception e){
		      System.out.println(e);
		    }
		
		return "page/main";
	}
	
}
