package ui;
//����
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import dao.TotalParseDAO;
import vo.Product;
import vo.Reviews;
import vo.SelectSearchById;


public class UI {
	private TotalParseDAO dao = new TotalParseDAO();
	Scanner sc = new Scanner(System.in);

	public UI() throws Exception {
		int option = 0;
		boolean keep = true;
		while(keep) {
			try {
				System.out.println("\n\n====== Smart Beauty ======");
				System.out.println("1.Watch Product BY Category");//ī�װ��� ����, ��ǰ�� ���並 �� �� �߰� ���� ���԰���
				System.out.println("2.Search Product");//��ǰ�� �˻�
				System.out.println("3.Manager Review");//���� ���̵�� �˻� �� ����Modification, ����Delete
				System.out.println("9.Exit");
				System.out.println("=======================");
				System.out.print("���� >>");
				
				option = sc.nextInt();
				sc.nextLine();
				
			} catch (Exception e) {
				System.out.println("[ERROR]�߸��� ���Դϴ�.");
				sc.nextLine();
				continue;
			}	
			switch (option) {
			case 1://ī�װ��� ����, ��ǰ�� ���並 �� �� �߰� ���� ���԰���
				WatchProductBYCategory();
				break;
			case 2://��ǰ�� �˻�
				System.out.println("====Search Product====");
				productInfo();
				break;
			case 3://���� ���̵�� �˻� �� ����Modification, ����Delete
				managerReview();
				break;
			case 9:
				System.out.println("�ý����� �����մϴ�");
				keep = false;
				break;
				
			default:
				System.out.println("[ERROR]�߸��� ���Դϴ�.");
				break;
			}			
		}		
	}//UI		
	
	//���� �޴����� 1������ 	ī�װ� ���
	private void WatchProductBYCategory() throws Exception {
		boolean keep = true;
		int choice = 0;
		//ī�װ��� ����Ʈ �θ��� �� ����		��ǰ ��� ���
		int selectCategory = selectCategory();
		// ī�װ��� �´�  productlist ���
		System.out.println("===Product List===");
		ArrayList<Product> list = dao.ProductListByCat(selectCategory);
		int count = 0;
		for(int i = 0; i < list.size(); i++)
		{
			count+=1;
			System.out.println(count + ". " + list.get(i).getProductName());
		}
		//��ǰ�� ������ (������) ���
		String proname = productInfo();
		
	}
	
	//���並 �ۼ��� ���� ���� �޼ҵ�
	private void reviewWrite(String name) throws Exception
	{
		boolean keep = true;
		int choice = 0;
		while(keep) {
			try{		
				System.out.println("\n\n���並 �ۼ��Ͻðڽ��ϱ�?");
				System.out.println("1.yes 2.no");
				System.out.print("�Է� >>");
				choice = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("[ERROR]�߸��� ���Դϴ�.");
				sc.nextLine();
				continue;
			}
			
			switch (choice) {
			case 1:
				insertReview(name);//���� ����
				break;
			case 2:
				System.out.println("�޴��� ���ư��ϴ�");
				keep = false;
				break;
			default:
				System.out.println("[ERROR]�߸��� ���Դϴ�.");
				break;
			}
		}	
	}

	private int selectCategory() 
	{
		boolean keep = true;
		int choice = 0;
		while(keep) 
		{
			try 
			{
				System.out.println("====ī�װ� �˻�====");
				System.out.println("1. LiquidFoundation");
				System.out.println("2. Sunscreen");
				System.out.println("3. Moisturizer");
				System.out.print("����>>");
				
				choice = sc.nextInt();
				sc.nextLine();
			} 
			catch (Exception e) {
				System.out.println("[ERROR]�߸��� ���Դϴ�.\n\n");
				sc.nextLine();
			}
			if (choice>3||choice<1) {
				System.out.println("[ERROR]�߸��� ���Դϴ�.\n\n");
				continue;
			}
			keep = false;
		}//while
		return choice;
	}
	
	//��ǰ�̸����� ��ǰ�� ���������� ���
	private String productInfo() throws Exception
	{
		//��ǰ ��� ����
		System.out.print("\n\n��ǰ �̸��� �Է��ϼ��� >> ");
		String proname = sc.nextLine();
		ArrayList<Product> list1 = dao.ProductListByCat(1);
		ArrayList<Product> list2 = dao.ProductListByCat(2);
		ArrayList<Product> list3 = dao.ProductListByCat(3);
		
		boolean result = false;
		for (Product p : list1) {
			if (p.getProductName().equals(proname)) {
				result = true;
			}
		}		
		for (Product p : list2) {
			if (p.getProductName().equals(proname)) {
				result = true;
			}
		}
		for (Product p : list3) {
			if (p.getProductName().equals(proname)) {
				result = true;
			}
		}
		
		if (result==false) {
			System.out.println("��Ȯ�� �̸��� �ƴϰų�    ��ǰ ���䰡 �����ϴ�.");
			return null;
		}
		
		List<Reviews> review = dao.proscore(proname);
		
		//��ǰ �� ����
		System.out.println("\n=========Product Infomation=========");
		

		for(int i = 0; i < review.size(); i++)
		{
			System.out.printf("���� : %.2f", review.get(i).getScore());
		}
		//���ɴ뺰 ����
		System.out.println("\n\n===���ɴ뺰 ����===");
		
		List<Reviews> tenage = dao.tenage(proname);
		List<Reviews> twenage = dao.twenage(proname);
		List<Reviews> threeage = dao.threeage(proname);
		List<Reviews> fourage = dao.fourage(proname);
		List<Reviews> fiveage = dao.fiveage(proname);

		for (Reviews t : tenage) {
			System.out.printf("10�� ���� : %.2f", t.getScore());
		}
		for (Reviews t : twenage) {
			System.out.printf("\n20�� ���� : %.2f", t.getScore());
		}	
		for (Reviews t : threeage) {
			System.out.printf("\n30�� ���� : %.2f", t.getScore());
		}		
		for (Reviews t : fourage) {
			System.out.printf("\n40�� ���� : %.2f", t.getScore());
		}	
		for (Reviews t : fiveage) {
			System.out.printf("\n50�� �̻� ���� : %.2f", t.getScore());
		}	
		
		
		//�Ǻ�Ÿ��
		System.out.println("\n\n===�Ǻ�Ÿ�Ժ� ����===");
		List<Reviews> verydry = dao.verydry(proname);
		List<Reviews> dry = dao.dry(proname);
		List<Reviews> normal = dao.normal(proname);
		List<Reviews> combination = dao.combination(proname);
		List<Reviews> acneprone = dao.acneprone(proname);
		List<Reviews> oliy = dao.oliy(proname);
		List<Reviews> veryoliy = dao.veryoliy(proname);
		List<Reviews> sensitive = dao.sensitive(proname);		
		List<Reviews> other = dao.other(proname);

		
		for (Reviews r : verydry) {
			System.out.printf("very dry ���� : %.2f",r.getScore());
		}
		for (Reviews r : dry) {
			System.out.printf("\ndry ���� : %.2f", r.getScore());
		}
		for (Reviews r : normal) {
			System.out.printf("\nnormal ���� : %.2f", r.getScore());
		}
		for (Reviews r : combination) {
			System.out.printf("\ncombination ���� : %.2f", r.getScore());
		}
		for (Reviews r : acneprone) {
			System.out.printf("\nacneprone ���� : %.2f", r.getScore());
		}
		for (Reviews r : oliy) {
			System.out.printf("\noliy ���� : %.2f", r.getScore());
		}
		for (Reviews r : veryoliy) {
			System.out.printf("\nvery oliy ���� : %.2f", r.getScore());
		}
		for (Reviews r : sensitive) {
			System.out.printf("\nsensitive ���� :%.2f ", r.getScore());
		}
		for (Reviews r : other) {
			System.out.printf("\nother ���� : %.2f", r.getScore());
			
			//�����ۼ��ҰŴ�?
			reviewWrite(proname);
		}
		return proname;
	}


	private void insertReview(String productName) throws Exception {//���� ����~!!!
		
		int selectAge=0, selectSkinType=0, canInsert=0;
		String writer="", writetext="";
		boolean keep;
				
		System.out.println("\n\n======Write review======");
		System.out.println("\n===id �Է�===");
		System.out.print("�Է�>>");
		writer = sc.nextLine();
		
		keep = true;
		while(keep) {
			try {
				System.out.println("\n===���� ���� �޴�===");
				System.out.println("1. 18�� ����");
				System.out.println("2. 19-29");
				System.out.println("3. 30-35");
				System.out.println("4. 36-43");
				System.out.println("5. 44-55");
				System.out.print("��ȣ�� �Է�  >> ");
				selectAge = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("[ERROR]�߸��� ���Դϴ�.");
				sc.nextLine();
			}
			if (selectAge<1||selectAge>5) {
				System.out.println("[ERROR]�߸��� ���Դϴ�.");
				continue;
			}
			keep = false;
			
		}
		keep = true;
		while(keep) {
			try {
				System.out.println("===�Ǻ�Ÿ�� ���� �޴�===");
				System.out.println("1. Very Dry");
				System.out.println("2. Dry");
				System.out.println("3. Normal");
				System.out.println("4. Combination");
				System.out.println("5. Acne-Prone");
				System.out.println("6. Oliy");
				System.out.println("7. Very Oliy");
				System.out.println("8. Sensitive");
				System.out.println("9. Other");
				System.out.print("Skin type >> ");
			
				selectSkinType = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("[ERROR]�߸��� ���Դϴ�.");
				sc.nextLine();
			}
			if (selectSkinType<1||selectSkinType>9) {
				System.out.println("[ERROR]�߸��� ���Դϴ�.");
				continue;
			}
			keep = false;
		}
		
		System.out.print("�����Է� >> ");
		writetext = sc.nextLine();
		
		ReviewScore rs = new ReviewScore();
		double d = rs.CalScore(writetext);
	
		Reviews r = new Reviews();
		r.setAgeNum(selectAge);//0
		r.setId(writer);
		r.setProductName(productName);//null
		r.setSkinNum(selectSkinType);//0
		r.setText(writetext);
		r.setScore(d);//0
		
		
		canInsert = dao.insertreview(r);
		
		if (canInsert ==0) {
			System.out.println("���� ��� ����");
		}else {
			System.out.println("���� ��� ����");
		}
	}

	private void managerReview() {
		boolean keep = true;
		int option = 0;
		while(keep) 
		{
			try 
			{
				System.out.println("\n\n====Review Manager====");
				System.out.println("1.Modification Review");
				System.out.println("2.Delete Review");
				System.out.println("3.SearchReviewById");
				System.out.println("4.Out To Menu");
				System.out.println("======================");
				System.out.print("�޴� ���� >>");
				option = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("[ERROR]�߸��� ���Դϴ�.");
				sc.nextLine();
				continue;
			}
			switch (option) 
			{
				case 1:
					if (ModificationReview() != 0)
						keep = false;
					break;
				case 2:
					if (DeleteReview() != 0)
						keep = false;
					break;
				case 3:
					searchReviewById();
					break;
				case 4:
					System.out.println("�޴��� ���ư��ϴ�");
					keep = false;
					break;
				default:
					System.out.println("[ERROR]�߸��� ���Դϴ�.");
					break;
			}//switch
		}
	}

	private void searchReviewById() {
		System.out.println("\n\n====Search Review====");
		System.out.print("�˻��� Review�� id�� �Է����ּ��� >> ");
		String writer = sc.nextLine();
		ArrayList<SelectSearchById> list = new ArrayList<>();
		list = dao.searchbyid(writer);
		
		if (list.size()==0) {
			System.out.println("�˻� ����� �����ϴ�!");
			return;
		}
		
		System.out.println("�˻�����Դϴ�.");
		System.out.println("\n\n====Review List====");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}




	private int DeleteReview() {//���� ����
	      System.out.println("\n\n====DeleteReview====");
	      System.out.print("������ �����ȣ�� �Է��ϼ��� >> ");
	      
	      int choice = 0;
	      int count = 0;
	      while(choice<=0) {
	         try {
	            choice = sc.nextInt();
	            sc.nextLine();
	            
	         } catch (Exception e) {
	            System.out.println("[ERROR]�߸��� ���Դϴ�.");
	            sc.nextLine();
	            choice = Integer.MAX_VALUE;
	         }
	         
	         count = dao.deletebynum(choice);
	         if (count ==0) {
	            System.out.println("�������� �� �߽��ϴ�");
	         }else {
	            System.out.println("�����Ͽ����ϴ�.");
	         }
	      }
	      return count;
	}




	private int ModificationReview() {
		System.out.println("\n\n====ModificationReview====");
	/*	UPDATE reviews
		SET
		text = #{text},
		Score = #{score},
		personDate = SYSDATE,
		WHERE
		id = #{id}*/
		int choice = 0;
		int count = 0;
		while(choice<=0) {			
			Reviews r = new Reviews();
			try {
				System.out.print("������ �����ȣ�� �Է��ϼ��� >> ");
				choice = sc.nextInt();
				sc.nextLine();
				System.out.print("������ �Է��ϼ��� >> ");
				String writetext = sc.nextLine();
				
				int score = 5;
				r.setReviewNum(choice);
				r.setText(writetext);
				r.setScore(score);
			
				
			} catch (Exception e) {
				System.out.println("[ERROR]�߸��� ���Դϴ�.");
				sc.nextLine();
				continue;
			}
			
			count = dao.updatebynum(r);
			if (count == 0) {
				System.out.println("������ �����Ͽ����ϴ�.");
			}else {
				System.out.println("�����Ͽ����ϴ�.");
			}
		}
		return count;
		
	}

	
}
