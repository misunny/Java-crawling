package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import vo.Product;
import vo.Reviews;
import vo.SelectSearchById;

public interface TotalParseMapper {

	int insertweb(Reviews r);
	int insertwebpro(Product p);
	int insertreview(Reviews r);
	int deletebynum(int reviewNum);
	int updatebynum(Reviews r);
	ArrayList<SelectSearchById> searchbyid(String id);

	ArrayList<Product> ProductListByCat(int catnum);
	ArrayList<Reviews> proscore(String proname);
	ArrayList<Reviews> tenage(String proname);
	ArrayList<Reviews> twenage(String proname);
	ArrayList<Reviews> threeage(String proname);
	ArrayList<Reviews> fourage(String proname);
	ArrayList<Reviews> fiveage(String proname);
	ArrayList<Reviews> verydry(String proname);
	ArrayList<Reviews> other(String proname);
	ArrayList<Reviews> sensitive(String proname);
	ArrayList<Reviews> veryoliy(String proname);
	ArrayList<Reviews> oliy(String proname);
	ArrayList<Reviews> acneprone(String proname);
	ArrayList<Reviews> combination(String proname);
	ArrayList<Reviews> normal(String proname);
	ArrayList<Reviews> dry(String proname);
//	ArrayList<Reviews> selectbyproname(String proname);

	
}
