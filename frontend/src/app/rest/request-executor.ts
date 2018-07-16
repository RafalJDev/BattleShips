import {HttpClient} from "@angular/common/http";
import {Injectable} from "@angular/core";

@Injectable()
export class RequestExecutor {
  
  constructor(protected httpClient: HttpClient) {
  }
  
  static getHostString(): string {
    const host = window.location.host;
    
    return host.includes("localhost") ? "http://localhost:8080" : "";
  }
  
}
