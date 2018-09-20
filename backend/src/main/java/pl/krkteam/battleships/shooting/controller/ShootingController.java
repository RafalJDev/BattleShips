package pl.krkteam.battleships.shooting.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.domain.GameBoardHolder;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.CoordinateDTO;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;
import pl.krkteam.battleships.room.holder.RoomHolder;
import pl.krkteam.battleships.shooting.dto.ShotDTO;
import pl.krkteam.battleships.shooting.dto.result.NotYourTurnDTO;
import pl.krkteam.battleships.shooting.dto.result.ResultPlayerWonDTO;
import pl.krkteam.battleships.shooting.dto.result.ShotResultDTO;
import pl.krkteam.battleships.shooting.services.ShotResultCheckerService;
import pl.krkteam.battleships.turns.holding.TurnHolder;

@Slf4j
@CrossOrigin(origins = "http://localhost:8080")
@RestController
class ShootingController {

    private final ShotResultCheckerService shotResultCheckerService;
    private final RoomHolder roomHolder;

    ShootingController(ShotResultCheckerService shotResultCheckerService,
                              RoomHolder roomHolder) {
        this.shotResultCheckerService = shotResultCheckerService;
        this.roomHolder = roomHolder;
    }

    @PostMapping(value = "/game/player/shot")
    String validateShot(@RequestBody String shotJson, @RequestParam String playerName,
                               @RequestParam String roomName) {
        final Player shootingPlayer = new Player(playerName);
        Gson gson = new Gson();

        final Game game = getGameFromRoom(roomName);
        final TurnHolder turnHolder = game.getTurnHolder();
        final boolean turnOfPlayer = turnHolder.isTurnOfPlayer(shootingPlayer);
        log.info("Player: " + playerName + ", turn: " + turnOfPlayer);
        if (!turnOfPlayer) {
            return gson.toJson(new NotYourTurnDTO());
        }

        final Player opponentPlayer = getOpponentPlayer(shootingPlayer, game);
        ShotDTO shotDTO = convertToShotDTO(shotJson, gson);
        log.info(shotJson);

        final ShotResultDTO shotResultDTO = getShotResult(opponentPlayer, shotDTO, game);
        log.info(shotResultDTO.toString());
        sendResponseToOpponentPlayer(shotResultDTO, shotDTO, opponentPlayer, game);

        calculateTurn(shootingPlayer, shotResultDTO, turnHolder);

        onGameEnd(shotResultDTO, roomName);
        return gson.toJson(shotResultDTO);
    }

    private void onGameEnd(ShotResultDTO shotResultDTO, String roomName) {
        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (shotResultDTO instanceof ResultPlayerWonDTO) {
                roomHolder.removeRoom(roomName);
            }
        }).start();
    }

    private Game getGameFromRoom(String roomName) {
        return roomHolder.getRoom(roomName).getGame();
    }

    private void calculateTurn(Player shootingPlayer, ShotResultDTO shotResultDTO, TurnHolder turnHolder) {
        turnHolder.addShotResult(shootingPlayer, shotResultDTO);
    }

    private ShotDTO convertToShotDTO(String shotJson, Gson gson) {
        return gson.fromJson(shotJson, ShotDTO.class);
    }

    private Player getOpponentPlayer(Player shootingPlayer, Game game) {
        final GameBoardHolder gameBoardHolder = game.getGameBoardHolder();
        return gameBoardHolder.getOpponentPlayer(shootingPlayer);
    }

    private ShotResultDTO getShotResult(Player opponentPlayer, ShotDTO shotDTO,
                                        Game game) {
        final GameBoardHolder gameBoardHolder = game.getGameBoardHolder();
        final GameBoard opponentGameBoard = gameBoardHolder.getGameBoard(opponentPlayer);
        return shotResultCheckerService.checkShotResult(shotDTO, opponentGameBoard);
    }

    private void sendResponseToOpponentPlayer(ShotResultDTO shotResultDTO, ShotDTO shotDTO,
                                              Player opponentPlayer, Game game) {
        CoordinateDTO shotCoordinates = shotDTO.getShotCoordinate();

        OpponentShotResult opponentShotResult = shotResultDTO
                .getOpponentShotResult(shotCoordinates.getY(), shotCoordinates.getX());

        game.addShotResultToQueue(opponentPlayer, opponentShotResult);
    }
}
