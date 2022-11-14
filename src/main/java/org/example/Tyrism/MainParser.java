package org.example.Tyrism;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class MainParser implements Parser<ArrayList<String>> {

    @Override
    public ArrayList<String> Parse(WebDriver driver) {

        ArrayList<String> list = new ArrayList<>();

        List<WebElement> reviewers = driver.findElements(By.cssSelector("div[class='find-list-box']"));
        int len = reviewers.size();
        for (int i = 0; i < len; i++) {
            reviewers = driver.findElements(By.cssSelector("div[class='find-list-box']"));
            WebElement reviewer = reviewers.get(i);
            WebElement button = reviewer.findElement(By.cssSelector("a[class='ss']"));
            button.click();

            StringBuilder tourCompany = new StringBuilder();
            WebElement header = driver.findElement(By.cssSelector("header[class='main-head']"));
            WebElement h1 = header.findElement(By.cssSelector("h1[class='ib']"));
            tourCompany.append(h1.getText());
            tourCompany.append("\n");

            WebElement all = driver.findElement(By.cssSelector("div[class='reviewers-block']"));
            List<WebElement> one = all.findElements(By.cssSelector("div[class='reviewers-box']"));
            if (one.size() == 0) tourCompany.append("Отзывов нет :(");
            else
                for (WebElement webElement : one) {

                    int point = 5;
                    tourCompany.append(webElement.findElement(By.cssSelector("span[itemprop='author']")).getText());
                    tourCompany.append(". Оценка: ");
                    WebElement head = webElement.findElement(By.cssSelector("header[class='head']"));
                    List<WebElement> li = head.findElements(By.cssSelector("li"));
                    for (WebElement element : li) {
                        WebElement span = element.findElement(By.cssSelector("span"));
                        if (span.getAttribute("class").equals("star e")) point--;
                    }
                    tourCompany.append(point);

                    tourCompany.append("\n");

                    WebElement tbody = webElement.findElement(By.cssSelector("tbody"));
                    List<WebElement> tr = tbody.findElements(By.cssSelector("tr"));
                    for (int j = 0; j < tr.size(); j++) {
                        if (j == 0) {
                            tourCompany.append("Плюсы: ");
                            tourCompany.append(webElement.findElement(By.cssSelector("td[itemprop='pro']")).getText());
                            tourCompany.append("\n");
                        } else if (j == 1) {
                            tourCompany.append("Минусы: ");
                            tourCompany.append(webElement.findElement(By.cssSelector("td[itemprop='contra']")).getText());
                            tourCompany.append("\n");
                        } else if (j == 2) {
                            tourCompany.append("Отзыв: ");
                            tourCompany.append(webElement.findElement(By.cssSelector("td[itemprop='reviewBody']")).getText());
                            tourCompany.append("\n");
                        }
                    }
                    tourCompany.append("\n");
                }
            tourCompany.append("\n");


            list.add(tourCompany.toString());
            driver.navigate().back();
        }


        return list;
    }
}
