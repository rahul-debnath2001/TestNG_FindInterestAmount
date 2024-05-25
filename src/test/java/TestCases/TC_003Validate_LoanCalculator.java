package TestCases;

import org.testng.Assert;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;

import PageObjects.HomePage;
import TestBase.BaseClass;

public class TC_003Validate_LoanCalculator extends BaseClass {
	HomePage hp;
	SoftAssert sa;

	@Test(priority=0)
	public void Validate_click_Calculators() {

		log.info("----------------- Test Case 3 Started ----------------------");
		try {

			hp= new HomePage(driver);
			sa = new SoftAssert();
			hp.click_calculators();
			log.info("click on calculators");


		}catch(Exception e) {
			log.info("click is not done on calculators");
			Assert.fail();
		}

	}
	@Test(priority=1)
	public void Validate_click_loanCalculatorClick() {
		try {
			hp.loan_Calculator();
			log.info("click on loan calculator");

		}catch(Exception e) {
			log.info("click on loan calculators is not done");
			Assert.fail();
		}
	}
	@Test(priority=2)
	public void Validate_click_loanAmountCalculatorClick() {
		try {
			hp.loanAmountCalculatorClick();
			log.info("click on loan Amount calculator window");

		}catch(Exception e) {
			Assert.fail();
			log.info("click on loan Amount calculator window is not done");
		}
	}
	@Test(priority=3)
	public void Validate_boxValidationCheck() { 
		try {
			boolean t1=hp.amountBox();
			sa.assertEquals(t1,false,"Emi TextBox is not Working");
			log.info("Emi TextBox is checked");
		}
		catch(Exception e)
		{
			log.info("Emi TextBox is not checked");
			sa.fail();
		}
		try {
			boolean t2=hp.interestBox();
			sa.assertEquals(t2,true,"Interest Rate TextBox is not Working");
			log.info("Interest Rate TextBox is checked");
		} catch (Exception e) {
			log.info("Interest Rate TextBox is not checked");
			sa.fail();
		}
		try {
			boolean t3=hp.loanTenureBox();
			sa.assertEquals(t3,true,"Loan Tenure TextBox is not Working");
			log.info("Loan Tenure TextBox is checked");
		} catch (Exception e) {
			log.info("Loan Tenure TextBox is not checked");
			sa.fail();
		}
		try {
			boolean t4=hp.loanFeesBox();
			sa.assertEquals(t4,true,"Fees And Charges TextBox is not Working");
			log.info("Fees And Charges TextBox is checked");
		} catch (Exception e) {
			log.info("Fees And Charges TextBox is not checked");
			sa.fail();
		}

		sa.assertAll();
	}
	@Test(priority=4)
	public void Validate_loanAmountYearToMonth() {
		int cal=hp.Tenure()*12; // convert year into month
		hp.Months(); // click on month
		//System.out.println(cal);
		int exp=hp.Tenure(); // again fetching the same text box but this time it is month
		//System.out.println(exp);
		try {
			Assert.assertEquals(cal,exp,"Calculated Tenure Months And Expected Months Mismatched");
			log.info("year and month is checked");
		}
		catch(Exception e)
		{
			log.info("year and month mismatched");
			Assert.fail();
		}	
	}

	@Test(priority=5)
	public void emiAmountCalculatorSliderValidating() {

		try {
			boolean s1=hp.emiCalculatorLoanAmountSliderCheck();
			sa.assertEquals(s1,false,"EMI Slider is not Working");
			log.info("Emi Slider is checked");
		} catch (Exception e) {
			log.info("Emi Slider is not checked");
			sa.fail();
		}


		try {
			boolean s2=hp.interestCalculatorLoanAmountSliderCheck();
			sa.assertEquals(s2,false,"Interest Slider is not Working");
			log.info("Interest Slider is checked");
		} catch (Exception e) {
			log.info("Interest Slider is not checked");
			sa.fail();
		}


		try {
			boolean s3=hp.loanTenureCalculatorLoanAmountSliderCheck();
			sa.assertEquals(s3,false,"LoanTenure Slider is not Working");
			log.info("LoanTenure Slider is checked");
		} catch (Exception e) {
			log.info("LoanTenure Slider is not checked");
			sa.fail();
		}


		try {
			boolean s4=hp.loanFeesCalculatorLoanAmountSliderCheck();
			sa.assertEquals(s4,true,"LoanFees Slider is not Working");
			log.info("LoanFees Slider is checked");
		} catch (Exception e) {
			log.info("LoanFees Slider is not checked");
			sa.fail();
		}
		log.info("-------------- Test Case 3 is Completed--------------------->>>>>>>>>>>>");
		sa.assertAll();	
	}


}
