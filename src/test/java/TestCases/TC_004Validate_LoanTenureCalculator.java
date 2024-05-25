package TestCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import PageObjects.HomePage;
import TestBase.BaseClass;

public class TC_004Validate_LoanTenureCalculator extends BaseClass {

	HomePage hp;

	@Test(priority = 0)
	public void Validate_click_Calculators() {

		log.info("----------------- Test Case 4 Started ----------------------");
		try {

			hp = new HomePage(driver);
			hp.click_calculators();
			log.info("click on calculators");

		} catch (Exception e) {
			log.info("click is not done on calculators");
			Assert.fail();
		}

	}

	@Test(priority = 1)
	public void Validate_click_loanCalculatorClick() {
		try {
			hp.loan_Calculator();
			log.info("click on loan calculator");

		} catch (Exception e) {
			log.info("click on loan calculators is not done");
			Assert.fail();
		}
	}

	@Test(priority = 2)
	public void loanTenureCalculatorClick() {
		try {
			hp.loanTenureCalculatorClick();
			log.info("click on loan tenure calculator window");

		} catch (Exception e) {
			log.info("click on loan tenure calculator window is not clicked");
			Assert.fail();
		}
	}

	@Test(priority = 3)
	public void boxValidationCheck() {
		try {
			boolean t1 = hp.loan_amount();
			Assert.assertEquals(t1, true, "Loan Amount TextBox is not Working");
			log.info("Loan Amount TextBox is checked");

			boolean t2 = hp.amountBox();
			Assert.assertEquals(t2, true, "Emi TextBox is not Working");
			log.info("Emi TextBox is checked");

			boolean t3 = hp.interestBox();
			Assert.assertEquals(t3, true, "Interest Rate TextBox is not Working");
			log.info("Interest Rate TextBox is checked");

			boolean t4 = hp.loanFeesBox();
			Assert.assertEquals(t4, true, "Fees And Charges TextBox is not Working");
			log.info("Fees And Charges TextBox is checked");

		} catch (Exception e) {
			log.info("TextBox validation failed");
			Assert.fail();
		}

	}

	@Test(priority = 4)
	public void sliderValidationCheck() {
		try {

			boolean c1 = hp.loanAmount_slider_check();
			Assert.assertEquals(c1, false, "Loan Amount slider is not Working");
			log.info("Loan Amount Slider is checked");

			boolean c2 = hp.emiCalculatorLoanAmountSliderCheck();
			Assert.assertEquals(c2, false, "EMI Slider is not Working");
			log.info("Emi Slider is checked");

			boolean c3 = hp.interestCalculatorLoanAmountSliderCheck();
			Assert.assertEquals(c3, false, "Interest Slider is not Working");
			log.info("Interest Slider is checked");

			boolean c4 = hp.loanFeesCalculatorLoanAmountSliderCheck();
			Assert.assertEquals(c4, false, "LoanFees Slider is not Working");
			log.info("LoanFees Slider is checked");

		} catch (Exception e) {
			log.info("slider validation failed");
			Assert.fail();
		} finally {
			log.info("---------------- Test Case 4 is Completed --------------->>>>>>>>>>>>>>");
		}
	}
}
