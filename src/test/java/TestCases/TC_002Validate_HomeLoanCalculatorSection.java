package TestCases;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import PageObjects.HomePage;
import TestBase.BaseClass;
import Utilities.DataProviders;

public class TC_002Validate_HomeLoanCalculatorSection extends BaseClass{
	
	HomePage hp;
	
	String homeValue = "";
	String homeDownPayment = "";
	String loanInsurance = "";
	String homeLoaninterest = "";
	String homeLoanTenure = "";
	String Loan_Fees = "";
	String oneTimeExpenses = "";
	String propertyTax = "";
	String HomeInsurance = "";
	String maintainance_expenses = "";
	String month = "";
	String year = "";
	
	
	@Test(priority=0,dataProvider="HomeLoan",dataProviderClass=DataProviders.class)
	public void validate_click_calculators(
			String home_Value ,String home_DownPayment ,String loan_Insurance ,
			String home_Loaninterest ,String home_LoanTenure ,String LoanFees ,String one_TimeExpenses ,
			String property_Tax ,String Home_Insurance ,String maintainanceExpenses, String Month, String Year) throws InterruptedException
	{
		log.info("------------------ Start Test Case 2 -------------------------->>>>>>>>>>>>>");
		
		hp = new HomePage(driver);
		
		homeValue = home_Value;
		homeDownPayment = home_DownPayment;
		loanInsurance = loan_Insurance;
		homeLoaninterest = home_Loaninterest;
		homeLoanTenure = home_LoanTenure;
		Loan_Fees = LoanFees;
		oneTimeExpenses = one_TimeExpenses;
		propertyTax = property_Tax;
		HomeInsurance = Home_Insurance;
		maintainance_expenses = maintainanceExpenses;
		month = Month;
		year = Year;
		
		try {
		hp.click_calculators();
		log.info("click on calculators");
		Thread.sleep(1000);
		}
		catch(Exception e) {
			log.info("click on calculators is not done");
			Assert.fail();
		}
		
	}
	
	@Test(priority=1,dependsOnMethods= {"validate_click_calculators"})
	public void validate_click_HomeLoanEmiCalculator() throws InterruptedException, IOException
	{	
		try {
			hp.click_HomeLoanEmiCalculator();	
			Thread.sleep(2000);	
			log.info("click on Home-Loan-Emi-Calculator");
			}
			catch(Exception e) {
				log.info("click on Home-Loan-Emi-Calculator is not done");
				Assert.fail();
			}
	}
	
	@Test(priority=2,dependsOnMethods= {"validate_click_calculators","validate_click_HomeLoanEmiCalculator"})
	public void validate_HomeValue()
	{
		try {
			hp.sendData_homeValue(homeValue);
			log.info("Home value set");
		}
		catch(Exception e)
		{
			log.info("Home value is not set");
			Assert.fail();
		}
	}
	
	@Test(priority=3,dependsOnMethods= {"validate_click_calculators","validate_click_HomeLoanEmiCalculator"})
	public void validate_HomeDownPayment()
	{
		try {
			hp.sendData_homeDownPayment(homeDownPayment);
			log.info("Home Down Payment set");
		}
		catch(Exception e)
		{
			log.info("Home Down Payment is not set");
			Assert.fail();
		}
	}
	
	@Test(priority=4,dependsOnMethods= {"validate_click_calculators","validate_click_HomeLoanEmiCalculator"})
	public void validate_homeLoanInterest()
	{
		try {
			hp.sendData_homeLoanInterest(homeLoaninterest);
			log.info("Home Interest rate is set");
		}
		catch(Exception e)
		{
			log.info("Home Interest rate is not set");
			Assert.fail();
		}
	}
	
	@Test(priority=5,dependsOnMethods= {"validate_click_calculators","validate_click_HomeLoanEmiCalculator"})
	public void validate_homeLoanTenure()
	{
		try {
			hp.sendData_homeLoanTenure(homeLoanTenure);
			log.info("Home Loan Tenure is set");
		}
		catch(Exception e)
		{
			log.info("Home Loan Tenure is not set");
			Assert.fail();
		}
	}
	
	@Test(priority=6,dependsOnMethods= {"validate_click_calculators","validate_click_HomeLoanEmiCalculator"})
	public void Validate_LoanInsurance_AND_LoanFees()
	{
		try {
			hp.sendData_LoanInsurance_AND_LoanFees(loanInsurance, Loan_Fees);
			log.info("Home Loan Insurance  & Loan Fees is set");
		}
		catch(Exception e)
		{
			log.info("Home Loan Insurance & Loan Fees is not set");
			Assert.fail();
		}
	}
	
	@Test(priority=7,dependsOnMethods= {"validate_click_calculators","validate_click_HomeLoanEmiCalculator"})
	public void Validate_sendData_HomeOwnerExpenses()
	{
		try {
			hp.sendData_HomeOwnerExpenses(oneTimeExpenses, propertyTax, HomeInsurance, maintainance_expenses);
			log.info("Home Owner Expenses is set");
		}
		catch(Exception e)
		{
			log.info("Home Owner Expenses is not set");
			Assert.fail();
		}
	}
	
	@Test(priority=8,dependsOnMethods= {"validate_click_calculators","validate_click_HomeLoanEmiCalculator"})
	public void Validate_sendData_Date()
	{
		try {
			hp.sendData_Date(month, year);
			log.info("Start date is set");
		}
		catch(Exception e)
		{
			log.info("Start date is not set");
			Assert.fail();
		}
	}
	
	@Test(priority=9,dependsOnMethods= {"validate_click_calculators","validate_click_HomeLoanEmiCalculator"})
	public void validate_fetch_table()
	{
		try {
			hp.fetch_table();
			log.info("Table Data fetched properly");
		}
		catch(Exception e)
		{
			log.info("Table Data is not fetched properly");
			Assert.fail();
		}
		finally {
			log.info("-------------- Test Case 2 completed ------------------->>>>>>");
		}
	}

}
