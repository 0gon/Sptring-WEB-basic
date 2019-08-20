package dao;


import java.util.List;

import model.GradeVO;
import model.UserVO;

public interface GradeDAO {
	public void insertGrade(GradeVO gradeVO);
	public void deleteGrade(int no);
	public void updateGrade(GradeVO gradeVO);
	
	public GradeVO selectGradeInfo(int no);
	public List selectGradeList(int startRow, int endRow);
	public int selectGradeCount();
}
