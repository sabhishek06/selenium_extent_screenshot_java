package utilities;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class Screenshot extends TestSetup{

	
	public String screenShot() throws IOException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String timeStamp = dateFormat.format(new Date());
		String screenShotName="screenshot_"+timeStamp+".png";
		String screenshotPath="./screenshot/"+screenShotName;
		TakesScreenshot screenShot = (TakesScreenshot)TestSetup.getDriver();
		File source = screenShot.getScreenshotAs(OutputType.FILE);
		File destination = new File(screenshotPath);
		FileUtils.copyFile(source, destination);
		return screenshotPath;
	}
}
