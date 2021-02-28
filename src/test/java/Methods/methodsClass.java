package Methods;
import DriverStandingUp.driverStandingUp;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class methodsClass extends driverStandingUp{


    private WebElement findElement(By element)
    {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 5);
        WebElement webElement = webDriverWait.
                until(ExpectedConditions.presenceOfElementLocated(element));
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'})",
                webElement);
        return webElement;

    }

    public void clickElement(By element)
    {
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void sendKeys(By element,String sendkey)
    {

        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.elementToBeClickable(element)).sendKeys(sendkey);
    }



    public String elementTextPrice(By element)
    {
        String elementPriceText = findElement(element).getText();
        return elementPriceText;
    }



}
