package drag_and_drop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;

public class Drag_and_Drop {

	public static void main(String[] args) throws InterruptedException, ExecuteException, IOException {
		//Start Server
		CommandLine command= new CommandLine("C:\\Program Files (x86)\\Appium\\node.exe");
		
		command.addArgument("C:\\Program Files (x86)\\Appium\\node_modules\\appium\\bin\\appium.js");
		
		command.addArgument("--address");
		command.addArgument("127.0.0.1");
		
		command.addArgument("--port");
		command.addArgument("4723");
		
		DefaultExecuteResultHandler ResultHandler=new DefaultExecuteResultHandler();
		DefaultExecutor executor=new DefaultExecutor();
			
		executor.execute(command, ResultHandler);
		
		Thread.sleep(15000);
		
		
		
		
		DesiredCapabilities capabilities = new DesiredCapabilities();
		
		//Device details
		capabilities.setCapability("deviceName", "emulator-5554");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		
		capabilities.setCapability("appPackage", "com.mobeta.android.demodslv");
		capabilities.setCapability("appActivity", "com.mobeta.android.demodslv.Launcher");
		
		//launch my app
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
	
		Thread.sleep(20000);
		
		driver.findElementById("com.mobeta.android.demodslv:id/activity_title").click();
		
		Thread.sleep(20000);
		List<WebElement> drag=driver.findElementsById("com.mobeta.android.demodslv:id/drag_handle");
		System.out.println(drag.size());
		
		int flag=0;
		String str = "Michael Brecker";
		
		TouchAction action = new TouchAction(driver);
		action.longPress(drag.get(6)).moveTo(drag.get(1)).release().perform();
		
		List<WebElement> text=driver.findElementsById("com.mobeta.android.demodslv:id/text");
		
		for(WebElement text1:text)
		{
			System.out.println(text1);
			if(text1.getText().equals(str))
			{
				flag=1;
				break;
			}
			else
				flag=0;
		}
		if(flag==1)
		{
			System.out.println("Pass");
		}
		else
			System.out.println("False");
		Thread.sleep(20000);
		
		action.longPress(drag.get(7)).moveTo(drag.get(3)).release().perform();
		
		
		CommandLine command2= new CommandLine("cmd");
		
		command2.addArgument("/c");
		command2.addArgument("taskkill");
		command2.addArgument("/F");
		command2.addArgument("/IM");
		command2.addArgument("node.exe");
		
		DefaultExecuteResultHandler ResultHandler2=new DefaultExecuteResultHandler();
		DefaultExecutor executor2=new DefaultExecutor();
		
		
		executor2.execute(command2, ResultHandler2);
		
	}

}
