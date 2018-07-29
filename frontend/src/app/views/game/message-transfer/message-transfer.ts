export class MessageTransfer {
  
  playerBoardMessage: string = "Before game"
  
  opponentBoardMessage: string = "Before game"
  
  private static thisRef:MessageTransfer = null
  
  static getInstance():MessageTransfer {
    if (this.thisRef ==null) {
      this.thisRef = new MessageTransfer()
    }
    return  this.thisRef
  }
}
