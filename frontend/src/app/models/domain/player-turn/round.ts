export class Round {
  
  private playerRoundBoolean: boolean;
  
  private waitingForShotResult: boolean;
  
  constructor(playerRoundBoolean: boolean) {
    this.playerRoundBoolean = playerRoundBoolean;
    this.waitingForShotResult = false;
  }
  
  static ofNewGame(playerRoundBoolean: boolean): Round {
    return new Round(playerRoundBoolean);
  }
  
  nextRoundIsPlayerRound() {
    this.nowIsPlayerTurn();
    this.endOfWaitingForShotResult();
  }
  
  nextRoundIsOpponentRound() {
    this.nowIsOpponentTurn();
    this.waitForShotResult();
  }
  
  isPlayerRound(): boolean {
    return this.playerRoundBoolean;
  }
  
  isWaitingForShotResult(): boolean {
    return this.waitingForShotResult;
  }
  
  private nowIsPlayerTurn() {
    this.playerRoundBoolean = true;
  }
  
  waitForShotResult() {
    this.waitingForShotResult = true;
  }
  
  private nowIsOpponentTurn() {
    this.playerRoundBoolean = false;
  }
  
  private endOfWaitingForShotResult() {
    this.waitingForShotResult = false;
  }
  
  isNotWaitingForShotResult(): boolean {
    return !(this.waitingForShotResult);
  }
  
  isOpponentRound() {
    return !this.isPlayerRound();
  }
}
