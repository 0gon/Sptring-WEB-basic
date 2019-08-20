package dao;

import java.util.List;

import model.OrderVO;

public interface OrderDAO {
	public void insertOrder(OrderVO orderVO);
	public void updateOrder(OrderVO orderVO);
	public OrderVO selectOrderByPK(int no);
	
	public int selectOrderCount();
	public List selectOrderList(int startRow, int endRow);
}
