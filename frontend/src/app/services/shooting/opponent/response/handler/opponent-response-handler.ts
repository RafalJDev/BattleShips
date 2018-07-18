import {Round} from "../../../../../models/domain/player-turn/round";
import {OpponetShotResultFactory} from "../../result/opponet-shot-result-factory";
import {OpponentAsker} from "../../../../../rest/get/opponent-asker";
import {DOMCell} from "../../../../DOM/dom-cell";

export class OpponentResponseHandler {

  round: Round;
  opponentAsker: OpponentAsker;
  playerBoardDiv: Element;

  calledFunction;

  constructor(round: Round, opponentAsker: OpponentAsker, playerBoardDiv: Element) {
    this.round = round;
    this.opponentAsker = opponentAsker;
    this.playerBoardDiv = playerBoardDiv;
  }

  handleOpponentResult() {
    if (this.round.isOpponentRound()) {
      this.calledFunction = setInterval(() => {
        console.log("Callback my dear");

        this.opponentAsker.getOpponentResult()
            .then(opponentResponse => {

              let strategyOSR = OpponetShotResultFactory.createStrategyForOpponentShotResultFromJson(opponentResponse);

              let domCell = DOMCell.ofBoardAndIndexes(this.playerBoardDiv, strategyOSR.getRowIndex(),
                                                      strategyOSR.getColumnIndex());
              strategyOSR.handleResult(this.round, domCell);

              if (this.round.isPlayerRound()) {
                clearInterval(this.calledFunction);
              }
            });
      }, 1000);
    }
  }

  private logSomeDebugInfo(opponentResponse, strategyOSR) {
    console.log(opponentResponse);

    console.log("opponentResponseJson: " + opponentResponse['opponentShotResult']);

    console.log(strategyOSR.resultNameJustForTesting());
    console.log("opponent rowindex" + strategyOSR.getRowIndex());
    console.log("opponent columnindex" + strategyOSR.getColumnIndex());
  }
}
