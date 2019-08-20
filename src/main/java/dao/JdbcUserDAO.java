package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.UserVO;
@Repository
public class JdbcUserDAO implements UserDAO{
	private static final String namespace = "user";
	private static JdbcUserDAO instance = new JdbcUserDAO();
	public static JdbcUserDAO getInstance() {
		return instance;
	}

	@Autowired
	private SqlSession sqlSession;
	/*@Override
	public void insertUser(UserVO user) {
		HashMap<String, String> map = new HashMap<String, String>();
		for(int i=0;i<5;i++) {
			map.put("nickname", "a"+i);
			map.put("password", "a"+i);
			map.put("email", "a"+i);
			sqlSession.insert(namespace + ".insertUser", map);
		}
	}*/
	@Override
	public void insertUser(UserVO user) {
		sqlSession.insert(namespace + ".insertUser", user);
	}

	@Override
	public void deleteUser(int no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("no", no);
		sqlSession.delete(namespace + ".deleteUser", map);
	}

	@Override
	public void updateUser(UserVO user) {
		sqlSession.update(namespace + ".updateUser", user);
	}
	
	@Override
	public int selectUserCount() {
		return sqlSession.selectOne(namespace + ".selectUserCount");
	}

	@Override
	public List selectUserList(int startRow, int endRow) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List li =sqlSession.selectList(namespace + ".selectUserList", map);
		return li;
	}

	@Override
	public UserVO selectUserInfo(String email) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", email);
		UserVO userVO=sqlSession.selectOne(namespace + ".selectUserInfo", map);
		return userVO;
	}
	@Override
	public UserVO selectUserInfoByPK(int no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("no", no);
		UserVO userVO=sqlSession.selectOne(namespace + ".selectUserInfoByPK", map);
		return userVO;
	}
	@Override
	public UserVO selectNickname(String nickname) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("nickname", nickname);
		UserVO userVO=sqlSession.selectOne(namespace + ".selectNickname", map);
		return userVO;
	}

	@Override
	public void updateUserGrade(UserVO userVO) {
		sqlSession.update(namespace + ".updateUserGrade", userVO);
	}


}
