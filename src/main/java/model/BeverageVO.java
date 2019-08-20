package model;

import org.springframework.web.multipart.MultipartFile;

public class BeverageVO {
	
	
	private int no;
	private String name;
	private String fileName;
	private int price; 
	private int count; 
	private int state; 
	private int capacity; 
	private int sellCount;
	private String regDate;
	private MultipartFile file;

	
	
	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getRegDate() {
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	@Override
	public String toString() {
		return "BeverageVO [no=" + no + ", name=" + name + ", fileName=" + fileName + ", price=" + price + ", count="
				+ count + ", state=" + state + ", capacity=" + capacity + ", sellCount=" + sellCount + ", regDate="
				+ regDate + ", file=" + file + "]";
	}


	
	
}
