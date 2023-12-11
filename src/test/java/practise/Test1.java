package practise;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import repository.HomePage;
import utilities.ExcelLib;
import utilities.TestSetup;

@Listeners({ utilities.Listeners.class })
public class Test1 extends TestSetup {
	ExcelLib excel = new ExcelLib();
	
	
	@Test(dataProvider = "TestData")
	public void runTest(String product) throws IOException, InterruptedException {
		HomePage home = new HomePage(TestSetup.getDriver());
		test=extent.createTest("First Test", "Abhishek");
		test.log(Status.INFO, "User is on homepage");
		home.enterText(product);
		test.log(Status.INFO, "Entered product in search box as "+ product);
		Thread.sleep(2000);
		home.clickAddToCart();
		test.log(Status.INFO, "Clicked on Add to Cart");
		Thread.sleep(2000);
		home.clearText();
		test.log(Status.INFO, "Cleared the search box");
		Thread.sleep(3000);
	}
	
	@DataProvider(name="TestData")
	public Object[][] dataprovider() throws IOException {
		Object[][] arrayObject = ExcelLib
				.getCellDatas("C:\\bdd_api\\selenium-java\\data\\DataSheet.xlsx", "Sheet1");
		return arrayObject;
	}
}
