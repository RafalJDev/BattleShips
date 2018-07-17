package pl.krkteam.battleships.common.domain;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.player.Player;

import java.util.NoSuchElementException;

public class GameBoardHolderTest {

    @Mock
    Player player1;

    @Mock
    Player player2;

    @Mock
    Player player3;

    @BeforeClass
    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DataProvider(name = "illegalPlayers")
    Object[][] illegalPlayers() {
        Object[][] result = new Object[3][2];
        result[1] = new Object[] {player1, null};
        result[0] = new Object[] {null, player2};
        result[2] = new Object[] {null, null};
        return result;
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "illegalPlayers")
    public void shouldThrowExceptionWhenAtLeastOneGivenPlayerIsNull(Player player1, Player player2) {
        new GameBoardHolder(player1,player2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenPlayerIsNull() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder(player1,player2);
        // when - then
        gameBoardHolder.getGameBoard(null);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldThrowExceptionWhenGivenPlayerDoesNotBelongToCurrentHolder() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder(player1,player2);
        // when - then
        gameBoardHolder.getGameBoard(player3);
    }

    @Test
    public void shouldReturnProperGameBoardConnectedWithGivenPlayer() {
        // given
        GameBoardHolder gameBoardHolder = new GameBoardHolder(player1,player2);
        // when - then
        Assert.assertNotEquals(gameBoardHolder.getGameBoard(player1), gameBoardHolder.getGameBoard(player2));

    }

}
