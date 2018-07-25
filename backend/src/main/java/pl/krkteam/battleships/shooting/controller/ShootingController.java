package pl.krkteam.battleships.shooting.controller;

import com.google.gson.Gson;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import pl.krkteam.battleships.common.domain.Game;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.common.domain.GameBoardHolder;
import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.CoordinateDTO;
import pl.krkteam.battleships.opponent.shot.response.dto.OpponentShotResult;
import pl.krkteam.battleships.shooting.dto.ShotDTO;
import pl.krkteam.battleships.shooting.dto.result.NotYourTurnDTO;
import pl.krkteam.battleships.shooting.dto.result.ShotResultDTO;
import pl.krkteam.battleships.shooting.services.ShotResultCheckerService;
import pl.krkteam.battleships.turns.holding.TurnHolder;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class ShootingController {

    private static final Logger logger = LogManager.getLogger(ShootingController.class);

    private final ShotResultCheckerService shotResultCheckerService;
    private final Game game;

    public ShootingController(ShotResultCheckerService shotResultCheckerService, Game game) {
        this.shotResultCheckerService = shotResultCheckerService;
        this.game = game;
    }

    @PostMapping(value = "/game/player/shot")
    public String validateShot(@RequestBody String shotJson, @RequestParam String playerName) {
        logger.debug("Post body: " + shotJson);

        final Player shootingPlayer = new Player(playerName);
        Gson gson = new Gson();

        final TurnHolder turnHolder = game.getTurnHolder();
        if (!turnHolder.isTurnOfPlayer(shootingPlayer)) {
            return gson.toJson(new NotYourTurnDTO());
        }

        final Player opponentPlayer = getOpponentPlayer(shootingPlayer);
        ShotDTO shotDTO = convertToShotDTO(shotJson);
        logger.info("Shot from frontend:" + shotDTO);

        final ShotResultDTO shotResultDTO = getShotResult(opponentPlayer, shotDTO);
        sendResponseToOpponentPlayer(shotResultDTO, shotDTO, opponentPlayer);

        calculateTurn(shootingPlayer, shotResultDTO, turnHolder);

        return gson.toJson(shotResultDTO);
    }

    private void calculateTurn(Player shootingPlayer, ShotResultDTO shotResultDTO, TurnHolder turnHolder) {
        turnHolder.addShotResult(shootingPlayer, shotResultDTO);
    }

    private ShotDTO convertToShotDTO(String shotJson) {
        Gson gson = new Gson();
        return gson.fromJson(shotJson, ShotDTO.class);
    }

    private Player getOpponentPlayer(Player shootingPlayer) {
        final GameBoardHolder gameBoardHolder = game.getGameBoardHolder();
        return gameBoardHolder.getOpponentPlayer(shootingPlayer);
    }

    private ShotResultDTO getShotResult(Player opponentPlayer, ShotDTO shotDTO) {
        final GameBoardHolder gameBoardHolder = game.getGameBoardHolder();
        final GameBoard opponentGameBoard = gameBoardHolder.getGameBoard(opponentPlayer);
        return shotResultCheckerService.checkShotResult(shotDTO, opponentGameBoard);
    }

    private void sendResponseToOpponentPlayer(ShotResultDTO shotResultDTO, ShotDTO shotDTO, Player opponentPlayer) {
        CoordinateDTO shotCoordinates = shotDTO.getShotCoordinate();

        OpponentShotResult opponentShotResult = shotResultDTO
                .getOpponentShotResult(shotCoordinates.getY(), shotCoordinates.getX());

        game.addShotResultToQueue(opponentPlayer, opponentShotResult);
    }
}
