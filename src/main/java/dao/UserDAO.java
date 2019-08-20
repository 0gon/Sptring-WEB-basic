package dao;

import java.util.List;

import model.UserVO;

public interface UserDAO {
	public void insertUser(UserVO user);
	public void deleteUser(int no);
	public void updateUser(UserVO user);
	public void updateUserGrade(UserVO userVO);
	
	public int selectUserCount();
	public List selectUserList(int startRow, int endRow);
	public UserVO selectUserInfo(String email);
	public UserVO selectUserInfoByPK(int no);
	public UserVO selectNickname(String nickname);
	
	
}
