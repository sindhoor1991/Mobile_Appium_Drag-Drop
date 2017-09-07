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

public class DragDropRemove {

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
		
		//Device Details
		capabilities.setCapability("deviceName", "emulator-5554");
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "4.4.2");
		
		//App details
		capabilities.setCapability("appPackage", "com.mobeta.android.demodslv");
		capabilities.setCapability("appActivity", "com.mobeta.android.demodslv.Launcher");
		
		//Launch the app
		
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
		Thread.sleep(5000);
		//Clicking cursor adaptor
		driver.findElementByXPath("//*[@text='CursorAdapter'][@class='android.widget.TextView']").click();
		Thread.sleep(5000);
		
		List<WebElement> ele_count = driver.findElementsById("com.mobeta.android.demodslv:id/drag_handle");
		System.out.println(ele_count);
		Thread.sleep(3000);
		TouchAction action = new TouchAction(driver);
		//drag and drop
		action.longPress(ele_count.get(6)).moveTo(ele_count.get(1)).release().perform();
		Thread.sleep(5000);
		
		List<WebElement> text = driver.findElementsById("com.mobeta.android.demodslv:id/text");
		int size1 = text.size();
		System.out.println(size1);
		
		List<WebElement> remove_ele = driver.findElementsById("com.mobeta.android.demodslv:id/click_remove");
		int size2 = remove_ele.size();
		System.out.println(size2);
		
		String s = "Michael Brecker";
		for(int i=0;i<size1;i++)
		{
			if((text.get(i).getText()).equals(s))
			{
				remove_ele.get(i).click();
				break;
			}
		}
		Thread.sleep(2000);
		List<WebElement> text2 = driver.findElementsById("com.mobeta.android.demodslv:id/text");
		int size3 = text2.size();
		System.out.println(size3);
		
		int flag=0;//available
		for(WebElement ele2:text2)
		{
			if(!(ele2.getText()).equals(s))
			{
				flag=1;//not available
			}
			else
				flag=0;
				break;
		}
		if(flag==1)
		{
			System.out.println("Pass");
		}
		else if(flag==0)
			System.out.println("Fail");
		
		
		
		
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
