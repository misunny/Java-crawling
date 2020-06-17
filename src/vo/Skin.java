package vo;

public class Skin {
	private int skinNum;
	private String skinType;
	
	public Skin() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Skin(int skinNum, String skinType) {
		super();
		this.skinNum = skinNum;
		this.skinType = skinType;
	}
	public int getSkinNum() {
		return skinNum;
	}
	public void setskinNum(int skinNum) {
		this.skinNum = skinNum;
	}
	public String getSkinType() {
		return skinType;
	}
	public void setSkinType(String skinType) {
		this.skinType = skinType;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "피부 타입 번호 : "+skinNum+", 피부타입 : "+skinType;
	}
	
	

}
