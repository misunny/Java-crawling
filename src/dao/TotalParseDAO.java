package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import vo.Product;
import vo.Reviews;
import vo.SelectSearchById;

public class TotalParseDAO {
	private SqlSessionFactory factory = MybatisConfig.getSqlSessionFactory();

	public int insertreview(Reviews r) {
		SqlSession session = null;
		int canReview = 0;
		try {
			session = factory.openSession();
			TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
			canReview = mapper.insertreview(r);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return canReview;
	}
	
	public int insertweb(Reviews r)
	{
		SqlSession session = null;
		int canReview = 0;
		try {
			session = factory.openSession();
			TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
			canReview = mapper.insertweb(r);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return canReview;
	}
	
	public ArrayList<SelectSearchById> searchbyid(String id)
	{
		SqlSession session = null;
		ArrayList<SelectSearchById> list = null;
			try {
				session = factory.openSession();
				TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
				list = mapper.searchbyid(id);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (session != null) {
					session.close();
				}	
			}
			return list;
	}
	
	//카테고리에서 선택 후 각 카테고리별 제품 목록 셀렉트
	public ArrayList<Product> ProductListByCat(int catnum)
	{
		SqlSession session = null;
		ArrayList<Product> list = null;
			try {
				session = factory.openSession();
				TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
				list = mapper.ProductListByCat(catnum);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (session != null) {
					session.close();
				}	
			}
			return list;
	}
	
	// 제품별 평점
	public ArrayList<Reviews> proscore(String proname)
	{
		SqlSession session = null;
		ArrayList<Reviews> list = null;
			try {
				session = factory.openSession();
				TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
				list = mapper.proscore(proname);
			} 
			catch (Exception e) {
				e.printStackTrace();
			}
			finally {
				if (session != null) {
					session.close();
				}	
			}
			return list;
	}
	
	// 나이 10대 평점
		public ArrayList<Reviews> tenage(String proname)
		{
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.tenage(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
		// 나이 20대 평점
		public ArrayList<Reviews> twenage(String proname)
		{
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.twenage(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
		// 나이 30대 평점
		public ArrayList<Reviews> threeage(String proname)
		{
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.threeage(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		// 나이 40대 평점
		public ArrayList<Reviews> fourage(String proname)
		{
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.fourage(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
		// 나이 50대 평점
		public ArrayList<Reviews> fiveage(String proname)
		{
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.fiveage(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}		
		
		//피부타입 verydry
		public List<Reviews> verydry(String proname) {
			// TODO Auto-generated method stub
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.verydry(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
		public List<Reviews> dry(String proname) {
			// TODO Auto-generated method stub
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.dry(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
		public List<Reviews> normal(String proname) {
			// TODO Auto-generated method stub
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.normal(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
		public List<Reviews> combination(String proname) {
			// TODO Auto-generated method stub
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.combination(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
		public List<Reviews> acneprone(String proname) {
			// TODO Auto-generated method stub
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.acneprone(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
		public List<Reviews> oliy(String proname) {
			// TODO Auto-generated method stub
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.oliy(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
		public List<Reviews> veryoliy(String proname) {
			// TODO Auto-generated method stub
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.veryoliy(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
		public List<Reviews> sensitive(String proname) {
			// TODO Auto-generated method stub
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.sensitive(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
		public List<Reviews> other(String proname) {
			// TODO Auto-generated method stub
			SqlSession session = null;
			ArrayList<Reviews> list = null;
				try {
					session = factory.openSession();
					TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
					list = mapper.other(proname);
				} 
				catch (Exception e) {
					e.printStackTrace();
				}
				finally {
					if (session != null) {
						session.close();
					}	
				}
				return list;
		}
		
	public int deletebynum(int reviewNum) {
		int candelete = 0;
		SqlSession session = null;
		try {
			session = factory.openSession();
			TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
			candelete = mapper.deletebynum(reviewNum);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return candelete;
	}
	
	public int updatebynum(Reviews r) {
		int canupdate = 0;
		SqlSession session = null;
		try {
			session = factory.openSession();
			TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
			canupdate = mapper.updatebynum(r);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return canupdate;
	}

	public int insertwebpro(Product p) 
	{
		// TODO Auto-generated method stub
		SqlSession session = null;
		int canReview = 0;
		try 
		{
			session = factory.openSession();
			TotalParseMapper mapper = session.getMapper(TotalParseMapper.class);
			
			canReview = mapper.insertwebpro(p);
			session.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (session != null) {
				session.close();
			}
		}
		return canReview;
	}

	


}
