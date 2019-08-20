package sample;

import java.util.List;

import model.UserVO;

public interface SampleDAO {
	// 입력, 삭제, 수정
	public void insertUser(UserVO user);
	public void deleteUser(int no);
	public void updateUser(UserVO user);
	
	//리스트 출력
	public int selectUserCount();
	public List<?> selectUserList(int startRow, int endRow);
	
	//정보 가지고오기
	public UserVO selectUserByPK(int no);
	
	
}
