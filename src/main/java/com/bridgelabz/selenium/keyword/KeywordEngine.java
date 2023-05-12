package com.bridgelabz.selenium.keyword;

import com.bridgelabz.selenium.base.BaseClass;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class KeywordEngine extends BaseClass {

    public Workbook book;
    WebElement element;
    public Sheet sheet;
    public String scenarioSheet = "C:\\Users\\Admin\\IdeaProjects\\KeywordDrivenFramework\\src\\main\\resources\\Book1.xlsx";

    public void startExecution() {
        FileInputStream file;
        try {
            file = new FileInputStream(scenarioSheet);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            book = WorkbookFactory.create(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sheet = book.getSheet("Login");

        int k = 0;
        for (int i = 0; i < sheet.getLastRowNum(); i++) {

            String locatorType = sheet.getRow(i + 1).getCell(k + 1).toString().trim();
            String locatorValue = sheet.getRow(i + 1).getCell(k + 2).toString().trim();
            String action = sheet.getRow(i + 1).getCell(k + 3).toString().trim();
            String value = sheet.getRow(i + 1).getCell(k + 4).toString().trim();

            switch (action) {
                case "open browser":
                    init(value);
                    break;
                case "enter url":
                    driver.get(value);
                    break;
                case "quit":
                    closeBrowser();
                    break;
                default:
                    break;
            }

            switch (locatorType) {
                case "id":
                    element = driver.findElement(By.id(locatorValue));
                    if (action.equalsIgnoreCase("sendkeys")) {
                        element.sendKeys(value);
                    } else if (action.equalsIgnoreCase("click")) {
                        element.click();
                    } else if (action.equalsIgnoreCase("isDisplayed")) {
                        element.isDisplayed();
                    }
                    locatorType = null;
                    break;
                case "xpath":
                    element = driver.findElement(By.xpath(locatorValue));
                    if (action.equalsIgnoreCase("sendkeys")) {
                        element.sendKeys(value);
                    } else if (action.equalsIgnoreCase("click")) {
                        element.click();
                    } else if (action.equalsIgnoreCase("isDisplayed")) {
                        element.isDisplayed();
                    }
                    locatorType = null;
                    break;
                default:
                    break;
            }
        }
    }
}
