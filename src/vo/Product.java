package vo;

public class Product { 
	private String brandName; 
	private String productName; 
	private int categoryNum;
	

	public Product(String brandName, String productName, int categoryNum) {
		super();
		this.brandName = brandName;
		this.productName = productName;
		this.categoryNum = categoryNum;
	}


	public Product() {
		super();
		// TODO Auto-generated constructor stub
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


	public int getCategoryNum() {
		return categoryNum;
	}


	public void setCategoryNum(int categoryNum) {
		this.categoryNum = categoryNum;
	}


	@Override
	public String toString() {
		return 	"카테고리번호 : "+categoryNum+", 브랜드명 : "+brandName+", 제품명 : "+productName;
	} 
}
