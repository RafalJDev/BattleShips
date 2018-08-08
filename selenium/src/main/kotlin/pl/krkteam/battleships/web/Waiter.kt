package pl.krkteam.battleships.web

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver
import org.openqa.selenium.support.ui.Wait
import org.openqa.selenium.support.ui.WebDriverWait

object Waiter {

    fun waitTillPageLoads(webDriver: WebDriver) {
        val wait: Wait<WebDriver> = WebDriverWait(webDriver, 10000)

        wait.until {
            (webDriver as JavascriptExecutor)
                    .executeScript("return document.readyState") == "complete"
        }
    }
}