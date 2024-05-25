package PageObjects;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.ExcelUtility;

public class HomePage extends BasePage {

	WebDriverWait mywait;
	JavascriptExecutor js;
	public HomePage(WebDriver driver) {
		super(driver);
		js =  (JavascriptExecutor)driver;
		mywait = new WebDriverWait(driver,Duration.ofSeconds(10));
	}
	
	//------------------- Test Case 1 -------------------------------->>>>>>>>>>>>>>>>>
	@FindBy(xpath="//li[@id='car-loan']/a")
		WebElement btn_carloan;
	
	@FindBy(xpath="//input[@id='loanamount']")
		WebElement car_loan_amount;
	
	@FindBy(xpath="//input[@id='loaninterest']")
		WebElement interest_rate;
	
	@FindBy(xpath="//input[@id='loanterm']")
		WebElement loan_tenure;

	@FindBy(xpath="//div[@id=\"emiamount\"]//span")
		WebElement txt_emiAmount;
	
	@FindBy(xpath="//*[@id=\"leschemewrapper\"]//label[@class=\"btn btn-secondary\"]")
		WebElement btn_advEmi;
	
	
	// ----------------------------- Test Case 2 ----------------------->>>>>>>>>>>>>>>>>>>>>>>>>>
	@FindBy(xpath="//a[contains(@id,\"2696\")]")
		WebElement btn_calculators;
	
	@FindBy(xpath="//a[contains(@title,\"Home Loan\")]")
		WebElement btn_homeLoanEmiCalculator;
	
	@FindBy(xpath="//*[@id='homeprice']")
		WebElement box_homeValue;
	
	@FindBy(xpath="//*[@id='downpayment']")
		WebElement box_homeDownPayment;
	
	@FindBy(xpath="//input[@id=\"homeloaninsuranceamount\"]")
		WebElement box_loanInsurance;
	
	@FindBy(xpath="//input[@id=\"homeloaninterest\"]")
		WebElement box_homeLoaninterest;
	
	@FindBy(xpath="//input[@id=\"homeloanterm\"]")
		WebElement box_homeLoanTenure;
	
	@FindBy(xpath="//input[@id=\"loanfees\"]")
		WebElement box_Loan_Fees;
	
	@FindBy(xpath="//input[@id=\"startmonthyear\"]")
		WebElement date_startDate;
	
	@FindBy(xpath="//input[@id=\"onetimeexpenses\"]")
	WebElement box_oneTimeExpenses;
	
	@FindBy(xpath="//input[@id=\"propertytaxes\"]")
	WebElement box_propertyTax;
	
	@FindBy(xpath="//input[@id=\"homeinsurance\"]")
	WebElement box_HomeInsurance;
	
	@FindBy(xpath="//input[@id=\"maintenanceexpenses\"]")
	WebElement box_maintainance_expenses;
	
	
	
	//--------TC003 xpath-----------//
		
		@FindBy(xpath="//a[@title='Loan Calculator']")
			WebElement calculator_click; 
		
		@FindBy(xpath="//*[@id='loan-amount-calc']/a[1]")
			WebElement loan_amount_calculator_click;
		
		@FindBy(xpath="//*[@id='loanemi']")
			WebElement emi_box;
		@FindBy(xpath="//*[@id='loaninterest']")
			WebElement interest_box;
		
		@FindBy(xpath="//*[@id='loanterm']")
			WebElement loan_tenure_box;
		
		@FindBy(xpath="//*[@id='loanfees']")
			WebElement loan_fees_box;
		
		@FindBy(xpath="//*[@id='loanmonths']")
			WebElement month_btn_click;
		
		@FindBy(xpath="//*[@id='loanemislider']")
			WebElement emi_slider;
		
		@FindBy(xpath="//*[@id='loaninterestslider']")
			WebElement interest_slider;
		
		@FindBy(xpath="//*[@id='loantermslider']")
			WebElement loan_tenure_slider;
		
		@FindBy(xpath="//*[@id='loanfeesslider']")
			WebElement loan_fees_slider;
		
		//---------------------------- TC004 xpath------------------
		@FindBy(xpath="//li[@id='loan-tenure-calc']")
			WebElement loan_tenure_calculator;
		
		@FindBy(xpath="//input[@name='loanamount']")
			WebElement loan_amount;
		
		@FindBy(xpath="//div[@id='loanamountslider']")
			WebElement loan_amount_slider;
	
	
	// ---------------------------- TC 001 --------------------------->>>>>>>>>>>>>>>>>>>>>>>>>>>
	public void clickCarLoan() {
		mywait.until(ExpectedConditions.visibilityOf(btn_carloan)).click();
	}
	public void setLoanAmount(String amount) throws InterruptedException {
		WebElement loan_amount = mywait.until(ExpectedConditions.visibilityOf(car_loan_amount));
		js.executeScript("arguments[0].value = '';", loan_amount);
		Thread.sleep(1000);
		js.executeScript("arguments[0].value = arguments[1];", loan_amount, amount);
	}
	public void setInterest(String interest) throws InterruptedException {

		WebElement ir = mywait.until(ExpectedConditions.visibilityOf(interest_rate));
		js.executeScript("arguments[0].value = '';", ir);
		Thread.sleep(1000);
		js.executeScript("arguments[0].value = arguments[1];", ir, interest);

	}
	public void setLoanTenure(String tenure) throws InterruptedException {

		WebElement loanterm = mywait.until(ExpectedConditions.visibilityOf(loan_tenure));
		js.executeScript("arguments[0].value = '';", loanterm);
		js.executeScript("arguments[0].value = arguments[1];", loanterm, tenure);
		Thread.sleep(1500);
		
		// clicking on emi In Advance button----------->>>>>>>>>>>>>
		WebElement emi = mywait.until(ExpectedConditions.visibilityOf(btn_advEmi));
		js.executeScript("arguments[0].click();", emi);
		Actions act= new Actions(driver);
		act.keyDown(Keys.ENTER).keyUp(Keys.ENTER);
	}
	public boolean fetch_emiData(String amount ,String year)
	{
		try {
			int loan_amount = Integer.parseInt(amount);
			int no_year = Integer.parseInt(year);
			int p_amount = (loan_amount / (no_year*12));
			
			String totalEmiAmount = mywait.until(ExpectedConditions.visibilityOf(txt_emiAmount)).getText();
			String[] str_arr = totalEmiAmount.split("[,\\s]+");
			totalEmiAmount="";
			for(int i=0;i<str_arr.length;i++)
			{
				totalEmiAmount += str_arr[i];
			}
			int emi_amount = Integer.parseInt(totalEmiAmount);
			
//			System.out.println("one month principal is : " + p_amount);
//			System.out.println("one month interest is : " + (emi_amount-p_amount));
			
			// writing the output in excel file...................
			ExcelUtility eu = new ExcelUtility();
			
			eu.setCellData("sheet1",1,0,String.valueOf(emi_amount));
			eu.setCellData("sheet1",1,1,String.valueOf(p_amount));
			eu.setCellData("sheet1",1,2,String.valueOf(emi_amount-p_amount));
			
			return true;
			
		}
		catch(Exception e) {
			return false;
		}
	}
	
	//------------------------------------- Test Case 2 ---------------------------------------//
	public void click_calculators()
	{
		mywait.until(ExpectedConditions.visibilityOf(btn_calculators)).click();
	}
	
	public void click_HomeLoanEmiCalculator() throws InterruptedException
	{
		Thread.sleep(1000);
		mywait.until(ExpectedConditions.visibilityOf(btn_homeLoanEmiCalculator)).click();
	}
	public void sendData_homeValue(String value)
	{
		WebElement homeValue = mywait.until(ExpectedConditions.visibilityOf(box_homeValue));
		homeValue.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		homeValue.sendKeys(Keys.DELETE);
		//String a=e.getCellData("Sheet3", 1, 0); /// need to fetch from excel
		homeValue.sendKeys(value);
	}
	public void sendData_homeDownPayment(String value)
	{
		WebElement homeDownPayment = mywait.until(ExpectedConditions.visibilityOf(box_homeDownPayment));
		homeDownPayment.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		homeDownPayment.sendKeys(Keys.DELETE);
		//String a=e.getCellData("Sheet3", 1, 1);
		homeDownPayment.sendKeys(value);
	}
	public void sendData_homeLoanInterest(String value)
	{
		WebElement homeLoaninterest = mywait.until(ExpectedConditions.visibilityOf(box_homeLoaninterest));
		homeLoaninterest.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		homeLoaninterest.sendKeys(Keys.DELETE);
		//String a=e.getCellData("Sheet3", 1, 3);
		homeLoaninterest.sendKeys(value);
	}
	public void sendData_homeLoanTenure(String value)
	{
		WebElement homeLoanTenure = mywait.until(ExpectedConditions.visibilityOf(box_homeLoanTenure));
		homeLoanTenure.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		homeLoanTenure.sendKeys(Keys.DELETE);
		//String a=e.getCellData("Sheet3", 1, 4);
		homeLoanTenure.sendKeys(value);
	}
	public void sendData_LoanInsurance_AND_LoanFees(String value1,String value2)
	{
		WebElement loanInsurance = mywait.until(ExpectedConditions.visibilityOf(box_loanInsurance));
		loanInsurance.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		loanInsurance.sendKeys(Keys.DELETE);
		//String a=e.getCellData("Sheet3", 1, 1);
		loanInsurance.sendKeys(value1);
		
		WebElement Loan_Fees = mywait.until(ExpectedConditions.visibilityOf(box_Loan_Fees));
		Loan_Fees.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		Loan_Fees.sendKeys(Keys.DELETE);
		//String a=e.getCellData("Sheet3", 1, 5);
		Loan_Fees.sendKeys(value2);
	}
	public void sendData_HomeOwnerExpenses(String value1,String value2,String value3,String value4)
	{
		// Home Owner Expense Section data filling --------------------------->>>>>>>>>>>>>>>>>>>>>>>>
		
		WebElement oneTimeExpenses = mywait.until(ExpectedConditions.visibilityOf(box_oneTimeExpenses));
		oneTimeExpenses.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		oneTimeExpenses.sendKeys(Keys.DELETE);
		//String a=e.getCellData("Sheet3", 1, 5);
		oneTimeExpenses.sendKeys(value1);
		
		
		WebElement propertyTax = mywait.until(ExpectedConditions.visibilityOf(box_propertyTax));
		propertyTax.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		propertyTax.sendKeys(Keys.DELETE);
		//String a=e.getCellData("Sheet3", 1, 5);
		propertyTax.sendKeys(value2);
		
		
		WebElement HomeInsurance = mywait.until(ExpectedConditions.visibilityOf(box_HomeInsurance));
		HomeInsurance.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		HomeInsurance.sendKeys(Keys.DELETE);
		//String a=e.getCellData("Sheet3", 1, 5);
		HomeInsurance.sendKeys(value3);
		
		
		WebElement maintainance_expenses = mywait.until(ExpectedConditions.visibilityOf(box_maintainance_expenses));
		maintainance_expenses.sendKeys(Keys.chord(Keys.CONTROL,"a"));
		maintainance_expenses.sendKeys(Keys.DELETE);
		//String a=e.getCellData("Sheet3", 1, 5);
		maintainance_expenses.sendKeys(value4);
		
	}
	
	public void sendData_Date(String month, String year)
	{
		String dt = month + year;
		dt = dt.trim();
		WebElement date = mywait.until(ExpectedConditions.visibilityOf(date_startDate));
		date.click();
		
		String mon = month;
		String yr = year;
		int input_year = Integer.parseInt(yr);
		
		List<WebElement> list = mywait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"datepicker-months\"]//table[@class=\"table-condensed\"]//thead//tr[2]//th")));
		WebElement prev_btn = list.get(0);
		WebElement next_btn = list.get(2);
		WebElement hd_year = list.get(1);
		int get_year;
		
		while(true)
		{
			get_year = Integer.parseInt(hd_year.getText());
			if(get_year < input_year)
			{
					next_btn.click();	
			}
			else if(get_year > input_year)
			{
				// click on previous button
				prev_btn.click();
			}
			else if(get_year == input_year)
			{
				break;
			}	
		}
		List<WebElement> list_month = mywait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class=\"datepicker-months\"]//table[@class=\"table-condensed\"]//tbody//span")));
		for(WebElement w : list_month)
		{
			if(w.getText().equalsIgnoreCase(mon))
			{
				w.click();
				break;
			}
		}	
		
	}
	
	public void fetch_table() throws IOException, InterruptedException
	{
		js.executeScript("window.scrollBy(0,1600)");
		Thread.sleep(3000);
		List<WebElement> table_rows = mywait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='paymentschedule']/table/tbody/tr[contains(@class,'row no-margin') and not(@id)]")));
		int row_size = table_rows.size();
		
		List<WebElement>table_cols = mywait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='paymentschedule']/table/tbody/tr[contains(@class,'row no-margin') and not(@id)][2]//td")));
		int cols_size = table_cols.size();
		
		WebElement data;
		
		ExcelUtility eu = new ExcelUtility();
		
		
		if(eu.check_ExcelSheet("sheet2")==true)
		{
			eu.deleteSheet("sheet2");
		}

		for(int j=2;j<=row_size;j++)
		{
			for(int k=1;k<=cols_size;k++)
			{
				data = mywait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='paymentschedule']/table/tbody/tr[contains(@class,'row no-margin') and not(@id)]["+j+"]//td["+k+"]")));
				System.out.print(data.getText()+"  ");
				eu.setCellData( "sheet2" , j-1 , k-1 , data.getText() );
			}
			System.out.println();
			System.out.println();
		}
	}
	
	//------------TC003----------------------- TC004-----------------(Reusable Method)------->>>>>//
	public void loan_Calculator() throws InterruptedException {
		Thread.sleep(1000);
		WebElement calculator_click_btn=mywait.until(ExpectedConditions.visibilityOf(calculator_click));
		js.executeScript("arguments[0].click();",calculator_click_btn);

	}
	public void loanAmountCalculatorClick() {
		WebElement click=mywait.until(ExpectedConditions.visibilityOf(loan_amount_calculator_click));
		js.executeScript("arguments[0].click();",click);
	}
	public boolean amountBox() {
		WebElement box=mywait.until(ExpectedConditions.visibilityOf(emi_box));	
		boolean check=box.isEnabled();
		return check;	
	}
	public boolean interestBox() {
		WebElement box=mywait.until(ExpectedConditions.visibilityOf(interest_box));
		boolean check=box.isEnabled();
		return check;
	}
	public boolean loanTenureBox() {
		WebElement box=mywait.until(ExpectedConditions.visibilityOf(loan_tenure_box));
		boolean check=box.isEnabled();
		return check;
	}
	public boolean loanFeesBox() {
		WebElement box=mywait.until(ExpectedConditions.visibilityOf(loan_fees_box));
		boolean check=box.isEnabled();
		return check;
	}
	public int Tenure() {
		String Text=loan_tenure_box.getAttribute("value");
		int year=Integer.parseInt(Text);
		return year;
	}
	public void Months() {
		WebElement month=mywait.until(ExpectedConditions.visibilityOf(month_btn_click));
		js.executeScript("arguments[0].click();",month);

	}
	public boolean emiCalculatorLoanAmountSliderCheck() {
		String amount1=emi_box.getAttribute("value");
		//System.out.println(amount1); // before sliding amount
		Actions act= new Actions(driver);
		act.dragAndDropBy(emi_slider,20, 0).build().perform();
		String amount2 = emi_box.getAttribute("value");
		//System.out.println(amount2); // after sliding amount
		boolean amount = amount1.equals(amount2);  // if both are equal then slider is not working
		return amount;
	}
	public boolean interestCalculatorLoanAmountSliderCheck() {
		String amount1=interest_box.getAttribute("value");
		Actions act= new Actions(driver);
		act.dragAndDropBy(interest_slider, 100, 0).build().perform();
		String amount2 = interest_box.getAttribute("value");
		boolean amount = amount1.equals(amount2);
		return amount;

	}
   public boolean loanTenureCalculatorLoanAmountSliderCheck() {
	   String amount1=loan_tenure_box.getAttribute("value");
		Actions act= new Actions(driver);
		act.dragAndDropBy(loan_tenure_slider, 20, 0).build().perform();
		String amount2 = loan_tenure_box.getAttribute("value");
		boolean amount = amount1.equals(amount2);
		return amount;
   }
   public boolean loanFeesCalculatorLoanAmountSliderCheck() {
	   js.executeScript("window.scrollBy(0,100)");
	   String amount1=loan_fees_box.getAttribute("value");
		Actions act= new Actions(driver);
		act.dragAndDropBy(loan_fees_slider, 100, 0).build().perform();
		String amount2 = loan_fees_box.getAttribute("value");
		boolean amount = amount1.equals(amount2);
		return amount;
	   
   }
	

	//----------------- TC 004 ---------------------------------------///////////////////////

	public void loanTenureCalculatorClick() {
		WebElement click_ltc=mywait.until(ExpectedConditions.visibilityOf(loan_tenure_calculator));
		js.executeScript("arguments[0].click();",click_ltc);
	}

	public boolean loan_amount() {
		WebElement box=mywait.until(ExpectedConditions.visibilityOf(loan_amount));
		boolean check = box.isEnabled();
		return check;
	}
	
	public boolean loanAmount_slider_check() {
		String amount_old=loan_amount.getAttribute("value");
		//System.out.println(amount_old);
		Actions act=new Actions(driver);
		act.dragAndDropBy(loan_amount_slider,-200, 0).build().perform();
		
		String amount_new=loan_amount.getAttribute("value");
		//System.out.println(amount_new);
		boolean amount=amount_old.equals(amount_new);
		return amount;
		
	}
	
}