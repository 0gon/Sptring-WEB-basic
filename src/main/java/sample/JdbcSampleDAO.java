package sample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.JdbcUserDAO;
import model.UserVO;
@Repository
public class JdbcSampleDAO implements SampleDAO{
	// ctrl + f 를 통해 user, User 값 변경, (입력,수정,삭제, 수 체크 리스트, 고유값 객체 가지고오기) 
	private static final String namespace = "user";
	private static JdbcUserDAO instance = new JdbcUserDAO();
	public static JdbcUserDAO getInstance() {return instance;}
	@Autowired
	private SqlSession sqlSession; 
	@Override
	public void insertUser(UserVO user) {
		sqlSession.insert(namespace + ".insertUser", user);
	}
	@Override
	public void updateUser(UserVO user) {
		sqlSession.update(namespace + ".updateUser", user);
	}
	@Override
	public void deleteUser(int no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("no", no);
		sqlSession.delete(namespace + ".deleteUser", map);
	}
	
	@Override
	public int selectUserCount() {
		return sqlSession.selectOne(namespace + ".selectUserCount");
	}
	@Override
	public List<?> selectUserList(int startRow, int endRow) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List<?> li =sqlSession.selectList(namespace + ".selectUserList", map);
		return li;
	}

	@Override
	public UserVO selectUserByPK(int no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("no", no);
		UserVO userVO=sqlSession.selectOne(namespace + ".selectUserByPK", map);
		return userVO;
	}

}
