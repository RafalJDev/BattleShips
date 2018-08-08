package pl.krkteam.battleships.page.game

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import java.util.*

class Shooter {

    fun play(webDriver: WebDriver) {

        Thread.sleep(500)
        firstRound(webDriver)

        shootAllTimeXD(webDriver)
    }

    fun shootAllTimeXD(webDriver: WebDriver) {
        while (true) {
            Thread.sleep(25)
            val randomSquare = "opponent-square" + generateRandomIndex() + generateRandomIndex()
            webDriver.findElement(By.id(randomSquare)).click()
        }
    }

    private fun firstRound(webDriver: WebDriver) {
        val randomSquare = "opponent-square" + generateRandomIndex() + generateRandomIndex()
        println(randomSquare)

//        Clicker.clickOn(webDriver, randomSquare, 0)
//        Clicker.clickOn(webDriver, randomSquare, 0)

        webDriver.findElement(By.id(randomSquare)).click()
    }

    private fun generateRandomIndex(): Int {
        return Random().nextInt(10)
    }

}