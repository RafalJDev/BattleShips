package pl.krkteam.battleships.web

import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

object SourcePageExtractor {

    fun getPageSource(webDriver: WebDriver, clasName: String): String {
        val element: WebElement = webDriver.findElement(By.className(clasName))
        return (webDriver as JavascriptExecutor)
                .executeScript("return arguments[0].innerHTML;", element)
                .toString()
    }
}