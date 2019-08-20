package dao;

import java.util.List;

import model.BeverageVO;

public interface BeverageDAO {
	public void insertBeverage(BeverageVO beverageVO);
	public void deleteBeverage(int no);
	public void updateBeverage(BeverageVO user);
	
	public int selectBeverageCount();
	public List<?> selectBeverageList(int startRow, int endRow);
	public BeverageVO selectBeverageByPK(int no);
	public void updateBeverageCount(int updateCount,int no);
	public void updateBeverageSellCount(int updateCount,int no);
}
