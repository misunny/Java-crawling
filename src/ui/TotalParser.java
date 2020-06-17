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
	
	Document ProductList = null;	//url�޾ƿ�
	Elements selTag = null;			//ũ�Ѹ� �� �±�
	String tagSelect = "";
	String skin = "";
	
	
	List<String> reviewURL = null;	//review ������ url�� ���� ����Ʈ
	List<String> ageList = null;
	List<String> skinList = null;
	List<String> categoryList = null;
	
	
	//�����ؽ�Ʈ, ���� ���̵�, ��¥, �귣�� �̸�, ��ǰ�̸��� ���⼭ �޾ƿ�
	List<String> reviewText = null;
	List<String> userID = null;
	List<String> reviewDate = null;
	List<String> brandList = null;
	List<String> pronameList = null;
			
	//����, �Ǻ�Ÿ��, ī�װ��� ���⼭ �޾ƿ�
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
		//ī�װ��� URL�� ��� List	
//		ListURL.add("https://www.makeupalley.com/product/searching.asp?Brand=&BrandName=&CategoryID=502&title=");//LiquidFoundation
//		ListURL.add("https://www.makeupalley.com/product/searching.asp?Brand=&BrandName=&CategoryID=22&title=");//Sunscreen
		ListURL.add("https://www.makeupalley.com/product/searching.asp?Brand=&BrandName=&CategoryID=707&title=");//Moisturizer
//		ListURL.add("https://www.makeupalley.com/product/searching.asp?Brand=&BrandName=&CategoryID=507&title=");//BB Cream
		return ListURL;
	}//DATA_TO_DB �޼ҵ�
	
	public List<String> UrlParser() throws Exception
	{
		ListURL();
		for (int i = 0; i < ListURL.size(); i++) 
		{
			ProductList = Jsoup.connect(ListURL.get(i)).get();
			selTag = ProductList.select("td.no-align");		//<td class="no-align"> �±� �������� �ڸ�
			selTag = selTag.select("a");	//href�� �ִ� a�±� ����
			
			//�� ��ǰ��Ͽ��� ��ǰ �ı�� ���� ���� href �±׸� ������
			for (Element e : selTag) 
			{
				tagSelect += e.attr("abs:href");
				tagSelect += '\n';
			}
		}//4���� url������ for��
		return reviewURL = Arrays.asList(tagSelect.split("\n"));
	}
	
	//ī�װ� String
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
	
	//ī�װ� ��ȣ���� �з��Ǿ�����
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
	
	//�귣�� �̸�
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
	
	//��ǰ �̸� 
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
			
			//����ڰ� �ۼ��� ���並 �ܾ�� �Ϳ� �±�����
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
		//�ı��ۼ��� id(name)
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
		//����, �Ǻ�Ÿ��
		for(int j = 0; j < reviewURL.size(); j++)
		{
			ProductList = Jsoup.connect(reviewURL.get(j)).get();
			selTag = ProductList.select("div.important");
			//����
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
		//ageó��
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
			//�Ǻ�Ÿ��
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
		//�Ǻ�Ÿ�� ó��
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
