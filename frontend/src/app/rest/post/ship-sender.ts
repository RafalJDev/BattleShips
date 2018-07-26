import {PostRequestExecutor} from "./post-request-executor"
import {Injectable} from "@angular/core"
import {PlayersService} from "../../services/player-identification/players-service.service"
import {HttpClient} from "@angular/common/http"
import {RoomsService} from "../../services/player-identification/rooms-service"

@Injectable()
export class ShipSender extends PostRequestExecutor {
  
  hostUrl: string = PostRequestExecutor.getHostString()
  
  constructor(httpClient: HttpClient, private playersService: PlayersService, private roomsService: RoomsService) {
    super(httpClient)
  }
  
  postShip(shipArrayJson: string) {
    return this.post(this.hostUrl + "/ships?playerName=" + this.playersService.whoami.name +
      "&roomName=" + this.roomsService.room.roomName, shipArrayJson)
  }
  
}
