import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class ShipSender {
  
  hostUrl: string = this.getHostString();
  
  constructor(private httpClient: HttpClient) {
  }
  
  async postShip(shipArrayJson: string) {
    let result = await this.httpClient
                           .post<string>(this.hostUrl + "/ships", shipArrayJson)
                           .toPromise();
    console.log(this.hostUrl);
    return result;
  }
  
  private getHostString(): string {
    const host = window.location.host;
  
    return host.includes("localhost") ? "http://localhost:8080" : "";
  }
}
