import {GetRequestExecutor} from "./get-request-executor";
import {Injectable} from "@angular/core"

@Injectable()
export class RegisteredPlayers extends GetRequestExecutor {

  hostUrl: string = GetRequestExecutor.getHostString();
1
  public getRegistered() {
    return this.get(this.hostUrl + "/registered");
  }

}
