package Utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {

	//DataProvider 1

	@DataProvider(name="carLoan")
	public String [][] getData() throws IOException
	{
		// String path=".\\testData\\LoginData.xlsx";//taking xl file from testData

		ExcelUtility xlutil=new ExcelUtility();//creating an object for XLUtility

		int totalrows=xlutil.getRowCount("Sheet1");	
		int totalcols=xlutil.getCellCount("Sheet1",1);

		String emidata[][]=new String[totalrows][totalcols];

		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				emidata[i-1][j]= xlutil.getCellData("Sheet1",i, j);  //1,0
			}
		}
		return emidata;//returning two dimension array

	}

	//DataProvider 2

	@DataProvider(name="HomeLoan")
	public String [][] getHomeLoanData() throws IOException
	{
		// String path=".\\testData\\LoginData.xlsx";//taking xl file from testData

		ExcelUtility xlutil=new ExcelUtility();//creating an object for XLUtility

		int totalrows=xlutil.getRowCount("Sheet2");	
		int totalcols=xlutil.getCellCount("Sheet2",1);
		totalcols = totalcols-1;

		String data[][]=new String[totalrows][totalcols];

		for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
		{		
			for(int j=0;j<totalcols;j++)  //0    i is rows j is col
			{
				data[i-1][j]= xlutil.getCellData("Sheet2",i, j);  //1,0
			}
		}
		return data;//returning two dimension array

	}

//		public static void main(String[] args) throws IOException {
//			ExcelUtility xlutil=new ExcelUtility();//creating an object for XLUtility
//	
//			int totalrows=xlutil.getRowCount("Sheet2");	
//			int totalcols=xlutil.getCellCount("Sheet2",1);
//			totalcols=totalcols-1;
//			
//			System.out.println(totalrows);
//			System.out.println(totalcols);
//	
//			String data[][]=new String[totalrows][totalcols];
//	
//			for(int i=1;i<=totalrows;i++)  //1   //read the data from xl storing in two deminsional array
//			{		
//				for(int j=0;j<totalcols;j++)  //0    i is rows j is col
//				{
//					data[i-1][j]= xlutil.getCellData("Sheet2",i, j);  //1,0
//				}
//			}
//		}

	//DataProvider 3

	//DataProvider 4
}
