package ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TotalParser 
{
	public TotalParser(){}
	ArrayList<String> ListURL = new ArrayList<>();
	
	Document ProductList = null;	//url받아옴
	Elements selTag = null;			//크롤링 할 태그
	String tagSelect = "";
	String skin = "";
	
	
	List<String> reviewURL = null;	//review 페이지 url을 담은 리스트
	List<String> ageList = null;
	List<String> skinList = null;
	List<String> categoryList = null;
	
	
	//리뷰텍스트, 유저 아이디, 날짜, 브랜드 이름, 제품이름은 여기서 받아옴
	List<String> reviewText = null;
	List<String> userID = null;
	List<String> reviewDate = null;
	List<String> brandList = null;
	List<String> pronameList = null;
			
	//나이, 피부타입, 카테고리는 여기서 받아옴
	ArrayList<Integer> intage = new ArrayList<>();
	ArrayList<Integer> intskin = new ArrayList<>();
	ArrayList<Integer> intcategory = new ArrayList<>();

	String reviews = "";
	String id = "";
	String date = "";
	String age = "";
	String brand = "";
	String category="";
	String productname="";
	
	int age_num = 0;
	int skin_num = 0;
	int category_num = 0;
	
	public ArrayList<String> ListURL()throws Exception 
	{
		//카테고리별 URL이 담긴 List	
//		ListURL.add("https://www.makeupalley.com/product/searching.asp?Brand=&BrandName=&CategoryID=502&title=");//LiquidFoundation
//		ListURL.add("https://www.makeupalley.com/product/searching.asp?Brand=&BrandName=&CategoryID=22&title=");//Sunscreen
		ListURL.add("https://www.makeupalley.com/product/searching.asp?Brand=&BrandName=&CategoryID=707&title=");//Moisturizer
//		ListURL.add("https://www.makeupalley.com/product/searching.asp?Brand=&BrandName=&CategoryID=507&title=");//BB Cream
		return ListURL;
	}//DATA_TO_DB 메소드
	
	public List<String> UrlParser() throws Exception
	{
		ListURL();
		for (int i = 0; i < ListURL.size(); i++) 
		{
			ProductList = Jsoup.connect(ListURL.get(i)).get();
			selTag = ProductList.select("td.no-align");		//<td class="no-align"> 태그 기준으로 자름
			selTag = selTag.select("a");	//href가 있는 a태그 추출
			
			//각 제품목록에서 제품 후기로 들어가기 위해 href 태그만 가져옴
			for (Element e : selTag) 
			{
				tagSelect += e.attr("abs:href");
				tagSelect += '\n';
			}
		}//4개의 url돌리는 for문
		return reviewURL = Arrays.asList(tagSelect.split("\n"));
	}
	
	//카테고리 String
	public List<String> CategoryParser() throws Exception
	{
		ListURL();
		for (int i = 0; i < ListURL.size(); i++) 
		{
			ProductList = Jsoup.connect(ListURL.get(i)).get();
			selTag = ProductList.select("td.no-align");
//			selTag = ProductList.select("a");
			
			for (Element e : selTag) 
			{
				tagSelect += e;
				tagSelect += '\n';
				tagSelect = tagSelect.replaceAll("<td class=(.*)<a href=\"", "");
				tagSelect = tagSelect.replaceAll("\">(.*)", "");
				tagSelect = tagSelect.replaceAll("/product/showreview.asp/", "");
				tagSelect = tagSelect.replaceAll("https://www.makeupalley.comItemId(.*)", "");
				tagSelect = tagSelect.replaceAll("ItemId=(\\d+)/(.+)/(.+)/(.+)", "$4");
//				
			}
			//ItemId=2238/Luminous-Silk-Foundation/Giorgio-Armani/Liquid
		}
		categoryList = Arrays.asList(tagSelect.split("\n"));
//		System.out.println(categoryList.size());
		return categoryList;
	}
	
	//카테고리 번호별로 분류되어있음
	public ArrayList<Integer> ConCatgory() throws Exception
	{
		CategoryParser();
		for(int i = 0; i < categoryList.size(); i++)
		{
			switch(categoryList.get(i))
			{
			case "Liquid":
				category_num = 1;
				break;
			case "Sunscreen":
				category_num = 2;
				break;
			case "Moisturizers":
				category_num = 3;
				break;
			}
			intcategory.add(category_num);
		}
		return intcategory;
	}
	
	//브랜드 이름
	public List<String> BrandParser() throws Exception
	{
		ListURL();
		for (int i = 0; i < ListURL.size(); i++) 
		{
			ProductList = Jsoup.connect(ListURL.get(i)).get();
			selTag = ProductList.select("td.no-align");
			
			for (Element e : selTag) 
			{
				tagSelect += e;
				tagSelect += '\n';
				tagSelect = tagSelect.replaceAll("<td class=(.*)<a href=\"", "");
				tagSelect = tagSelect.replaceAll("\">(.*)", "");
				tagSelect = tagSelect.replaceAll("/product/showreview.asp/", "");
				brand = tagSelect.replaceAll("ItemId=(\\d+)/(.+)/(.+)/(.+)", "$3");
				brand = brand.replace("-", " ");
			}
		}
		return brandList = Arrays.asList(brand.split("\n"));
	}
	
	//제품 이름 
	public List<String> ProNameParser() throws Exception
	{
		ListURL();
		for (int i = 0; i < ListURL.size(); i++) 
		{
			ProductList = Jsoup.connect(ListURL.get(i)).get();
			selTag = ProductList.select("td.no-align");
			
			for (Element e : selTag) 
			{
				tagSelect += e;
				tagSelect += '\n';
				tagSelect = tagSelect.replaceAll("<td class=(.*)<a href=\"", "");
				tagSelect = tagSelect.replaceAll("\">(.*)", "");
				tagSelect = tagSelect.replaceAll("/product/showreview.asp/", "");
				tagSelect = tagSelect.replaceAll("https://www.makeupalley.com", "");
				productname = tagSelect.replaceAll("ItemId=(\\d+)/(.+)/(.+)/(.+)", "$2");
				productname = productname.replaceAll("-", " ");
			}
		}
		return pronameList = Arrays.asList(productname.split("\n"));
	}
	
	public List<String> ReviewParser() throws Exception
	{	
		UrlParser();
		//reviews
		for(int j = 0; j < reviewURL.size(); j++)
		{
			ProductList = Jsoup.connect(reviewURL.get(j)).get();
			selTag = ProductList.select("div.comment-content");
			
			//사용자가 작성한 리뷰를 긁어온 것에 태그제거
			for (Element e2 : selTag) 
			{
				reviews += e2.text();
				reviews +='\n';		
			}
			reviewText = Arrays.asList(reviews.split("\n"));
		}
		return reviewText;
	}
	
	public List<String> IdParser() throws Exception
	{
		UrlParser();
		//후기작성자 id(name)
		for(int j = 0; j < reviewURL.size(); j++)
		{
			ProductList = Jsoup.connect(reviewURL.get(j)).get();
			selTag = ProductList.select("a.track_User_Profile");
			for (Element e3 : selTag) {
				id += e3.text();
				id += '\n';
			}
			userID = Arrays.asList(id.split("\n"));
		}
		return userID;
//			System.out.println(userID);
	}
	
	public List<String> DateParser() throws Exception
	{
		UrlParser();
		//date
		for(int j = 0; j < reviewURL.size(); j++)
		{
			ProductList = Jsoup.connect(reviewURL.get(j)).get();
			selTag = ProductList.select("div.date");
			for(Element e4 : selTag)
			{
				date += e4.text();
				date += '\n';
				date = date.replaceAll("on ", "");
				date = date.replaceAll(" (.*)", "");
				date = date.replaceAll(" (.*)", "");
			}
			reviewDate = Arrays.asList(date.split("\n"));
		}
		return reviewDate;
//			System.out.println(reviewDate);
	}
	public List<String> AgeParser() throws Exception
	{
		UrlParser();
		//나이, 피부타입
		for(int j = 0; j < reviewURL.size(); j++)
		{
			ProductList = Jsoup.connect(reviewURL.get(j)).get();
			selTag = ProductList.select("div.important");
			//나이
			for (Element e5 : selTag) {
				age += e5.text();
				age += '\n';
				age = age.replaceAll(" Skin(.*)\n", "\n");
				age = age.replaceAll("Age: ", "");
				age = age.replaceAll(" & Over", "");
				age = age.replaceAll(" & Under", "");
				age = age.replaceAll("(.*)-", "");
			}
			ageList = Arrays.asList(age.split("\n"));
		}
		return ageList;
	}
	
	public ArrayList<Integer> ConAge() throws Exception
	{
		AgeParser();
		//age처리
		for(int i = 0; i < ageList.size(); i++)
		{
			switch(ageList.get(i))
			{
			case "18":
				age_num = 1;
				break;
			case "24": case "29":
				age_num = 2;
				break;
			case "35":
				age_num = 3;
				break;
			case "43":
				age_num = 4;
				break;
			case "55": case "56":
				age_num = 5;
				break;
			case "Unknow":
				age_num = 0;
			}
			intage.add(age_num);
		}
		return intage;		
	}
	
	public List<String> SkinParser() throws Exception
	{
		UrlParser();
		for(int j = 0; j < reviewURL.size(); j++)
		{
			ProductList = Jsoup.connect(reviewURL.get(j)).get();
			selTag = ProductList.select("div.important");
			//피부타입
			for (Element e6 : selTag)
			{
				skin += e6.text();
				skin += '\n';
				skin = skin.replaceAll("(.*)Skin: ", "");
				skin = skin.replaceAll("Hair(.*)\n", "\n");
				skin = skin.replaceAll(",(.*)\n", "\n");
			}
			skinList = Arrays.asList(skin.split("\n"));
		}
		return skinList;
	}
	
	public ArrayList<Integer> ConSkin() throws Exception
	{
		SkinParser();
		//피부타입 처리
		for(int i = 0; i < skinList.size(); i++)
		{
			switch(skinList.get(i))
			{
			case "Very Dry":
				skin_num = 1;
				break;
			case "Dry":
				skin_num = 2;
				break;
			case "Normal":
				skin_num = 3;
				break;
			case "Combination":
				skin_num = 4;
				break;
			case "Acne-Prone":
				skin_num = 5;
				break;
			case "Oliy":
				skin_num = 6;
				break;
			case "Very Oliy":
				skin_num = 7;
				break;
			case "Sensitive":
				skin_num = 8;
				break;
			case "Other":
				skin_num = 9;
				break;
			}
			intskin.add(skin_num);
		}
		return intskin;
	}

}
