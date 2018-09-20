package pl.krkteam.battleships.common.domain;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.start.game.waiter.dto.ReadyForPlayDTO;
import pl.krkteam.battleships.start.game.waiter.dto.WaitForPlayDTO;

import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;
import static org.testng.AssertJUnit.assertEquals;

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

    @BeforeMethod
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenGivenPlayerToAddGameBoardIsNull() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        // when - then
        gameBoardHolder.addPlayer(null, gameBoard1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenGivenToAddGameBoardIsNull() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        // when - then
        gameBoardHolder.addPlayer(player1, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    void shouldThrowExceptionWhenGivenPlayerToGetGameBoardIsNull() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        // when - then
        gameBoardHolder.getGameBoard(null);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    void shouldThrowExceptionWhenGivenPlayerDoesNotBelongToCurrentHolder() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        gameBoardHolder.addPlayer(player1, gameBoard1);
        gameBoardHolder.addPlayer(player2, gameBoard2);
        // when - then
        gameBoardHolder.getGameBoard(player3);
    }

    @Test
    void shouldReturnProperGameBoardConnectedWithGivenPlayer() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        gameBoardHolder.addPlayer(player1, gameBoard1);
        gameBoardHolder.addPlayer(player2, gameBoard2);
        // when - then
        Assert.assertEquals(gameBoardHolder.getGameBoard(player1), gameBoard1);
        Assert.assertEquals(gameBoardHolder.getGameBoard(player2), gameBoard2);
    }

    @Test
    void shouldReturnFalseWhenGivenPlayerToAddAlreadyExists() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        gameBoardHolder.addPlayer(player1, gameBoard1);
        boolean addResult = gameBoardHolder.addPlayer(player1, gameBoard2);
        // when - then
        Assert.assertFalse(addResult);
    }

    @Test
    void testAreBothFleetsValidAndExpectAreValid() {
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        gameBoardHolder.addPlayer(player1, gameBoard1);
        gameBoardHolder.addPlayer(player2, gameBoard2);

        when(gameBoard1.isPlacedFleet()).thenReturn(true);
        when(gameBoard2.isPlacedFleet()).thenReturn(true);

        assertEquals(gameBoardHolder.areBothFleetsValid(player1).getResult(),
                new ReadyForPlayDTO().getResult());
    }

    @Test
    void testAreBothFleetsValidAndExpectAreNotValid() {
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        gameBoardHolder.addPlayer(player1, gameBoard1);
        gameBoardHolder.addPlayer(player2, gameBoard2);

        when(gameBoard1.isPlacedFleet()).thenReturn(true);

        assertEquals(gameBoardHolder.areBothFleetsValid(player1).getResult(),
                new WaitForPlayDTO().getResult());
    }

    @Test(expectedExceptions = IndexOutOfBoundsException.class)
    void testAreBothFleetsValidAndExpectThereAreNotEnoughPlayers() {
        GameBoardHolder gameBoardHolder = new GameBoardHolder();
        gameBoardHolder.addPlayer(player1, gameBoard1);

        when(gameBoard1.isPlacedFleet()).thenReturn(true);

        gameBoardHolder.areBothFleetsValid(player1);
    }
}
