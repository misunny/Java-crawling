package vo;

import java.util.ArrayList;

public class SelectSearchById {
	private int reviewNum;
	private String categoryName;
	private String brandName; 
	private String productName; 
	private String id;
	private String ageType;
	private String skinType;
	private String text;
	private String personDate;

	
	public SelectSearchById(int reviewNum, String categoryName, String brandName, String productName, String id,
			String ageType, String skinType, String text, String personDate) {
		super();
		this.reviewNum = reviewNum;
		this.categoryName = categoryName;
		this.brandName = brandName;
		this.productName = productName;
		this.id = id;
		this.ageType = ageType;
		this.skinType = skinType;
		this.text = text;
		this.personDate = personDate;
	}


	public SelectSearchById() {
		super();
		// TODO Auto-generated constructor stub
	}


	public int getReviewNum() {
		return reviewNum;
	}


	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}


	public String getCategoryName() {
		return categoryName;
	}


	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}


	public String getBrandName() {
		return brandName;
	}


	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getAgeType() {
		return ageType;
	}


	public void setAgeType(String ageType) {
		this.ageType = ageType;
	}


	public String getSkinType() {
		return skinType;
	}


	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}


	public String getPersonDate() {
		return personDate;
	}


	public void setPersonDate(String personDate) {
		this.personDate = personDate;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "| 텍스트번호 : "+reviewNum+" | 카테고리 : "+categoryName+" | 브랜드 : "+brandName+" | 제품명 : "+productName+" | id : "+id+" | 연령 : "+ageType+" | 피부타입 : "+
				skinType+" |\n| 내용 : "+text+" |\n| 입력시간 : "+personDate+" |\n";
	}
	

}
