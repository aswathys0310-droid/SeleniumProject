package swagpage_pkg;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SwagLogin_page {
		
	WebDriver driver;
		
	  @FindBy(xpath="//*[@id=\"user-name\"]") WebElement username;
	  @FindBy(xpath="//*[@id=\"password\"]") WebElement password;
	  @FindBy(xpath="//*[@id=\"login-button\"]") WebElement login_button;
		
		public SwagLogin_page(WebDriver driver) 
		{
			this.driver=driver;
			PageFactory.initElements(driver, this);
			
		}
		
		
		public void login_method(String user, String pass)
		{
			username.sendKeys(user);
			password.sendKeys(pass);
			login_button.click();
		}

	}


