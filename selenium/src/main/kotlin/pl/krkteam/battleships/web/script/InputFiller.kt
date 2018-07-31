package pl.krkteam.battleships.web.script

import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import pl.krkteam.battleships.web.WebElementGetter

object InputFiller {

    fun fillInputElement(webDriver: WebDriver, inputClassName: String, valueToFill: String) {
        val element: WebElement = WebElementGetter.byClass(webDriver, inputClassName)
        element.sendKeys(valueToFill)
    }

    fun clearInputElement(webDriver: WebDriver, inputClassName: String) {
        val scriptString = "document.getElementsByClassName" +
                "('$inputClassName')[0].value='';"

        ScriptExecutor.execute(webDriver, scriptString)
    }
}