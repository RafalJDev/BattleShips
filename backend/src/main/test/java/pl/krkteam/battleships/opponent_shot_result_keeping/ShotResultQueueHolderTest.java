package pl.krkteam.battleships.opponent_shot_result_keeping;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.player.Player;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;

public class ShotResultQueueHolderTest {

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
        result[0] = new Object[] {player1, null};
        result[1] = new Object[] {null, player2};
        result[2] = new Object[] {null, null};
        return result;
    }

    @Test(expectedExceptions = IllegalArgumentException.class, dataProvider = "illegalPlayers")
    public void shouldThrowExceptionWhenAtLeastOneGivenPlayerIsNull(Player player1, Player player2) {
        new ShotResultQueueHolder(player1,player2);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenGivenPlayerIsNull() {
        // given
        ShotResultQueueHolder shotResultQueueHolder = new ShotResultQueueHolder(player1,player2);
        // when - then
        shotResultQueueHolder.getShotResultQueue(null);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void shouldThrowExceptionWhenGivenPlayerDoesNotBelongToCurrentHolder() {
        // given
        ShotResultQueueHolder shotResultQueueHolder = new ShotResultQueueHolder(player1,player2);
        // when - then
        shotResultQueueHolder.getShotResultQueue(player3);
    }

    @Mock
    OpponentShotResult opponentShotResult1;

    @Mock
    OpponentShotResult opponentShotResult2;

    @Test
    public void shouldReturnProperQueueConnectedWithGivenPlayer() {
        // given
        ShotResultQueueHolder shotResultQueueHolder = new ShotResultQueueHolder(player1,player2);
        when(opponentShotResult1.getMessage()).thenReturn("Missed");
        when(opponentShotResult2.getMessage()).thenReturn("Hit");
        // when
        ShotResultQueue queueOfPlayer1 = shotResultQueueHolder.getShotResultQueue(player1);
        queueOfPlayer1.addShotResult(opponentShotResult1);
        ShotResultQueue queueOfPlayer2 = shotResultQueueHolder.getShotResultQueue(player2);
        queueOfPlayer2.addShotResult(opponentShotResult2);
        // then
        
    }

}
