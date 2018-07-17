package pl.krkteam.battleships.common.domain;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.player.Player;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;

public class GameBoardHolderTest {

    @Mock
    Player player1;

    @Mock
    Player player2;

    @Mock
    Player player3;

    @Mock
    GameBoard gameBoard1;

    @Mock
    GameBoard gameBoard2;

    @BeforeClass
    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenPlayerToAddGameBoardIsNull() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        // when - then
        gameBoardHolder.addPlayer(null, gameBoard1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenToAddGameBoardIsNull() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        // when - then
        gameBoardHolder.addPlayer(player1, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenPlayerToGetGameBoardIsNull() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        // when - then
        gameBoardHolder.getGameBoard(null);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldThrowExceptionWhenGivenPlayerDoesNotBelongToCurrentHolder() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        gameBoardHolder.addPlayer(player1, gameBoard1);
        gameBoardHolder.addPlayer(player2, gameBoard2);
        // when - then
        gameBoardHolder.getGameBoard(player3);
    }

    @Test
    public void shouldReturnProperGameBoardConnectedWithGivenPlayer() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        gameBoardHolder.addPlayer(player1, gameBoard1);
        gameBoardHolder.addPlayer(player2, gameBoard2);
        // when - then
        Assert.assertEquals(gameBoardHolder.getGameBoard(player1), gameBoard1);
        Assert.assertEquals(gameBoardHolder.getGameBoard(player2), gameBoard2);
    }

    @Test
    public void shouldReturnFalseWhenGivenPlayerToAddAlreadyExists() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        gameBoardHolder.addPlayer(player1, gameBoard1);
        boolean addResult = gameBoardHolder.addPlayer(player1, gameBoard2);
        // when - then
        Assert.assertFalse(addResult);
    }

}
