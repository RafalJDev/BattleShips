package pl.krkteam.battleships.web

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

object WebElementGetter {

    fun byClass(webDriver: WebDriver, className:String): WebElement {
        return webDriver.findElement(By.className(className))
    }
}