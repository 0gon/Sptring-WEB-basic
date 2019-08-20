package service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import dao.GradeDAO;
import dao.OrderDAO;
import dao.UserDAO;
import model.GradeVO;
import model.UserVO;
@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	UserDAO userDAO;
	@Autowired
	GradeDAO gradeDAO;
	@Autowired
	OrderDAO orderDAO;
	
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
		.addAttribute("startPage", startPage)
		.addAttribute("pageNum", pageNum)
		.addAttribute("currentPage", currentPage)
		.addAttribute("number", number);
		return rows;
	}
	
	

	@Override
	public void getGradeList(Model model, String pageNum) {
		int count = gradeDAO.selectGradeCount();
		ArrayList<Integer> rows = ListView(Integer.parseInt(pageNum), count, model);
		List<?> grades = null; 
		if (count > 0) {
			grades = gradeDAO.selectGradeList(rows.get(0), rows.get(1));
		}
		model.addAttribute("grades", grades)
		.addAttribute("count", count);
	}






}
