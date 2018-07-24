import {Component, OnInit} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {ShipArray} from "./models/domain/ship/ship-array";
import {ShipsDaoService} from "./models/dto/ships/ships-dao.service";
import {TranslateService} from "@ngx-translate/core";

@Component({
             selector: 'app-root',
             templateUrl: './app.component.html',
             styleUrls: ['./app.component.css']
           })
export class AppComponent implements OnInit {
  
  message: string;
  
  data1: string;
  data2: string;
  
  selectedLanguage: string = "en";
  
  constructor(private httpClient: HttpClient, private translate: TranslateService) {
    
    translate.addLangs(['en', 'pl']);
    translate.setDefaultLang('en');
    
    const browserLang = translate.getBrowserLang();
    translate.use(browserLang.match(/en|pl/) ? browserLang : 'en');
  }
  
  languageChange(languageValue) {
    this.translate.use(languageValue);
    
    this.selectedLanguage = languageValue;
  }
  
  ngOnInit(): void {
  
    //TODO remove 6.7.2018
  
    const host = this.getHostString();
    const hostUrl = "http://" + host;
  
    this.message = 'null';
  
    this.data1 = "cos";
    this.data2 = "cos2";
  
    this.httpClient.get(hostUrl + "/hello")
        .subscribe(data => {
          console.log('DATA', data);
  
          this.data1 = data['message'];
          this.message = "Get:" + this.data1;
        });
    
    this.httpClient.post(hostUrl + "/hello/postt", "{\"message\": \"Bye my dear\"}").subscribe(
      data => {
        console.log('DATA', data);
  
        this.data2 = data['message'];
        this.message += " post: " + this.data2;
      }
    );
  
    console.log(this.data1);
    console.log(this.data2);
  
    this.createAndPostShipToSpring(hostUrl);
  
    const shipArray = new ShipArray();
    shipArray.addShipWithCoordinates(1, 1, 1, 2);
  }
  
  createAndPostShipToSpring(hostUrl): void {
    let ships = new ShipsDaoService(["Ship1", "Statek", "Anaconda"]);
  
    let response = JSON.stringify(ships);
  
    console.log("I will try do to post to spring backend");
    this.httpClient.post(hostUrl + "/post/ships", response)
        .subscribe(data => {
          console.log(data);
        });
  }
  
  private getHostString(): string {
    const host = window.location.host;
    console.log(host);
  
    if (host.includes("localhost")) {
      return "localhost:8080";
    }
    return host;
  }
}
