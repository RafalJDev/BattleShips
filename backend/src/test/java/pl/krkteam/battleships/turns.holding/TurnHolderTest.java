package pl.krkteam.battleships.turns.holding;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.shooting.dto.result.ResultHitDTO;
import pl.krkteam.battleships.shooting.dto.result.ResultMissDTO;
import pl.krkteam.battleships.shooting.dto.result.ResultSunkDTO;
import pl.krkteam.battleships.shooting.dto.result.ResultWrongShotDTO;

import java.util.NoSuchElementException;

public class TurnHolderTest {

    @Mock
    Player player1;

    @Mock
    Player player2;

    @Mock
    Player player3;

    @BeforeClass
    @BeforeTest
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @DataProvider(name = "illegalPlayers")
    public Object[][] illegalPlayers() {
        Object[][] result = new Object[3][2];
        result[0] = new Object[]{player1, null};
        result[1] = new Object[]{null, player2};
        result[2] = new Object[]{null, null};
        return result;
    }

    @DataProvider(name = "oneProperPlayer")
    public Object[][] oneProperPlayer() {
        Object[][] result = new Object[3][1];
        result[0] = new Object[]{player1};
        result[1] = new Object[]{player2};
        result[2] = new Object[]{player3};
        return result;
    }

    @DataProvider(name = "threeProperPlayers")
    public Object[][] threeProperPlayers() {
        Object[][] result = new Object[6][3];
        result[0] = new Object[]{player1, player2, player3};
        result[1] = new Object[]{player2, player1, player3};
        result[2] = new Object[]{player2, player3, player1};
        result[3] = new Object[]{player1, player3, player2};
        result[4] = new Object[]{player3, player1, player2};
        result[5] = new Object[]{player3, player2, player1};
        return result;
    }

    @DataProvider(name = "twoProperPlayers")
    public Object[][] twoProperPlayers() {
        Object[][] result = new Object[6][2];
        result[0] = new Object[]{player1, player2};
        result[1] = new Object[]{player1, player3};
        result[2] = new Object[]{player2, player1};
        result[3] = new Object[]{player2, player3};
        result[4] = new Object[]{player3, player1};
        result[5] = new Object[]{player3, player2};
        return result;
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "illegalPlayers")
    public void shouldThrowExceptionWhenAtLeastGivenInConstructorPlayerIsNull(Player playerOne, Player playerTwo) {
        new TurnHolder(playerOne, playerTwo);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldThrowExceptionOnChoosingFirstWhenGivenPlayerDoesNotBelongToHolder() {
        // given
        TurnHolder turnHolder = new TurnHolder(player1, player2);
        // when - then
        turnHolder.choseFirst(player3);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenToAddPlayerIsNull() {
        new TurnHolder().addPlayer(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "oneProperPlayer")
    public void shouldThrowExceptionWhenTryingAddAlreadyExistingInHolderPlayer(Player player) {
        // given
        TurnHolder turnHolder = new TurnHolder();
        // when - then
        turnHolder.addPlayer(player);
        turnHolder.addPlayer(player);
    }

    @Test(expectedExceptions = IllegalStateException.class, dataProvider = "threeProperPlayers")
    public void shouldThrowExceptionWhenHolderIsFull(Player playerOne, Player playerTwo, Player playerThree) {
        // given
        TurnHolder turnHolder = new TurnHolder();
        // when - then
        turnHolder.addPlayer(playerOne);
        turnHolder.addPlayer(playerTwo);
        turnHolder.addPlayer(playerThree);
    }

    @Test(dataProvider = "twoProperPlayers")
    public void shouldBeFistPlayerTurnByDefault(Player playerOne, Player playerTwo) {
        // given
        TurnHolder turnHolder = new TurnHolder();
        // when
        turnHolder.addPlayer(playerOne);
        turnHolder.addPlayer(playerTwo);
        // then
        Assert.assertEquals(turnHolder.getCurrentPlayer(), playerOne);
        Assert.assertTrue(turnHolder.isTurnOfPlayer(playerOne));
    }

    @Test(expectedExceptions = NoSuchElementException.class, dataProvider = "threeProperPlayers")
    public void shouldThrowExceptionWhenGivenPlayerTo_isTurnOfPlayer_DoesNotBelongToHolder(Player playerOne,
                                                                                           Player playerTwo,
                                                                                           Player playerThree) {
        // given
        TurnHolder turnHolder = new TurnHolder(playerOne, playerTwo);
        // when - then
        turnHolder.isTurnOfPlayer(playerThree);
    }

    @Test(dataProvider = "twoProperPlayers")
    public void shouldSetFirstPlayerOn_choseFirst(Player playerOne, Player playerTwo) {
        // given
        TurnHolder turnHolder = new TurnHolder();
        turnHolder.addPlayer(playerOne);
        turnHolder.addPlayer(playerTwo);
        // when
        turnHolder.choseFirst(playerTwo);
        // then
        Assert.assertEquals(turnHolder.getCurrentPlayer(), playerTwo);
    }

    @Test(dataProvider = "twoProperPlayers")
    public void shouldChangeCurrentPlayerWhenHeMissed(Player playerOne, Player playerTwo) {
        // given
        TurnHolder turnHolder = new TurnHolder(playerOne, playerTwo);
        // when
        turnHolder.addShotResult(playerOne, new ResultMissDTO());
        // then
        Assert.assertEquals(turnHolder.getCurrentPlayer(), playerTwo);
        Assert.assertTrue(turnHolder.isTurnOfPlayer(playerTwo));
    }

    @Test(dataProvider = "twoProperPlayers")
    public void shouldChangeCurrentPlayerWhenHeMadeWrongShot(Player playerOne, Player playerTwo) {
        // given
        TurnHolder turnHolder = new TurnHolder(playerOne, playerTwo);
        // when
        turnHolder.addShotResult(playerOne, new ResultWrongShotDTO());
        // then
        Assert.assertEquals(turnHolder.getCurrentPlayer(), playerTwo);
        Assert.assertTrue(turnHolder.isTurnOfPlayer(playerTwo));
    }

    @Test(dataProvider = "twoProperPlayers")
    public void shouldStayCurrentPlayerWhenHeHit(Player playerOne, Player playerTwo) {
        // given
        TurnHolder turnHolder = new TurnHolder(playerOne, playerTwo);
        // when
        turnHolder.addShotResult(playerOne, new ResultHitDTO());
        // then
        Assert.assertEquals(turnHolder.getCurrentPlayer(), playerOne);
        Assert.assertTrue(turnHolder.isTurnOfPlayer(playerOne));
    }

    @Test(dataProvider = "twoProperPlayers")
    public void shouldStayCurrentPlayerWhenHeSunk(Player playerOne, Player playerTwo) {
        // given
        TurnHolder turnHolder = new TurnHolder(playerOne, playerTwo);
        // when
        turnHolder.addShotResult(playerOne, new ResultSunkDTO());
        // then
        Assert.assertEquals(turnHolder.getCurrentPlayer(), playerOne);
        Assert.assertTrue(turnHolder.isTurnOfPlayer(playerOne));
    }

    @Test(expectedExceptions = IllegalStateException.class, dataProvider = "twoProperPlayers")
    public void shouldThrowExceptionOn_choseFirst_WhenGameHasBeenStarted(Player playerOne, Player playerTwo) {
        // given
        TurnHolder turnHolder = new TurnHolder(playerOne, playerTwo);
        // when
        turnHolder.addShotResult(playerOne, new ResultSunkDTO());
        // then
        turnHolder.choseFirst(playerTwo);
    }

    @Test(expectedExceptions = NoSuchElementException.class, dataProvider = "threeProperPlayers")
    public void shouldThrowExceptionOn_addShotResult_WhenPlayerDoesNotExistInHolder(Player playerOne, Player playerTwo,
                                                                                    Player playerThree) {
        // given
        TurnHolder turnHolder = new TurnHolder(playerOne, playerTwo);
        // when - then
        turnHolder.addShotResult(playerThree, new ResultSunkDTO());
    }

}
