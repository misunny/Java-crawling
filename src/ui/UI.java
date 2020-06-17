package ui;
//새것
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
				System.out.println("1.Watch Product BY Category");//카테고리별 보기, 제품과 리뷰를 본 뒤 추가 리뷰 삽입가능
				System.out.println("2.Search Product");//제품명 검색
				System.out.println("3.Manager Review");//리뷰 아이디로 검색 후 수정Modification, 삭제Delete
				System.out.println("9.Exit");
				System.out.println("=======================");
				System.out.print("선택 >>");
				
				option = sc.nextInt();
				sc.nextLine();
				
			} catch (Exception e) {
				System.out.println("[ERROR]잘못된 값입니다.");
				sc.nextLine();
				continue;
			}	
			switch (option) {
			case 1://카테고리별 보기, 제품과 리뷰를 본 뒤 추가 리뷰 삽입가능
				WatchProductBYCategory();
				break;
			case 2://제품명 검색
				System.out.println("====Search Product====");
				productInfo();
				break;
			case 3://리뷰 아이디로 검색 후 수정Modification, 삭제Delete
				managerReview();
				break;
			case 9:
				System.out.println("시스템을 종료합니다");
				keep = false;
				break;
				
			default:
				System.out.println("[ERROR]잘못된 값입니다.");
				break;
			}			
		}		
	}//UI		
	
	//메인 메뉴에서 1번선택 	카테고리 출력
	private void WatchProductBYCategory() throws Exception {
		boolean keep = true;
		int choice = 0;
		//카테고리별 리스트 부르기 후 선택		제품 목록 출력
		int selectCategory = selectCategory();
		// 카테고리에 맞는  productlist 출력
		System.out.println("===Product List===");
		ArrayList<Product> list = dao.ProductListByCat(selectCategory);
		int count = 0;
		for(int i = 0; i < list.size(); i++)
		{
			count+=1;
			System.out.println(count + ". " + list.get(i).getProductName());
		}
		//제품의 상세정보 (평점들) 출력
		String proname = productInfo();
		
	}
	
	//리뷰를 작성할 건지 묻는 메소드
	private void reviewWrite(String name) throws Exception
	{
		boolean keep = true;
		int choice = 0;
		while(keep) {
			try{		
				System.out.println("\n\n리뷰를 작성하시겠습니까?");
				System.out.println("1.yes 2.no");
				System.out.print("입력 >>");
				choice = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("[ERROR]잘못된 값입니다.");
				sc.nextLine();
				continue;
			}
			
			switch (choice) {
			case 1:
				insertReview(name);//리뷰 삽입
				break;
			case 2:
				System.out.println("메뉴로 돌아갑니다");
				keep = false;
				break;
			default:
				System.out.println("[ERROR]잘못된 값입니다.");
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
				System.out.println("====카테고리 검색====");
				System.out.println("1. LiquidFoundation");
				System.out.println("2. Sunscreen");
				System.out.println("3. Moisturizer");
				System.out.print("선택>>");
				
				choice = sc.nextInt();
				sc.nextLine();
			} 
			catch (Exception e) {
				System.out.println("[ERROR]잘못된 값입니다.\n\n");
				sc.nextLine();
			}
			if (choice>3||choice<1) {
				System.out.println("[ERROR]잘못된 값입니다.\n\n");
				continue;
			}
			keep = false;
		}//while
		return choice;
	}
	
	//제품이름으로 제품의 리뷰정보를 출력
	private String productInfo() throws Exception
	{
		//제품 목록 선택
		System.out.print("\n\n제품 이름을 입력하세요 >> ");
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
			System.out.println("정확한 이름이 아니거나    제품 리뷰가 없습니다.");
			return null;
		}
		
		List<Reviews> review = dao.proscore(proname);
		
		//제품 상세 설명
		System.out.println("\n=========Product Infomation=========");
		

		for(int i = 0; i < review.size(); i++)
		{
			System.out.printf("평점 : %.2f", review.get(i).getScore());
		}
		//연령대별 평점
		System.out.println("\n\n===연령대별 평점===");
		
		List<Reviews> tenage = dao.tenage(proname);
		List<Reviews> twenage = dao.twenage(proname);
		List<Reviews> threeage = dao.threeage(proname);
		List<Reviews> fourage = dao.fourage(proname);
		List<Reviews> fiveage = dao.fiveage(proname);

		for (Reviews t : tenage) {
			System.out.printf("10대 평점 : %.2f", t.getScore());
		}
		for (Reviews t : twenage) {
			System.out.printf("\n20대 평점 : %.2f", t.getScore());
		}	
		for (Reviews t : threeage) {
			System.out.printf("\n30대 평점 : %.2f", t.getScore());
		}		
		for (Reviews t : fourage) {
			System.out.printf("\n40대 평점 : %.2f", t.getScore());
		}	
		for (Reviews t : fiveage) {
			System.out.printf("\n50대 이상 평점 : %.2f", t.getScore());
		}	
		
		
		//피부타입
		System.out.println("\n\n===피부타입별 평점===");
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
			System.out.printf("very dry 평점 : %.2f",r.getScore());
		}
		for (Reviews r : dry) {
			System.out.printf("\ndry 평점 : %.2f", r.getScore());
		}
		for (Reviews r : normal) {
			System.out.printf("\nnormal 평점 : %.2f", r.getScore());
		}
		for (Reviews r : combination) {
			System.out.printf("\ncombination 평점 : %.2f", r.getScore());
		}
		for (Reviews r : acneprone) {
			System.out.printf("\nacneprone 평점 : %.2f", r.getScore());
		}
		for (Reviews r : oliy) {
			System.out.printf("\noliy 평점 : %.2f", r.getScore());
		}
		for (Reviews r : veryoliy) {
			System.out.printf("\nvery oliy 평점 : %.2f", r.getScore());
		}
		for (Reviews r : sensitive) {
			System.out.printf("\nsensitive 평점 :%.2f ", r.getScore());
		}
		for (Reviews r : other) {
			System.out.printf("\nother 평점 : %.2f", r.getScore());
			
			//리뷰작성할거니?
			reviewWrite(proname);
		}
		return proname;
	}


	private void insertReview(String productName) throws Exception {//리뷰 삽입~!!!
		
		int selectAge=0, selectSkinType=0, canInsert=0;
		String writer="", writetext="";
		boolean keep;
				
		System.out.println("\n\n======Write review======");
		System.out.println("\n===id 입력===");
		System.out.print("입력>>");
		writer = sc.nextLine();
		
		keep = true;
		while(keep) {
			try {
				System.out.println("\n===나이 선택 메뉴===");
				System.out.println("1. 18세 이하");
				System.out.println("2. 19-29");
				System.out.println("3. 30-35");
				System.out.println("4. 36-43");
				System.out.println("5. 44-55");
				System.out.print("번호로 입력  >> ");
				selectAge = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("[ERROR]잘못된 값입니다.");
				sc.nextLine();
			}
			if (selectAge<1||selectAge>5) {
				System.out.println("[ERROR]잘못된 값입니다.");
				continue;
			}
			keep = false;
			
		}
		keep = true;
		while(keep) {
			try {
				System.out.println("===피부타입 선택 메뉴===");
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
				System.out.println("[ERROR]잘못된 값입니다.");
				sc.nextLine();
			}
			if (selectSkinType<1||selectSkinType>9) {
				System.out.println("[ERROR]잘못된 값입니다.");
				continue;
			}
			keep = false;
		}
		
		System.out.print("리뷰입력 >> ");
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
			System.out.println("리뷰 등록 실패");
		}else {
			System.out.println("리뷰 등록 성공");
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
				System.out.print("메뉴 선택 >>");
				option = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				System.out.println("[ERROR]잘못된 값입니다.");
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
					System.out.println("메뉴로 돌아갑니다");
					keep = false;
					break;
				default:
					System.out.println("[ERROR]잘못된 값입니다.");
					break;
			}//switch
		}
	}

	private void searchReviewById() {
		System.out.println("\n\n====Search Review====");
		System.out.print("검색할 Review의 id를 입력해주세요 >> ");
		String writer = sc.nextLine();
		ArrayList<SelectSearchById> list = new ArrayList<>();
		list = dao.searchbyid(writer);
		
		if (list.size()==0) {
			System.out.println("검색 결과가 없습니다!");
			return;
		}
		
		System.out.println("검색결과입니다.");
		System.out.println("\n\n====Review List====");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}




	private int DeleteReview() {//리뷰 삭제
	      System.out.println("\n\n====DeleteReview====");
	      System.out.print("삭제할 리뷰번호를 입력하세요 >> ");
	      
	      int choice = 0;
	      int count = 0;
	      while(choice<=0) {
	         try {
	            choice = sc.nextInt();
	            sc.nextLine();
	            
	         } catch (Exception e) {
	            System.out.println("[ERROR]잘못된 값입니다.");
	            sc.nextLine();
	            choice = Integer.MAX_VALUE;
	         }
	         
	         count = dao.deletebynum(choice);
	         if (count ==0) {
	            System.out.println("삭제하지 못 했습니다");
	         }else {
	            System.out.println("삭제하였습니다.");
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
				System.out.print("수정할 리뷰번호를 입력하세요 >> ");
				choice = sc.nextInt();
				sc.nextLine();
				System.out.print("내용을 입력하세요 >> ");
				String writetext = sc.nextLine();
				
				int score = 5;
				r.setReviewNum(choice);
				r.setText(writetext);
				r.setScore(score);
			
				
			} catch (Exception e) {
				System.out.println("[ERROR]잘못된 값입니다.");
				sc.nextLine();
				continue;
			}
			
			count = dao.updatebynum(r);
			if (count == 0) {
				System.out.println("수정에 실패하였습니다.");
			}else {
				System.out.println("수정하였습니다.");
			}
		}
		return count;
		
	}

	
}
