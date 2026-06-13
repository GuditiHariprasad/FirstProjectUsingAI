package com.automation.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import java.util.List;

public class EmployeePage extends BasePage {

    // Locators for Employee Management Page
    private By addEmployeeButton = By.xpath("//button[contains(text(), 'Add')]");
    private By employeeNameField = By.xpath("//input[@placeholder='First Name']");
    private By employeeLastNameField = By.xpath("//input[@placeholder='Last Name']");
    private By employeeIdField = By.xpath("//input[@name='employeeId']");
    private By saveButton = By.xpath("//button[@type='submit'][contains(text(), 'Save')]");
    private By successMessage = By.xpath("//div[contains(@class, 'success')]");
    private By employeeTable = By.xpath("//table[@class='oxd-table']");
    private By searchField = By.xpath("//input[@placeholder='Search']");
    private By searchButton = By.xpath("//button[contains(text(), 'Search')]");

    public void clickAddEmployeeButton() {
        logger.info("Clicking Add Employee button");
        WebElement addBtn = wait.waitForElementToBeClickable(addEmployeeButton);
        addBtn.click();
    }

    public void enterEmployeeFirstName(String firstName) {
        logger.info("Entering employee first name: " + firstName);
        WebElement firstNameField = wait.waitForElement(employeeNameField);
        firstNameField.clear();
        firstNameField.sendKeys(firstName);
    }

    public void enterEmployeeLastName(String lastName) {
        logger.info("Entering employee last name: " + lastName);
        WebElement lastNameField = wait.waitForElement(employeeLastNameField);
        lastNameField.clear();
        lastNameField.sendKeys(lastName);
    }

    public void enterEmployeeId(String employeeId) {
        logger.info("Entering employee ID: " + employeeId);
        WebElement idField = wait.waitForElement(employeeIdField);
        idField.clear();
        idField.sendKeys(employeeId);
    }

    public void clickSaveButton() {
        logger.info("Clicking Save button");
        WebElement save = wait.waitForElementToBeClickable(saveButton);
        save.click();
    }

    public boolean isSuccessMessageDisplayed() {
        logger.info("Checking if success message is displayed");
        try {
            return wait.waitForElementToBeVisible(successMessage) != null;
        } catch (Exception e) {
            logger.warn("Success message not found");
            return false;
        }
    }

    public void searchEmployee(String employeeName) {
        logger.info("Searching for employee: " + employeeName);
        WebElement search = wait.waitForElement(searchField);
        search.clear();
        search.sendKeys(employeeName);
        clickSearchButton();
    }

    public void clickSearchButton() {
        logger.info("Clicking Search button");
        WebElement search = wait.waitForElementToBeClickable(searchButton);
        search.click();
    }

    public boolean isEmployeeInTable(String employeeName) {
        logger.info("Checking if employee is in table: " + employeeName);
        try {
            WebElement table = wait.waitForElement(employeeTable);
            List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
            for (WebElement row : rows) {
                if (row.getText().contains(employeeName)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            logger.warn("Employee not found in table");
            return false;
        }
    }

    public int getEmployeeTableRowCount() {
        logger.info("Getting employee table row count");
        WebElement table = wait.waitForElement(employeeTable);
        List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));
        return rows.size();
    }
}

