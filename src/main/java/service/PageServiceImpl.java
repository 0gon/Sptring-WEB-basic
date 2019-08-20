package service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import dao.BeverageDAO;
import dao.GradeDAO;
import dao.OrderDAO;
import dao.UserDAO;
import model.BeverageVO;
import model.GradeVO;
import model.OrderVO;
import model.UserVO;
@Service
public class PageServiceImpl implements PageService{
	@Autowired
	UserDAO userDAO;
	@Autowired
	GradeDAO gradeDAO;
	@Autowired
	BeverageDAO beverageDAO;
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
	public void getBeverageList(Model model, int pageNum) {
		int count = beverageDAO.selectBeverageCount();
		ArrayList<Integer> rows = ListView(pageNum, count, model);
		List<?> beverages = null; 
		if (count > 0) {
			beverages = beverageDAO.selectBeverageList(rows.get(0), rows.get(1));
		}
		model.addAttribute("beverages", beverages)
		.addAttribute("count", count);		
	}

	@Override
	public void getOrderList(Model model, int pageNum) {
		int count = orderDAO.selectOrderCount();
		ArrayList<Integer> rows = ListView(pageNum, count, model);
		List<?> orders = null; 
		if (count > 0) {
			orders = orderDAO.selectOrderList(rows.get(0), rows.get(1));
		}
		model.addAttribute("orders", orders)
		.addAttribute("count", count);
	}

	@Override
	public void repurchaseService(int orderNo,int buyCount) {
		//기존의 구매조건 불러와서 새로운 정보 인서트
		OrderVO aleadyVO = orderDAO.selectOrderByPK(orderNo) ;
		OrderVO newVO = new OrderVO();
		newVO.setBeverageName(aleadyVO.getBeverageName());
		newVO.setBeverageNo(aleadyVO.getBeverageNo());
		newVO.setBeveragePrice(aleadyVO.getBeveragePrice());
		newVO.setCount(buyCount);
		newVO.setUserNo(aleadyVO.getUserNo());
		orderDAO.insertOrder(newVO);
		
		BeverageVO beverageVO= beverageDAO.selectBeverageByPK(orderNo);
		int beverageNo=beverageVO.getNo();
		int aleadySellCount=beverageVO.getSellCount();
		
		int sellCount=aleadySellCount+buyCount;
		beverageDAO.updateBeverageSellCount(sellCount,beverageNo);
	}

	@Override
	public void purchaseService(OrderVO orderVO, UserVO userVO, Model model) {
		orderDAO.insertOrder(orderVO);
		//기존 음료테이블의 재고량 감소
		//첫 음료 구매시 유저 등급 상승
		BeverageVO beverageVO= beverageDAO.selectBeverageByPK(orderVO.getBeverageNo());
		
		userDAO.updateUserGrade(userVO); // 1 일반회원 2 정회원 3 우수회원
		
		UserVO updateVO=userDAO.selectUserInfoByPK(userVO.getNo());
		GradeVO gradeVO=gradeDAO.selectGradeInfo(updateVO.getGrade());
		updateVO.setGradeVO(gradeVO);
		model.addAttribute("userVO", updateVO);
		
		
		
		int beverageNo=beverageVO.getNo();
		int reposit=beverageVO.getCount();
		int buyCount=orderVO.getCount();
		int aleadySellCount=beverageVO.getSellCount();
		
		int sellCount=aleadySellCount+buyCount;
		int repositResult=reposit-buyCount;
		beverageDAO.updateBeverageCount(repositResult, beverageNo);
		beverageDAO.updateBeverageSellCount(sellCount,beverageNo);
	}



}
