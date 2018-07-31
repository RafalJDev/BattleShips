import {Component, OnInit} from "@angular/core"
import {HttpClient} from "@angular/common/http"
import {TranslateService} from "@ngx-translate/core"

@Component({
             selector: 'app-root',
             templateUrl: './app.component.html',
             styleUrls: ['./app.component.css']
           })
export class AppComponent implements OnInit {
  
  constructor(private httpClient: HttpClient, public translate: TranslateService) {
    
    translate.addLangs(['en', 'pl']);
    translate.setDefaultLang('pl');
    
    const browserLang = translate.getBrowserLang();
    translate.use(browserLang.match(/en|pl/) ? browserLang : 'pl');
  }
  
  languageChange(languageValue) {
    this.translate.use(languageValue);
  }
  
  ngOnInit(): void {
  
  }
}
