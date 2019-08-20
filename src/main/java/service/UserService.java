package service;


import org.springframework.ui.Model;

import model.UserVO;

public interface UserService {
	
	public UserVO getUserInfo(String email, boolean isNaver);
	public void getUserList(Model model, int pageNum);
	
	public UserVO getUserInfoByPK(int no);
	public void updateUserInfo(UserVO userVO);
}
