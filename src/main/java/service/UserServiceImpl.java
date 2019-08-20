package service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import dao.GradeDAO;
import dao.UserDAO;
import model.GradeVO;
import model.UserVO;
@Service
public class UserServiceImpl implements UserService{
	@Autowired
	UserDAO userDAO;
	@Autowired
	GradeDAO gradeDAO;
	
	public ArrayList<Integer> ListView(int pageNum,int count,Model model) {
		ArrayList<Integer> rows= new ArrayList<Integer>();
		int pageSize = 6;
		int currentPage = pageNum;
		int startRow = (currentPage - 1) * pageSize + 1;
		int endRow = currentPage * pageSize;
		rows.add(startRow);
		rows.add(endRow);
		int number = 0;
		number = count - (currentPage - 1) * pageSize;
		int bottomLine = 5;
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = 1 + (currentPage - 1) / bottomLine * bottomLine;
		int endPage = startPage + bottomLine - 1;
		if (endPage > pageCount)
			endPage = pageCount;
		model.addAttribute("pageCount", pageCount)
		.addAttribute("endPage", endPage)
		.addAttribute("bottomLine", bottomLine)
		.addAttribute("pageNum", pageNum)
		.addAttribute("startPage", startPage)
		.addAttribute("currentPage", currentPage)
		.addAttribute("number", number);
		return rows;
	}
	
	
	@Override
	public UserVO getUserInfo(String email, boolean isNaver) {
		if(email.equals("init")) {
			return null;
		}
		UserVO userVO = userDAO.selectUserInfo(email);
		if(isNaver==true) {
			if(userVO==null) {
				GradeVO gradeVO=gradeDAO.selectGradeInfo(1);
				userVO = new UserVO();
				userVO.setEmail(email);
				userVO.setGrade(1);
				userVO.setGradeVO(gradeVO);
				userVO.setNickname("naver회원");
				userVO.setPassword("NAVER_DEFAULT_PASSWORD");
				userDAO.insertUser(userVO);
			}else {
				GradeVO grade =gradeDAO.selectGradeInfo(userVO.getGrade());
				userVO.setGradeVO(grade);
			}
		}else if(isNaver==false) {
			if(userVO!=null) {
				GradeVO grade =gradeDAO.selectGradeInfo(userVO.getGrade());
				userVO.setGradeVO(grade);
			}
		}
		return userVO;
	}

	@Override
	public void getUserList(Model model, int pageNum) {
		int count = userDAO.selectUserCount();
		ArrayList<Integer> rows = ListView(pageNum, count, model);
		List<?> users = null; 
		List userLi=null;
		if (count > 0) {
			users = userDAO.selectUserList(rows.get(0), rows.get(1));
			Iterator<?> it = users.iterator();
			if(it.hasNext()) {
				userLi=new ArrayList<UserVO>();
				do {
					UserVO userVO = (UserVO) it.next();
					GradeVO gradeVO=gradeDAO.selectGradeInfo(userVO.getGrade());
					userVO.setGradeVO(gradeVO);
					userLi.add(userVO);				
				}while(it.hasNext());
			}
		}
		
		model.addAttribute("users", userLi)
		.addAttribute("count", count);
	}


	@Override
	public UserVO getUserInfoByPK(int no) {
		UserVO userVO=userDAO.selectUserInfoByPK(no);
		GradeVO gradeVO = gradeDAO.selectGradeInfo(userVO.getGrade());
		userVO.setGradeVO(gradeVO);
		return userVO;
	}


	@Override
	public void updateUserInfo(UserVO userVO) {
		userDAO.updateUser(userVO);
		GradeVO gradeVO = gradeDAO.selectGradeInfo(userVO.getGrade());
		userVO.setGradeVO(gradeVO);
	}



}
