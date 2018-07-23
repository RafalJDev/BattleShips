import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";

import {AppComponent} from "./app.component";
import {HttpClientModule} from "@angular/common/http";
import {ConfigurationScreenComponent} from "./views/configuration-screen/configuration-screen.component";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule, MatButtonToggleModule, MatCheckboxModule, MatInputModule} from "@angular/material";
import {GameComponent} from "./views/game/game.component";
import {ShipsToPlaceComponent} from "./views/game/ships-to-place/ships-to-place.component";
import {DragShipService} from "./services/drag-ship/drag-ship.service";
import {BoardOfCells} from "./models/domain/board/board-of-cells";
import {ShipGenerator} from "./services/ship-generator/ship-generator.service";
import {ShipSender} from "./rest/post/ship-sender";
import {StartGameButtonComponent} from "./views/game/start-game-button/start-game-button.component";
import {PlayerBoardComponent} from './views/boards/player-board/player-board.component';
import {OpponentBoardComponent} from './views/boards/opponent-board/opponent-board.component';
import {RequestExecutor} from "./rest/request-executor";
import {ShotSender} from "./rest/post/shot-sender";
import {RegisteredPlayersComponent} from "./views/registered-players/registered-players.component";
import {OpponentInfoComponent} from "./views/opponent-info/opponent-info.component";
import {LoginRequestSender} from "./rest/post/login-request";
import {PlayersService} from "./services/players-service.service";
import {RegisteredPlayers} from "./rest/get/registered-players";
import {OpponentAsker} from "./rest/get/opponent-asker";
import {FirstTurnAsker} from "./rest/get/first-turn-asker";
import {WindowRef} from "./WindowRef";

const appRoutes: Routes = [
  {path: 'configuration', component: ConfigurationScreenComponent},
  {path: 'configuration/board', component: GameComponent},
  {path: 'players/registered', component: RegisteredPlayersComponent},
];

@NgModule({
            declarations: [
              AppComponent,
              ConfigurationScreenComponent,
              GameComponent,
              ShipsToPlaceComponent,
              StartGameButtonComponent,
              PlayerBoardComponent,
              OpponentBoardComponent,
              RegisteredPlayersComponent,
              OpponentInfoComponent
            ],
            imports: [
              BrowserModule,
              HttpClientModule,
              FormsModule,
              RouterModule.forRoot(appRoutes),
              BrowserAnimationsModule,
              MatCheckboxModule,
              MatInputModule,
              MatButtonModule,
              MatButtonToggleModule,
            ],
            providers: [
              DragShipService,
              BoardOfCells,
              ShipGenerator,
              ShipSender,
              RequestExecutor,
              ShotSender,
              LoginRequestSender,
              PlayersService,
              RegisteredPlayers,
              OpponentAsker,
              FirstTurnAsker,
              WindowRef,
            ],
            bootstrap: [AppComponent]
          })
export class AppModule {
  
  constructor(private winRef: WindowRef) {
    winRef.nativeWindow.document.locale = 'fr';
    
  }
}
