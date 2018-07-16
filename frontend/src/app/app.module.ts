import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";

import {AppComponent} from "./app.component";
import {HttpClientModule} from "@angular/common/http";
import {ConfigurationScreenComponent} from "./views/configuration-screen/configuration-screen.component";
import {BoardComponent} from "./views/boards/board/board.component";
import {FormsModule} from "@angular/forms";
import {RouterModule, Routes} from "@angular/router";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatButtonModule, MatButtonToggleModule, MatCheckboxModule, MatInputModule} from "@angular/material";
import {GameComponent} from "./views/game/game.component";
import {ShipsToPlaceComponent} from "./views/game/ships-to-place/ships-to-place.component";
import {DragShipService} from "./services/drag-ship/drag-ship.service";
import {BoardOfCells} from "./models/board/board-of-cells";
import {ShipGenerator} from "./services/ship-generator/ship-generator.service";
import {ShipSender} from "./rest/post/ship-sender";
import {StartGameButtonComponent} from "./views/game/start-game-button/start-game-button.component";
import {PlayerBoardComponent} from './views/boards/player-board/player-board.component';
import {OpponentBoardComponent} from './views/boards/opponent-board/opponent-board.component';
import {RequestExecutor} from "./rest/request-executor";
import {OpponentBoardHandler} from "./services/click-handler/oppent-board-handler.service";
import {ShotSender} from "./rest/post/shot-sender";
import {RegisteredPlayersComponent} from "./views/registered-players/registered-players.component";
import {OpponentInfoComponent} from "./views/opponent-info/opponent-info.component";
import {LoginRequestSender} from "./rest/post/login-request";
import {PlayersService} from "./services/players-service.service";
import {RegisteredPlayers} from "./rest/get/registered-players";

const appRoutes: Routes = [
  {path: 'configuration', component: ConfigurationScreenComponent},
  {path: 'game/board', component: BoardComponent},
  {path: 'configuration/board', component: GameComponent},
  {path: 'players/registered', component: RegisteredPlayersComponent},
  {path: 'game', component: GameComponent}
];

@NgModule({
            declarations: [
              AppComponent,
              ConfigurationScreenComponent,
              BoardComponent,
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
              OpponentBoardHandler,
              ShotSender,
              LoginRequestSender,
              PlayersService,
              RegisteredPlayers
            ],
            bootstrap: [AppComponent]
          })
export class AppModule {
}
