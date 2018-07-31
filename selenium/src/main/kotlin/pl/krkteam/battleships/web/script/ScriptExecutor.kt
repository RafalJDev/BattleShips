package pl.krkteam.battleships.web.script;

import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebDriver

object ScriptExecutor {

    fun execute(webDriver: WebDriver, script: String) {
        (webDriver as JavascriptExecutor).executeScript(script)
    }
}
