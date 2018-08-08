package pl.krkteam.battleships.page.rooms

import org.openqa.selenium.WebDriver
import pl.krkteam.battleships.web.Clicker
import pl.krkteam.battleships.web.script.InputFiller

object RoomCreator {

    fun createRoom(webDriver: WebDriver) {
        val inputClassName = "mat-input-element"
        InputFiller.clearInputElement(webDriver, inputClassName)

        InputFiller.fillInputElement(webDriver, inputClassName, "selenium room")

        Thread.sleep(100)
        Clicker.clickOn(webDriver,"mat-button-toggle-label-content",0)
    }


}