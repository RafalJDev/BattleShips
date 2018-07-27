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
    
    this.askIsOpponentPresentInterval(opponentPresentAsker)
  }
  
  ngOnDestroy() {
    clearInterval(this.askerInterval)
  }
  
  askIsOpponentPresentInterval(opponentInRoomAsker) {
    this.askerInterval = setInterval(() => {
      console.log("Callback to know if opponent is in room")
      
      this.opponentPresentAsker.getOpponenIsPresent()
          .then(opponentPresentResponse => {
        
            let opponentPresentString = opponentPresentResponse['response']
            let opponentPresent: boolean = this.responseStringToBoolean(opponentPresentString)
        
            if (opponentPresent) {
              this.router.navigate(['/game/fleet/placing'])
              // clearInterval(this.askerInterval)
            }
          })
    }, 500)
  }
  
  private responseStringToBoolean(result: string): boolean {
    switch (result) {
      case "OpponentPresent":
        return true
      case "OpponentAbsent":
        return false
    }
  }
}
