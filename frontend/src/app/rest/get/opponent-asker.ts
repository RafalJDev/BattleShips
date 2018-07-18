import {GetRequestExecutor} from "./get-request-executor";

export class OpponentAsker extends GetRequestExecutor {
  
  hostUrl: string = GetRequestExecutor.getHostString();
  
  public getOpponentResult() {
    return this.get(this.hostUrl + "/game/opponent/result");
    
  }
}
