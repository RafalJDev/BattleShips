import {Round} from "../../../../../models/domain/player-turn/round"
import {OpponetShotResultFactory} from "../../result/opponet-shot-result-factory"
import {OpponentAsker} from "../../../../../rest/get/opponent-asker"
import {DOMCell} from "../../../../DOM/dom-cell"
import {HttpEvent} from "@angular/common/http"

export class OpponentResponseHandler {
  
  round: Round
  opponentAsker: OpponentAsker
  playerBoardDiv: Element
  
  calledFunction
  
  constructor(round: Round, opponentAsker: OpponentAsker, playerBoardDiv: Element) {
    this.round = round
    this.opponentAsker = opponentAsker
    this.playerBoardDiv = playerBoardDiv
  }
  
  handleOpponentResult() {
    if (this.round.isOpponentRound() && !this.round.isEndOfGame) {
      this.handleOpponentRound()
    }
  }
  
  handleOpponentRound() {
    this.calledFunction = setInterval(() => {
      console.log("Callback in handleOpponentResult")
      
      this.opponentAsker.getOpponentResult()
          .then(opponentResponse => {
            this.handleOpponentShotResult(opponentResponse)
          })
    }, 1000)
  }
  
  handleOpponentShotResult(opponentResponse: HttpEvent<string>) {
    let strategyOSR = OpponetShotResultFactory.createStrategyForOpponentShotResultFromJson(opponentResponse)
    
    let domCell = DOMCell.ofBoardAndIndexes(this.playerBoardDiv, strategyOSR.getRowIndex(),
                                            strategyOSR.getColumnIndex())
    strategyOSR.handleResult(this.round, domCell)
    
    if (this.round.isPlayerRound() || this.round.isEndOfGame) {
      clearInterval(this.calledFunction)
    }
  }
  
  private logSomeDebugInfo(opponentResponse, strategyOSR) {
    console.log(opponentResponse)
    
    console.log("opponentResponseJson: " + opponentResponse['opponentShotResult'])
    
    console.log(strategyOSR.resultNameJustForTesting())
    console.log("opponent rowindex" + strategyOSR.getRowIndex())
    console.log("opponent columnindex" + strategyOSR.getColumnIndex())
  }
}
