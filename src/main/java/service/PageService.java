package service;

import org.springframework.ui.Model;

import model.OrderVO;
import model.UserVO;


public interface PageService {
	public void getOrderList(Model model, int pageNum);
	public void getBeverageList(Model model, int pageNum);
	public void repurchaseService(int orderNo,int buyCount);
	public void purchaseService(OrderVO orderVO,UserVO userVO, Model model);
}
