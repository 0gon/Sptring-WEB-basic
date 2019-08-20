package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.OrderVO;
@Repository
public class JdbcOrderDAO implements OrderDAO{
	private static final String namespace = "order";
	private static JdbcOrderDAO instance = new JdbcOrderDAO();

	public static JdbcOrderDAO getInstance() {
		return instance;
	}

	@Autowired
	private SqlSession sqlSession;

	@Override
	public void insertOrder(OrderVO orderVO) {
		sqlSession.insert(namespace + ".insertOrder", orderVO);
	}

	@Override
	public void updateOrder(OrderVO orderVO) {
		sqlSession.update(namespace + ".updateOrder", orderVO);	
	}

	@Override
	public OrderVO selectOrderByPK(int no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("no", no);
		OrderVO orderVO=sqlSession.selectOne(namespace + ".selectOrderByPK", map);
		return orderVO;
	}
	@Override
	public int selectOrderCount() {
		return sqlSession.selectOne(namespace + ".selectOrderCount");
	}

	@Override
	public List selectOrderList(int startRow, int endRow) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List li =sqlSession.selectList(namespace + ".selectOrderList", map);
		return li;
	}

}
