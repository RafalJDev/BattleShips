package pl.krkteam.battleships.opponent_shot_result_keeping;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

public class ShotResultQueueTest {

    @Mock
    OpponentShotResult opponentShotResult1;

    @Mock
    OpponentShotResult opponentShotResult2;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void shouldReturnFirstOfAddedShotResult() {
        // given
        ShotResultQueue shotResultQueue = new ShotResultQueue();
        // when
        when(opponentShotResult1.getMessage()).thenReturn("Missed");
        when(opponentShotResult2.getMessage()).thenReturn("Shot");
        boolean addResult1 = shotResultQueue.addShotResult(opponentShotResult1);
        boolean addResult2 = shotResultQueue.addShotResult(opponentShotResult2);
        // then
        OpponentShotResult firstResult = shotResultQueue.getOpponentShotResult();
        Assert.assertTrue(addResult1);
        Assert.assertTrue(addResult2);
        Assert.assertEquals(firstResult.getMessage(), "Missed");
    }

    @Test
    public void shouldReturnSecondOfAddedShotResultProperly() {
        // given
        ShotResultQueue shotResultQueue = new ShotResultQueue();
        // when
        when(opponentShotResult1.getMessage()).thenReturn("Missed");
        when(opponentShotResult2.getMessage()).thenReturn("Shot");
        boolean addResult1 = shotResultQueue.addShotResult(opponentShotResult1);
        boolean addResult2 = shotResultQueue.addShotResult(opponentShotResult2);
        // then
        shotResultQueue.getOpponentShotResult();
        OpponentShotResult secondResult = shotResultQueue.getOpponentShotResult();
        Assert.assertTrue(addResult1);
        Assert.assertTrue(addResult2);
        Assert.assertEquals(secondResult.getMessage(), "Shot");
    }

    @Test
    public void shouldReturnNoShootWhenQueueIsEmpty() {
        // given
        ShotResultQueue shotResultQueue = new ShotResultQueue();
        // when - then
        OpponentShotResult result = shotResultQueue.getOpponentShotResult();
        Assert.assertEquals(result.getMessage(), "NoShot");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddingResultIsNull() {
        // given
        ShotResultQueue shotResultQueue = new ShotResultQueue();
        // when - then
        shotResultQueue.addShotResult(null);
    }

    @Test
    public void shouldReturnFalseWhenAddingResultIsNoShoot() {
        // given
        ShotResultQueue shotResultQueue = new ShotResultQueue();
        OpponentShotResult shotResult = new NoShoot();
        // when
        boolean addResult = shotResultQueue.addShotResult(shotResult);
        // then
        Assert.assertFalse(addResult);
    }

}
