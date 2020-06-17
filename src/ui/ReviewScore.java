package ui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReviewScore 
{
	
	public ReviewScore(){};
	
	String[] reviewSplit;
	double TotalScore = 0.0;
	double Score = 0.0;
	int count = 0;
	ArrayList<Double> ReviewsScore = new ArrayList<>();
	
	ExcelRead readmap = new ExcelRead();
	TotalParser tp = new TotalParser();
	HashMap<String, String> map;

	//web���� �޾ƿ� ���並 �м��Ͽ� �����ű�
	public ArrayList<Double> WebCalScore() throws Exception
	{
		List<String> reviews = tp.ReviewParser();
		
		map = readmap.readExcel();
		for(int i = 0; i < reviews.size(); i++)
		{	
			count = 0;
			String review = reviews.get(i);
			review = review.replace("(", " ");
			review = review.replace(")", " ");
			review = review.replace("/", " ");
			review = review.replace(",", " ");
			review = review.replace(".", " ");
			review = review.replace("\"", " ");
			review = review.replace(":", " ");
			review = review.replace("!", " ");
			review = review.toLowerCase();

			reviewSplit = review.split(" ");


			for(int j = 0; j < reviewSplit.length; j++)
			{
				if(map.containsKey(reviewSplit[j]) == true)
				{
					Score = Double.parseDouble(map.get(reviewSplit[j]));
					count += 1;
					TotalScore += Score;
				}
			}
			TotalScore = TotalScore / count;
			ReviewsScore.add(TotalScore);
		}
		return ReviewsScore;
	}
	
	//cmd���� insert�� review text�� �м��� ���� �ű�� �ڵ�.
	public double CalScore(String text) throws Exception
	{
		map = readmap.readExcel();
	
		String review = text;
		review = review.replace("(", " ");
		review = review.replace(")", " ");
		review = review.replace("/", " ");
		review = review.replace(",", " ");
		review = review.replace(".", " ");
		review = review.replace("\"", " ");
		review = review.replace(":", " ");
		review = review.replace("!", " ");
		review = review.toLowerCase();

		reviewSplit = review.split(" ");

		for(int j = 0; j < reviewSplit.length; j++)
		{
			if(map.containsKey(reviewSplit[j]) == true)
			{
				Score = Double.parseDouble(map.get(reviewSplit[j]));
				count += 1;
				TotalScore += Score;
			}
			else 
				TotalScore = 0.0;
		}
		
		if (TotalScore == 0) {
			TotalScore = 0;
		}else {
			TotalScore = (double)TotalScore / count;			
		}

		return TotalScore;
	}
}
