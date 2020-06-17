package ui;

import java.util.ArrayList;
import java.util.List;

import vo.Reviews;

public class Main {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		/*UI실행전 한 번만 실행하면 됨. (사전작업)	
		*웹에서 가져온 데이터 DB에 INSERT하는 작업
		*웹에서 리뷰 글들을 가져올때 글이 많은 경우 
		*DB VARCHAR2의 데이터 타입의 길이를 초과하여 안들어가 오류가 뜹니다.ㅠ
		*그러나 큰 문제가 되지 않아 그냥 실행하시면 됩니다...
		*그리고 데이터가 많아서 엄청 오래걸리는 것이지 절대 안되는 것이 아닙니다! 기다려주세요!!!
		*/
		/*
		*InsertDB t = new InsertDB();
		*t.InsertProductName();
		*t.InsertWebReview();*/
		
		new UI();
	}
}
