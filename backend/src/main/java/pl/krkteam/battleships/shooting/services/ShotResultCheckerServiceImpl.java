package pl.krkteam.battleships.shooting.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.shooting.converters.CoordinateDTOtoCoordinates;
import pl.krkteam.battleships.shooting.dto.ShotDTO;
import pl.krkteam.battleships.shooting.dto.result.ResultWrongShotDTO;
import pl.krkteam.battleships.shooting.dto.result.ShotResultDTO;

@Slf4j
@Service
public class ShotResultCheckerServiceImpl implements ShotResultCheckerService {
    private final CoordinateDTOtoCoordinates coordinateDTOtoCoordinates;

    public ShotResultCheckerServiceImpl(
            CoordinateDTOtoCoordinates coordinateDTOtoCoordinates) {
        this.coordinateDTOtoCoordinates = coordinateDTOtoCoordinates;
    }

    @Override
    public ShotResultDTO checkShotResult(ShotDTO shotDTO, GameBoard gameBoard) {

        Coordinates shotCoor = coordinateDTOtoCoordinates.convert(shotDTO.getShotCoordinate());
        final Board board = gameBoard.getBoard();

        ChainOfShotResult chainOfShotResult = new CheckMiss();
        try {
            return chainOfShotResult.getShotResult(shotCoor, board);
        } catch (UnexpectedShotResultException e) {
            log.error(e.getMessage());
            return new ResultWrongShotDTO();
        }
    }

}
