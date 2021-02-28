import Methods.methodsClass;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;
import java.util.Random;
import static Variable.variableClass.*;


public class TestClass extends methodsClass {

        @Test
        public void guestPageUrlControl() throws InterruptedException
        {
            Assert.assertTrue(gittiGidiyorURL.contains("https://www.gittigidiyor.com/"));
            System.out.println("AnaSayfa URL Kontrolü Yapılır."+gittiGidiyorURL);
            clickElement(By.cssSelector(divLogin));
            Thread.sleep(3000);
            clickElement(By.cssSelector(divLogin2));
            login();
        }

        public void login() throws InterruptedException
        {
            sendKeys(By.cssSelector(userName),"USERNAME");
            sendKeys(By.name(passwordInput),"PASSWORD");
            clickElement(By.cssSelector(inputLogin));
            Assert.assertNotNull(girisKontrol);
            System.out.println("Giriş Kontrolü URL ile yapılır"+girisKontrol);
            searchProduct();
        }

        public void searchProduct() throws InterruptedException
        {
            sendKeys(By.cssSelector(searchInput),"Bilgisayar");
            clickElement(By.xpath(buttonSearch));
            Thread.sleep(3000);
            goSecondPage();
        }


        public void goSecondPage() throws InterruptedException
        {
            Thread.sleep(4000);
            clickElement(By.cssSelector(secondPageCookies));
            Thread.sleep(3000);
            clickElement(By.cssSelector(buttonIkinciSayfa));
            Assert.assertTrue(urlIkinciSayfa.contains("https://www.gittigidiyor.com/arama/?k=Bilgisayar&sf=2"));
            System.out.println("İkinci Sayfaya Gidildiği Kontrol Edilir."+urlIkinciSayfa);
            randomProductChoose();
        }


        public void randomProductChoose() throws InterruptedException
        {
            List<WebElement> prodducts = driver.findElements(By.className("srp-item-list"));
            System.out.println("boyutu : "+prodducts.size());
            Random random = new Random();
            int rnd = random.nextInt(prodducts.size());
            clickElement(By.xpath("/html/body/div[5]/div[2]/div/div[2]/div[3]/div[2]/ul/li["+rnd+"]"));
            getPrpductPrice();
            /*
            // random seçimde indirimili ürün çıktğığı zaman fiyat kontrollerinde problem oluşyor. O yüzden bu sabit inspecti de kullanabiliriz
            Thread.sleep(3000);
            clickElement(By.xpath("//html/body/div[5]/div[2]/div/div[2]/div[3]/div[2]/ul/li[2]"));
             */
        }

        public String productPriceText;
        public void getPrpductPrice() throws InterruptedException
        {
            productPriceText=  elementTextPrice(By.id("sp-price-highPrice"));
            System.out.println(productPriceText);

            addBasket();
        }

        public void addBasket() throws InterruptedException
        {
            clickElement(By.cssSelector(buttonBasket));
            Thread.sleep(3000);
            goToBasket();

        }

        public void goToBasket() throws InterruptedException
        {
            clickElement(By.cssSelector("a[class='gg-ui-btn-default padding-none']"));
           basketToProductDetailPriceControl();
        }
        public String basketPriceText;
        public void basketToProductDetailPriceControl() throws InterruptedException{
            basketPriceText=  elementTextPrice(By.cssSelector("div[class='total-price']>strong"));
            System.out.println(basketPriceText);
            //Assert.assertSame(basketPriceText,productPriceText);
            increaseBasketProduct();
        }


        public void increaseBasketProduct() throws InterruptedException
        {
            Thread.sleep(3000);
            WebElement dropdown = driver.findElement(By.xpath(dropdownSepetAdet));
            Select select = new Select(dropdown);
            select.selectByValue("2");
            WebElement adet = select.getFirstSelectedOption();
            System.out.println("Seçilen Adet Sayısı:"+ adet.getText());
            Thread.sleep(3000);
            removeProduct();
        }

        public void removeProduct() throws InterruptedException
        {
            clickElement(By.xpath(deleteProduct));
            Assert.assertTrue(textSepetteUrunYok.contains("Sepetinizde ürün bulunmamaktadır."));
            System.out.println("Ürün Bulunmamaktadır Yazısı Kontrol Edilir. "+textSepetteUrunYok);

        }
}
