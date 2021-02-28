package DriverStandingUp;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class driverStandingUp{
    public WebDriver driver;
    @Before
    public void beforeSenaryo()
    {
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------Senaryo Başladı--------------");
        System.setProperty("webdriver.chrome.driver","drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.gittigidiyor.com/");
        driver.manage().window().maximize();
    }


    @After
    public void driverQuit() throws InterruptedException {
        Thread.sleep(1000);
        System.out.println("-------------------------------------------------");
        System.out.println("-------------------Senaryo Bitti--------------");
        driver.quit();
    }

}
