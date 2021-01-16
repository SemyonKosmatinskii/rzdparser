import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Application {

    public static void main(String[] args) throws InterruptedException {

        Set<String> input = new HashSet<>();
        input.add("https://ticket.rzd.ru/searchresults/v/1/5a323c29340c7441a0a556bb/5a13bc15340c745ca1e844e3/2020-12-1");
        input.add("https://ticket.rzd.ru/searchresults/v/1/5a323c29340c7441a0a556bb/5a13bc15340c745ca1e844e3/2020-12-2");
        input.add("https://ticket.rzd.ru/searchresults/v/1/5a323c29340c7441a0a556bb/5a13bc15340c745ca1e844e3/2020-12-3");
        input.add("https://ticket.rzd.ru/searchresults/v/1/5a323c29340c7441a0a556bb/5a13bc15340c745ca1e844e3/2020-12-4");

        Map<String, Integer> minMaxMap = new HashMap<>();
        minMaxMap.put("min", 1000000);
        minMaxMap.put("max", 0);

        System.setProperty("webdriver.chrome.driver", "D:\\rzd\\chromedriver.exe");

        //C:\\bin\\chromedriver.exe  тут он установлен. Я ниче не менял

        WebDriver driver = new ChromeDriver();

        for(String uri: input) {

            driver.get(uri);
            Thread.sleep(5000);
            String content = driver.getPageSource();

            try {
                Document doc = Jsoup.parse(content);
                Elements elements = doc.select("rzd-search-results-card-railway");
                if (elements.size() == 0) {
                    System.out.println("Нет результата");
                    break;
                }
                for (Element elem : elements) {
                    Elements prices = elem.select("span.item__sum");
                    Elements names = elem.select("div.item__name");
                    for (Element name : names) {
                        String nameStr = name.text();
                        if (nameStr.equals("Плацкартный")) {
                            int i = names.indexOf(name);
                            Integer placPrice = Integer.parseInt(prices.get(i).text().replace(" ", ""));
                            if (minMaxMap.get("min") > placPrice) {
                                minMaxMap.put("min", placPrice);
                            }
                            if (minMaxMap.get("max") < placPrice) {
                                minMaxMap.put("max", placPrice);
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        driver.quit();
        System.out.println(minMaxMap);
        /*try (FileWriter writer = new FileWriter("content.txt", false)) {
            writer.write(content);
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    }
}
