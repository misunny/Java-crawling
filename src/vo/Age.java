package vo;

public class Age {
	private int ageNum;
	private String ageType;
	
	public Age() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Age(int ageNum, String ageType) {
		super();
		this.ageNum = ageNum;
		this.ageType = ageType;
	}
	
	public int getAgeNum() {
		return ageNum;
	}
	
	public void ageNum(int ageNum) {
		this.ageNum = ageNum;
	}
	
	public String getAgeType() {
		return ageType;
	}
	
	public void setAgeType(String ageType) {
		this.ageType = ageType;
	}
	
	@Override
	public String toString() {
		return "연령대 번호 : "+ageNum+", 연령대 : "+ageType;
	}
	
	
}
