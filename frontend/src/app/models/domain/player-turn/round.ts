export class Round {
  
  playerRoundBoolean: boolean
  
  private waitingForShotResult: boolean
  
  isEndOfGame: boolean = false
  
  constructor(playerRoundBoolean) {
    if (playerRoundBoolean === 'true') {
      this.playerRoundBoolean = true
      this.waitingForShotResult = false
    } else {
      this.playerRoundBoolean = false
      this.waitingForShotResult = true
    }
  }
  
  static ofNewGame(playerRoundBoolean: boolean): Round {
    return new Round(playerRoundBoolean)
  }
  
  nextRoundIsPlayerRound() {
    this.nowIsPlayerTurn()
    this.endOfWaitingForShotResult()
  }
  
  nextRoundIsOpponentRound() {
    this.nowIsOpponentTurn()
    this.waitForShotResult()
  }
  
  isPlayerRound(): boolean {
    return this.playerRoundBoolean
    /*.toString() === 'true'*/
  }
  
  private nowIsPlayerTurn() {
    this.playerRoundBoolean = true
  }
  
  waitForShotResult() {
    console.log("i shouldnt be here at begining")
    this.waitingForShotResult = true
  }
  
  private nowIsOpponentTurn() {
    this.playerRoundBoolean = false
  }
  
  private endOfWaitingForShotResult() {
    this.waitingForShotResult = false
  }
  
  isNotWaitingForShotResult(): boolean {
    return !this.waitingForShotResult
    /*.toString() === 'false'*/
  }
  
  isOpponentRound(): boolean {
    return !this.isPlayerRound()
    /*.toString() === 'false'*/
  }
  
  isPlayerClickPossible(): boolean {
    return this.isPlayerRound() && this.isNotWaitingForShotResult() && !this.isEndOfGame
  }
  
}
