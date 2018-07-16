import {GetRequestExecutor} from "./get-request-executor";

export class RegisteredPlayers extends GetRequestExecutor {

  hostUrl: string = GetRequestExecutor.getHostString();

  public getRegistered() {
    return this.get(this.hostUrl + "/registered");
  }

}
