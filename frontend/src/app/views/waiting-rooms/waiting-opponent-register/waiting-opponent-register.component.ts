import {Component, OnDestroy} from '@angular/core'

@Component({
             selector: 'app-waiting-opponent-name',
             templateUrl: './waiting-opponent-register.component.html',
             styleUrls: ['./waiting-opponent-register.component.css'],
           })
export class WaitingOpponentRegisterComponent implements OnDestroy {
  
  askerInterval
  
  constructor() {
    
    this.askForRoomListInInterval(null)
  }
  
  ngOnDestroy() {
    clearInterval(this.askerInterval)
  }
  
  askForRoomListInInterval(opponentInRoomAsker) {
    this.askerInterval = setInterval(() => {
      console.log("Callback to know if opponent is in room")
      
      clearInterval(this.askerInterval)
    }, 500)
  }
  
  isThereOpponent(): boolean {
    return true
  }
}
