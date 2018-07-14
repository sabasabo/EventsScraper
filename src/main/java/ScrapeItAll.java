import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class ScrapeItAll {

    private static final String THINK_AND_DRINK_URL = "https://www.thinkdrink.co.il/#%D7%94%D7%A8%D7%A6%D7%90%D7%95%D7%AA-%D7%91%D7%97%D7%99%D7%A4%D7%94";
    private static final String THINK_ֹֹANDֹ_DRINK_TITLE = "דף ראשי";
    private static final String THINK_AND_DRINK_EVENT_CLASS = "cat1";

    public static void main(String[] args) {
        scrape();

    }

    public static String scrape() {
        //        System.setProperty("webdriver.gecko.driver","path of geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
//        driver.navigate().to("http://www.google.com");
        driver.get(THINK_AND_DRINK_URL);
        if (!driver.getTitle().contains(THINK_ֹֹANDֹ_DRINK_TITLE)) {
            System.exit(0);
        }
        List<WebElement> eventElements = ((FirefoxDriver) driver).findElementsByClassName(THINK_AND_DRINK_EVENT_CLASS);
        StringBuilder result = new StringBuilder();
        for (WebElement eventElement : eventElements) {
            String link = getLink(eventElement);
            String image = getImage(eventElement);
            String date = eventElement.findElement(By.tagName("h3")).getText();
            String time = eventElement.findElement(By.tagName("h4")).getText();
            String shortDescription = eventElement.findElement(By.tagName("h2")).getText();
            result.append("event:").append('\n');
            result.append("shortDescription:").append('\n');
            result.append(shortDescription).append('\n');
            result.append("date:").append('\n');
            result.append(date).append('\n');
            result.append("time:").append('\n');
            result.append(time).append('\n');
            result.append("link:").append('\n');
            result.append(link).append('\n');
            result.append("image:").append('\n');
            result.append(image).append('\n');
            result.append("\n\n").append('\n');
        }
        result.append(eventElements.size()).append('\n');


        driver.close();
        return result.toString();
    }

    private static String getImage(WebElement eventElement) {
        return eventElement.findElement(By.tagName("img")).getAttribute("src");
    }

    private static String getLink(WebElement eventElement) {
        return eventElement.findElement(By.tagName("a")).getAttribute("href");
    }
}
