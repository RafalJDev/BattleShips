package pl.krkteam.battleships.authentication;

import pl.krkteam.battleships.common.domain.player.Player;
import pl.krkteam.battleships.common.dto.PlayerDTO;

import java.util.Collection;

public class PlayerConversionUtil {

    public static PlayerDTO convertPlayerToPlayerDTO(Player playerDAO) {
        PlayerDTO result = new PlayerDTO();
        result.name = playerDAO.getName();
        return result;
    }

    public static Player convertPlayerDTOtoPlayer(PlayerDTO playerDTO) {
        return new Player(playerDTO.name);
    }

    public static PlayerDTO[] convertPlayerToPlayerDTOs(Collection<Player> playerCollection) {
        return playerCollection.stream()
                .map(playerDAO -> new PlayerDTO(playerDAO.getName()))
                .toArray(PlayerDTO[]::new);
    }

}
