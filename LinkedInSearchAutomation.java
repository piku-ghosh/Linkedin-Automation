package myProject;



import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration; 
import java.util.List;

public class LinkedInSearchAutomation {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.edge.driver", "C:\\Project\\Driver_Notes\\msedgedriver.exe");
        WebDriver driver = new EdgeDriver();
        driver.get("https://www.linkedin.com/checkpoint/rm/sign-in-another-account?fromSignIn=true&trk=guest_homepage-basic_nav-header-signin");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(By.id("username")));
        usernameInput.sendKeys("pikughosh29@gmail.com");

        WebElement passwordInput = driver.findElement(By.id("password"));
        passwordInput.sendKeys("Piku@9800");

        WebElement loginButton = driver.findElement(By.xpath("//*[@id=\"organic-div\"]/form/div[4]/button"));
        loginButton.click();

        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"global-nav-typeahead\"]/input")));
        searchInput.sendKeys("hospitals");
        searchInput.sendKeys(Keys.ENTER);

        clickPeopleSection(driver);
        extractNames(driver); // Add this to call the method
    }

    private static void clickPeopleSection(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement peopleSection = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='People']")));
        peopleSection.click();
    }

    private static void extractNames(WebDriver driver) throws InterruptedException {
        int currentPage = 1;
        int totalPages = 100;

        while (currentPage <= totalPages) {
            // ✅ Stable XPath for names
        	List<WebElement> names = driver.findElements(By.xpath("//*[@id=\"Y5xivIsnQDaFzH1kNcO6jw==\"]/div/ul/li[1]/div/div/div/div[2]/div[1]/div[1]/div/span[1]/span/a/span/span[1]"));

            for (WebElement name : names) {
                System.out.println(name.getText());
            }

            // ✅ Stable XPath for Next button
            try {
                WebElement nextPageButton = driver.findElement(By.xpath("//*[@id=\"ember500\"]/span"));
                nextPageButton.click();
                Thread.sleep(5000); // Wait for the next page to load
            } catch (Exception e) {
                System.out.println("Reached the last page or an error occurred.");
                break;
            }

            currentPage++;
        }

    }
}
