package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.JdbcBeverageDAO;
import model.BeverageVO;
@Repository
public class JdbcBeverageDAO implements BeverageDAO{
	private static final String namespace = "beverage";
	private static JdbcBeverageDAO instance = new JdbcBeverageDAO();
	public static JdbcBeverageDAO getInstance() {return instance;}
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public void insertBeverage(BeverageVO beverageVO) {
		sqlSession.insert(namespace + ".insertBeverage", beverageVO);
	}
	
	@Override
	public void updateBeverage(BeverageVO user) {
		sqlSession.update(namespace + ".updateBeverage", user);
	}
	@Override
	public void deleteBeverage(int no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("no", no);
		sqlSession.delete(namespace + ".deleteBeverage", map);
	}
	
	@Override
	public int selectBeverageCount() {
		return sqlSession.selectOne(namespace + ".selectBeverageCount");
	}
	@Override
	public List<?> selectBeverageList(int startRow, int endRow) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<?> li =sqlSession.selectList(namespace + ".selectBeverageList", map);
		return li;
	}

	@Override
	public BeverageVO selectBeverageByPK(int no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("no", no);
		BeverageVO userVO=sqlSession.selectOne(namespace + ".selectBeverageByPK", map);
		return userVO;
	}

	@Override
	public void updateBeverageCount(int updateCount,int no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("updateCount", updateCount);
		map.put("no", no);
		sqlSession.update(namespace + ".updateBeverageCount", map);
	}

	@Override
	public void updateBeverageSellCount(int updateCount, int no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("updateCount", updateCount);
		map.put("no", no);
		sqlSession.update(namespace + ".updateBeverageSellCount", map);
	}
	


}
