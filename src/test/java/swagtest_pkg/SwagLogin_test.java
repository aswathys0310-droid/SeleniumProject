package swagtest_pkg;

import org.testng.annotations.Test;

import swagbase_pkg.Swagbase_class;


public class SwagLogin_test {
	
	public class Swag_LoginTest extends Swagbase_class{
		
		@Test
		public void login_validation() throws Exception
		{
			test = extent.createTest("Login Verification");
			obj.login_method("standard_user","secret_sauce" );
			Thread.sleep(2000);
		}

	}

}
