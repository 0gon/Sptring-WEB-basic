package model;

public class OrderVO {
	private int no;
	private int userNo;
	private int beverageNo;
	private int beveragePrice;
	private String beverageName;
	private int count;
	private String regDate;
	
	@Override
	public String toString() {
		return "OrderVO [no=" + no + ", userNo=" + userNo + ", beverageNo=" + beverageNo + ", beveragePrice="
				+ beveragePrice + ", beverageName=" + beverageName + ", count=" + count + ", regDate=" + regDate + "]";
	}
	public int getBeveragePrice() {
		return beveragePrice;
	}
	public void setBeveragePrice(int beveragePrice) {
		this.beveragePrice = beveragePrice;
	}
	public String getBeverageName() {
		return beverageName;
	}
	public void setBeverageName(String beverageName) {
		this.beverageName = beverageName;
	}
	public int getBeverageNo() {
		return beverageNo;
	}
	public void setBeverageNo(int beverageNo) {
		this.beverageNo = beverageNo;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getRegDate() {
		return regDate;
	}
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
}
