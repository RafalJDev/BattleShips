package pl.krkteam.battleships.shooting.services;

import org.springframework.stereotype.Service;
import pl.krkteam.battleships.common.domain.Board;
import pl.krkteam.battleships.common.domain.Coordinates;
import pl.krkteam.battleships.common.domain.GameBoard;
import pl.krkteam.battleships.shooting.conventers.CoordinateDTOtoCoordinates;
import pl.krkteam.battleships.shooting.models.dto.ShotDTO;
import pl.krkteam.battleships.shooting.models.dto.result.ResultWrongShotDTO;
import pl.krkteam.battleships.shooting.models.dto.result.ShotResultDTO;

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
            System.out.println(e.getMessage());
            return new ResultWrongShotDTO();
        }
    }

}
