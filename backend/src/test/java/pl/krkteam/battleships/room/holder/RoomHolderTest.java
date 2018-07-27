package pl.krkteam.battleships.room.holder;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.room.holder.dto.create.room.CreateRoomWrongDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultOkDTO;
import pl.krkteam.battleships.room.holder.dto.join.result.JoinResultWrongDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentAbsentDTO;
import pl.krkteam.battleships.wait.opponent.dto.OpponentPresentDTO;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static org.testng.Assert.assertEquals;

public class RoomHolderTest {

    private RoomHolder roomHolder;

    @BeforeMethod
    public void setUp() {
        roomHolder = new RoomHolder();
    }

    @Test
    public void testGetRoomListAndExpectHasTheSameRoomsNames() {
        Player playerA = new Player("SomePlayerA");
        Player playerB = new Player("SomePlayerB");
        List<String> roomNamesList = Arrays.asList("SomeRoom1", "SomeRoom2");
        roomHolder.createRoomAndJoinPlayer(playerA, roomNamesList.get(0));
        roomHolder.createRoomAndJoinPlayer(playerB, roomNamesList.get(1));

        final List<String> returnedRoomNamesList = roomHolder.getRoomList()
                .stream()
                .map(room -> room.getRoomName())
                .collect(Collectors.toList());

        assertEquals(returnedRoomNamesList, roomNamesList);
    }

    @Test
    public void createRoomEndExpectSecondPlayerJoined() {
        String roomName = "SomeRoom";
        Player playerA = new Player("SomePlayerA");
        roomHolder.createRoomAndJoinPlayer(playerA, roomName);

        Player playerB = new Player("SomePlayerB");
        assertEquals(roomHolder.joinPlayer(roomName, playerB).getResult(),
                new JoinResultOkDTO().getResult());
    }

    @Test
    public void testTwoPlayersJoinedThirdPlayerCannotJoin() {
        String roomName = "SomeRoom";

        Player playerA = new Player("SomePlayerA");
        roomHolder.createRoomAndJoinPlayer(playerA, roomName);
        Player playerB = new Player("SomePlayerB");
        roomHolder.joinPlayer(roomName, playerB);

        Player playerC = new Player("PlayerC");
        assertEquals(roomHolder.joinPlayer(roomName, playerC).getResult(),
                new JoinResultWrongDTO().getResult());
    }

    @Test
    public void createRoomAndJoinSecondPlayerAndExpectOpponentPresent() {
        String roomName = "SomeRoom";

        Player playerA = new Player("SomePlayerA");
        roomHolder.createRoomAndJoinPlayer(playerA, roomName);
        Player playerB = new Player("SomePlayerB");
        roomHolder.joinPlayer(roomName, playerB);

        assertEquals(roomHolder.isOpponentInRoom(roomName, playerA).getResponse(),
                new OpponentPresentDTO().getResponse());
    }

    @Test
    public void createRoomAndExpectOpponentAbsent() {
        String roomName = "SomeRoom";

        Player playerA = new Player("SomePlayerA");
        roomHolder.createRoomAndJoinPlayer(playerA, roomName);

        assertEquals(roomHolder.isOpponentInRoom(roomName, playerA).getResponse(),
                new OpponentAbsentDTO().getResponse());
    }

    @Test
    public void createRoomAndJoinSecondPlayerAndOpponentPresenceForPlayerDoesNotBelongToRoom() {
        String roomName = "SomeRoom";

        Player playerA = new Player("SomePlayerA");
        roomHolder.createRoomAndJoinPlayer(playerA, roomName);
        Player playerB = new Player("SomePlayerB");
        roomHolder.joinPlayer(roomName, playerB);

        Player playerNotBelongToRoom = new Player("SomeExternalPlayer");

        assertEquals(roomHolder.isOpponentInRoom(roomName, playerNotBelongToRoom).getResponse(),
                new OpponentAbsentDTO().getResponse());
    }


    @Test
    public void createRoomAndExpectGetThisRoom() {
        String roomName = "SomeRoom";

        Player playerA = new Player("SomePlayerA");
        roomHolder.createRoomAndJoinPlayer(playerA, roomName);

        assertEquals(roomHolder.getRoom(roomName).getRoomName(), roomName);
    }

    @Test(expectedExceptions = NoSuchElementException.class)
    public void createRoomAndTryGetFromRoomHolderNotExistingRoom() {
        String roomName = "SomeRoom";
        Player playerA = new Player("SomePlayerA");
        roomHolder.createRoomAndJoinPlayer(playerA, roomName);

        String notExistingRoomName = "BadRoom";

        roomHolder.getRoom(notExistingRoomName);
    }

    @Test
    public void tryToCreateRoomForBusyNameAndExpectWillNotCreate() {
        String roomName = "SomeRoom";

        Player playerA = new Player("SomePlayerA");
        roomHolder.createRoomAndJoinPlayer(playerA, roomName);

        Player playerB = new Player("SomePlayerB");

        assertEquals(roomHolder.createRoomAndJoinPlayer(playerB, roomName).getResult(),
                new CreateRoomWrongDTO().getResult());
    }
}
