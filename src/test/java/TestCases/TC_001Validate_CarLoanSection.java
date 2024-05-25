package TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.*;
import PageObjects.HomePage;
import TestBase.BaseClass;
import Utilities.DataProviders;
import Utilities.ExcelUtility;

public class TC_001Validate_CarLoanSection extends BaseClass{
	HomePage hp;
	String p_amount="";
	String rate="";
	String period="";

	@Test(priority=0,dataProvider="carLoan",dataProviderClass=DataProviders.class)
	public void validate_ClickCarLoan(String amount, String roi, String tenure) throws InterruptedException {
		log.info("---------------------Starting Test 1--------------------------");
		try {
			hp= new HomePage(driver);

			//click on carloan
			hp.clickCarLoan();
			log.info("car loan clicked");

			p_amount = amount;
			rate = roi;
			period = tenure;

			
		}
		catch(Exception e)
		{
			log.error("Test failed");
		}
	}
	
	@Test(priority=1,dependsOnMethods= {"validate_ClickCarLoan"})
	public void validate_allEntitySet() throws InterruptedException
	{
		try {
		hp.setLoanAmount(p_amount);
		log.info("Car Loan Amount Set");
		
		hp.setInterest(rate);
		log.info("Interest Rate set");
		
		hp.setLoanTenure(period);
		log.info("Loan Tenure set");
		
		log.info("all entity set");
		}
		catch(Exception e)
		{
			log.error("Test failed");
			Assert.fail();
		}
	}
	@Test(priority=2,dependsOnMethods= {"validate_ClickCarLoan"})
	public void Validate_ExtractionOfemiAmount()
	{
		if(hp.fetch_emiData(p_amount, period))
		{
			Assert.assertTrue(true);
			log.info("Emi amount fetched properly");
		}
		else
		{
			log.error("Emi amount is not fetched properly");
			Assert.fail();
		}
	}

	@Test(priority=3,dependsOnMethods= {"validate_ClickCarLoan"})
	public void Validate_ExcelEmiData() throws IOException
	{
		ExcelUtility eu = new ExcelUtility();
		// output excel file placed in PATH2 in excelUtility.java
		if(eu.get_CellData("sheet1", 1, 0).equals("") && eu.get_CellData("sheet1", 1, 1).equals("") && eu.get_CellData("sheet1", 1, 2).equals("")){
			log.info("Data is not fetched in excel file");
			Assert.fail();
		}
		else {
			log.info("Data is properly fetched in excel file");
			Assert.assertTrue(true);
		}
		log.info("------------- Test Case 1 completed ---------------->>>>>");
	}


}