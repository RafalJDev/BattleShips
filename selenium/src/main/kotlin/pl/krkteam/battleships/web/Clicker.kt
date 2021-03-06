package pl.krkteam.battleships.web

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

object Clicker {

    fun clickOn(webDriver: WebDriver, className: String, elementNumber: Int) {
        (webDriver as JavascriptExecutor).executeScript("document.getElementsByClassName" + "(\"" + className + "\")"
                + "[" + elementNumber + "]" + ".click();")

        Waiter.waitTillPageLoads(webDriver)

        sleep()
    }

    private fun sleep() {
        Thread.sleep(200)
    }
}