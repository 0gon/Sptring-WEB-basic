package sample;


import org.springframework.ui.Model;

import model.UserVO;

public interface sampleService {
	
	public void getUserList(Model model, int pageNum);
	public UserVO getUserInfoByPK(int no);
	public void updateUserInfo(UserVO userVO);
}
