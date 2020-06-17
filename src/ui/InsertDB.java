package ui;

import java.util.ArrayList;
import java.util.List;

import dao.TotalParseDAO;
import vo.Product;
import vo.Reviews;


public class InsertDB
{
//	public String id;
//	public int ageNum;
//	public int skinNum;
//	public String text;
//	public double Score;
//	public String personDate;
//	public String productName;
	
	private TotalParseDAO dao = new TotalParseDAO();
	TotalParser tp = new TotalParser();
	int canInsert = 0;
	int count = 0;
	
	//REIVEW data삽입
	public void InsertWebReview() throws Exception
	{
		//score에 담기 위해 텍스트를 분석하는 클래스 생성
		ReviewScore rs = new ReviewScore();
		
		//TotalParser의 파싱된 데이터들 받아온다.
		List<String> idList = tp.IdParser();
		ArrayList<Integer> ageList = tp.ConAge();
		ArrayList<Integer> skinList = tp.ConSkin();
		List<String> textList = tp.ReviewParser();
		ArrayList<Double> scoreList = rs.WebCalScore();
		List<String> dateList = tp.DateParser();
		List<String> proNameList = tp.ProNameParser();
		
		Reviews r = new Reviews();
		for(int i = 0; i < idList.size(); i++)
		{
			//객체 r에 review의 정보들을 저장한다.

			r.setId(idList.get(i));
			r.setAgeNum(ageList.get(i));
			r.setSkinNum(skinList.get(i));
			r.setText(textList.get(i));
			r.setScore(scoreList.get(i));
			r.setPersonDate(dateList.get(i));
			r.setProductName(proNameList.get(i));
			count += 1;
			//DAO에 객체 r을 전달한다.
			canInsert = dao.insertweb(r);
			if(canInsert != 0)
			{
				System.out.println(canInsert + "웹 데이터 삽입 성공");
			}
			else
				System.out.println("웹데이터 삽입 실패");
		}
	}

	//제품이름, 브랜드 이름, 카테고리 번호 DB에 넣기
	public void InsertProductName() throws Exception
	{
		List<String> proname = tp.ProNameParser();
		List<String> probrand = tp.BrandParser();
		ArrayList<Integer> catnum = tp.ConCatgory();
		
		Product p = new Product();
		for(int i = 0; i < proname.size(); i++)
		{

			p.setBrandName(probrand.get(i));
			p.setProductName(proname.get(i));
			p.setCategoryNum(catnum.get(i));
			canInsert = dao.insertwebpro(p);
			if(canInsert != 0)
			{
				System.out.println(canInsert + "웹 데이터 삽입 성공");
			}
			else
				System.out.println("웹데이터 삽입 실패");
		}
	}
}
