import {Injectable} from "@angular/core"
import {PostRequestExecutor} from "./post-request-executor"
import {PlayersService} from "../../services/player-identification/players-service.service"
import {HttpClient} from "@angular/common/http"

@Injectable()
export class RoomCreateAsker extends PostRequestExecutor {
  
  hostUrl: string = PostRequestExecutor.getHostString()
  
  constructor(httpClient: HttpClient, private playersService: PlayersService) {
    super(httpClient)
  }
  
  postCreateRoom(roomToCreateJson) {
    return this.post(this.hostUrl + "/room/create?playerName=" + this.playersService.whoami.name, roomToCreateJson);
  }
}
