import {Injectable} from "@angular/core";
import {PostRequestExecutor} from "./post-request-executor";
import {HttpClient} from "@angular/common/http";
import {PlayersService} from "../../services/players-service.service";

@Injectable()
export class ShotSender extends PostRequestExecutor {

  hostUrl: string = PostRequestExecutor.getHostString();

  constructor(httpClient: HttpClient, private playersService: PlayersService) {
    super(httpClient);
  }

  postShot(shotJson: string) {
    return this.post(this.hostUrl + "/game/player/shot?player=" + this.playersService.whoami.name, shotJson);
  }

}
