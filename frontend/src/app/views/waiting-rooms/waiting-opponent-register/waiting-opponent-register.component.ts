import {Component, OnDestroy} from '@angular/core'
import {OpponentPresentAsker} from "../../../rest/get/opponent-present-asker"
import {Router} from "@angular/router"

@Component({
             selector: 'app-waiting-opponent-name',
             templateUrl: './waiting-opponent-register.component.html',
             styleUrls: ['./waiting-opponent-register.component.css'],
           })
export class WaitingOpponentRegisterComponent implements OnDestroy {
  
  askerInterval
  
  constructor(private opponentPresentAsker: OpponentPresentAsker, private router: Router) {
    
    this.askIsOpponentPresentInterval()
  }
  
  ngOnDestroy() {
    clearInterval(this.askerInterval)
  }
  
  askIsOpponentPresentInterval() {
    this.askerInterval = setInterval(() => {
      console.log("Callback to know if opponent is in room")
      
      this.opponentPresentAsker.getOpponenIsPresent()
          .then(opponentPresentResponse => {
            //todo 27.8 lambda body to method, but I don't know if it work with interval
            let opponentPresent: boolean = this.opponentPresentAsker.responseToBoolean(opponentPresentResponse)
        
            if (opponentPresent) {
              this.router.navigate(['/game/fleet/placing'])
            }
          })
    }, 500)
  }
  
}
