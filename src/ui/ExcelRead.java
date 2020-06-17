package ui;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import jxl.*;
import jxl.write.*;



public class ExcelRead 
{
	Workbook excelFile = null;
	Sheet exampleSheet = null;
	WritableWorkbook workbook = null;
	HashMap<String, String> hashScore = new HashMap<>();


	public HashMap<String, String> readExcel() throws Exception
	{
//		ExcelRead jvEx = new ExcelRead();
//		jvEx.readExcel();
		try
		{
//			System.out.println("***Start:Read**********");
			
			String excelFile1 = "C:/Users/simms/Desktop/FirstTeamProject/src/WordNet.xls";
			excelFile = Workbook.getWorkbook(new File(excelFile1));
			exampleSheet = excelFile.getSheet(0);	//sheetÀÎ½Ä
			
			int total_Row = exampleSheet.getRows();	//Çà ÆÄ¾Ç
			int total_Column = exampleSheet.getColumns();
//			System.out.println("Total Row of Sheet : " + total_Row);
//			System.out.println("Total Column of Sheet : " + total_Column);
//			System.out.println();
		
			
			//¼¿ ÀÐ±â
			for(int i = 0; i < total_Row; i++)
			{
				Cell readCell = exampleSheet.getCell(0, i);
				String stringCell = readCell.getContents();
				
				Cell readCell2 = exampleSheet.getCell(1, i);
				String stringCell2 = readCell2.getContents();
				
				hashScore.put(stringCell,stringCell2);							
			}

//			//hashmapÃâ·ÂÈ®ÀÎ
//			for(int i = 0; i < hashScore.size(); i++)
//			{
//				Iterator iterator = hashScore.entrySet().iterator();
//				while (iterator.hasNext()) 
//				{
//					Entry entry = (Entry)iterator.next();
//					System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//				}
//			}
			//¾×¼¿ ´Ý¾ÆÁÖ±â
			excelFile.close();
			if(workbook != null){
				workbook.close();
			}
//			System.out.println();
//			
//			System.out.println("***End*********");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return hashScore;

	}
}
