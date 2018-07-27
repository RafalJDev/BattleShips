import {GetRequestExecutor} from "./get-request-executor"
import {PlayersService} from "../../services/player-identification/players-service.service"
import {HttpClient} from "@angular/common/http"
import {Injectable} from "@angular/core"
import {RoomsService} from "../../services/player-identification/rooms-service"

@Injectable()
export class OpponentAsker extends GetRequestExecutor {
  
  hostUrl: string = GetRequestExecutor.getHostString()
  
  constructor(httpClient: HttpClient, private playersService: PlayersService, private roomsService: RoomsService) {
    super(httpClient)
  }
  
  public getOpponentResult() {
    return this.get(this.hostUrl + "/game/opponent/result?playerName=" + this.playersService.whoami.name +
      "&roomName=" + this.roomsService.room.roomName)
  }
}
