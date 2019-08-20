package model;


import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class UserVO {

	@NotEmpty
	@Length(min=3)
	private String nickname;
	
	@NotEmpty
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[!@#$%^&+=])(?=\\S+$).{5,}")	
	private String password;
	
	@NotEmpty
	private String confirmPassword;
	
	@NotEmpty
	@Email
	private String email;
	
	private String regDate;
	private int no;
	public String getRegDate() {
		return regDate;
	}

	@Override
	public String toString() {
		return "UserVO [nickname=" + nickname + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", email=" + email + ", regDate=" + regDate + ", no=" + no + ", grade=" + grade + ", gradeVO="
				+ gradeVO + "]";
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	private int grade;
	private GradeVO gradeVO;
	


	public GradeVO getGradeVO() {
		return gradeVO;
	}

	public void setGradeVO(GradeVO gradeVO) {
		this.gradeVO = gradeVO;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	
	public boolean isSamePassword() {
		if (password == null || confirmPassword == null)
			return false;
		return password.equals(confirmPassword);
	}

	public boolean hasPassword() {
		return password != null && password.trim().length() > 0;
	}
	
	
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getPassword() {
		return password;
	}
	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
}
