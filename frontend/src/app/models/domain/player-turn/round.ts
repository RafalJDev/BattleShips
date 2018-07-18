export class Round {
  
  private playerRoundBoolean: boolean;
  
  private waitingForShotResult: boolean;
  
  constructor() {
    this.playerRoundBoolean = true;
    this.waitingForShotResult = false;
  }
  
  static ofNewGame(): Round {
    return new Round();
  }
  
  isPlayerRound(): boolean {
    return this.playerRoundBoolean;
  }
  
  nowIsPlayerTurn() {
    this.playerRoundBoolean = true;
  }
  
  nowIsOpponetTurn() {
    this.playerRoundBoolean = false;
  }
  
  isWaitingForShotResult(): boolean {
    return this.waitingForShotResult;
  }
  
  waitForShotResult() {
    this.waitingForShotResult = true;
  }
  
  endOfWaitingForShotResult() {
    this.waitingForShotResult = false;
  }
  
  isNotWaitingForShotResult(): boolean {
    return !(this.waitingForShotResult);
  }
  
  nextRoundIsPlayerRound() {
    this.nowIsPlayerTurn();
    this.endOfWaitingForShotResult();
  }
  
  nextRoundIsOpponentRound() {
    this.nowIsOpponetTurn();
    this.endOfWaitingForShotResult();
  }
  
  isOpponentRound() {
    return !this.isPlayerRound();
  }
}
