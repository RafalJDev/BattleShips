import {Injectable} from "@angular/core"
import {PostRequestExecutor} from "./post-request-executor"
import {HttpClient} from "@angular/common/http"
import {PlayersService} from "../../services/player-identification/players-service.service"
import {RoomsService} from "../../services/player-identification/rooms-service"

@Injectable()
export class ShotSender extends PostRequestExecutor {
  
  hostUrl: string = PostRequestExecutor.getHostString()
  
  constructor(httpClient: HttpClient, private playersService: PlayersService, private roomsService: RoomsService) {
    super(httpClient)
  }
  
  postShot(shotJson: string) {
    return this.post(this.hostUrl + "/game/player/shot?playerName=" + this.playersService.whoami.name +
      "&roomName=" + this.roomsService.room.roomName, shotJson)
  }
  
}
