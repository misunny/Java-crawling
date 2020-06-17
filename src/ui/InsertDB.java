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
	
	//REIVEW data����
	public void InsertWebReview() throws Exception
	{
		//score�� ��� ���� �ؽ�Ʈ�� �м��ϴ� Ŭ���� ����
		ReviewScore rs = new ReviewScore();
		
		//TotalParser�� �Ľ̵� �����͵� �޾ƿ´�.
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
			//��ü r�� review�� �������� �����Ѵ�.

			r.setId(idList.get(i));
			r.setAgeNum(ageList.get(i));
			r.setSkinNum(skinList.get(i));
			r.setText(textList.get(i));
			r.setScore(scoreList.get(i));
			r.setPersonDate(dateList.get(i));
			r.setProductName(proNameList.get(i));
			count += 1;
			//DAO�� ��ü r�� �����Ѵ�.
			canInsert = dao.insertweb(r);
			if(canInsert != 0)
			{
				System.out.println(canInsert + "�� ������ ���� ����");
			}
			else
				System.out.println("�������� ���� ����");
		}
	}

	//��ǰ�̸�, �귣�� �̸�, ī�װ� ��ȣ DB�� �ֱ�
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
				System.out.println(canInsert + "�� ������ ���� ����");
			}
			else
				System.out.println("�������� ���� ����");
		}
	}
}
