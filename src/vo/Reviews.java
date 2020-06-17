package vo;

public class Reviews {
	private int reviewNum;
	private String id;
	private int ageNum;
	private int skinNum;
	private String text;
	private double Score;
	private String personDate;
	private String productName;
	
	public Reviews() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Reviews(int reviewNum, String id, int ageNum, int skinNum, String text, double score, String personDate,
			String productName) {
		super();
		this.reviewNum = reviewNum;
		this.id = id;
		this.ageNum = ageNum;
		this.skinNum = skinNum;
		this.text = text;
		this.Score = score;
		this.personDate = personDate;
		this.productName = productName;
	}

	public int getReviewNum() {
		return reviewNum;
	}

	public void setReviewNum(int reviewNum) {
		this.reviewNum = reviewNum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAgeNum() {
		return ageNum;
	}

	public void setAgeNum(int ageNum) {
		this.ageNum = ageNum;
	}

	public int getSkinNum() {
		return skinNum;
	}

	public void setSkinNum(int skinNum) {
		this.skinNum = skinNum;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public double getScore() {
		return Score;
	}

	public void setScore(double score) {
		Score = score;
	}

	public String getPersonDate() {
		return personDate;
	}

	public void setPersonDate(String personDate) {
		this.personDate = personDate;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "리뷰번호 : "+reviewNum+",id : "+id+",연령대 : "+
				ageNum+",피부타입 : "+skinNum+", 내용 : "+text+",평점 : "+Score+
				",등록일 : "+personDate+",제품명 : "+productName;
	}
}
