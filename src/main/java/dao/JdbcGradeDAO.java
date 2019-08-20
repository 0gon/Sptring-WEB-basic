package dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.GradeVO;
import model.UserVO;
@Repository
public class JdbcGradeDAO implements GradeDAO{
	private static final String namespace = "grade";
	private static JdbcGradeDAO instance = new JdbcGradeDAO();

	public static JdbcGradeDAO getInstance() {
		return instance;
	}

	@Autowired
	private SqlSession sqlSession;
	@Override
	public void insertGrade(GradeVO gradeVO) {
		sqlSession.insert(namespace + ".insertGrade", gradeVO);
	}
	@Override
	public void deleteGrade(int no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("no", no);
		sqlSession.delete(namespace + ".deleteGrade", map);
	}

	@Override
	public void updateGrade(GradeVO gradeVO) {
		sqlSession.update(namespace + ".updateGrade", gradeVO);
	}
	@Override
	public GradeVO selectGradeInfo(int no) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("no", no);
		return sqlSession.selectOne(namespace + ".selectGradeInfo", map);
	}

	@Override
	public int selectGradeCount() {
		return sqlSession.selectOne(namespace + ".selectGradeCount");
	}

	@Override
	public List selectGradeList(int startRow, int endRow) {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		List li =sqlSession.selectList(namespace + ".selectGradeList", map);
		return li;
	}
}
