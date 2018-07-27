import {BrowserModule} from "@angular/platform-browser"
import {NgModule} from "@angular/core"

import {AppComponent} from "./app.component"
import {HttpClient, HttpClientModule} from "@angular/common/http"
import {PlayerNameConfiguration} from "./views/configuration-rooms/player-name-configuration/player-name-configuration.component"
import {FormsModule} from "@angular/forms"
import {RouterModule, Routes} from "@angular/router"
import {BrowserAnimationsModule} from "@angular/platform-browser/animations"

import {
  MatButtonModule,
  MatButtonToggleModule,
  MatCheckboxModule,
  MatInputModule,
  MatListModule,
} from "@angular/material"

import {GameComponent} from "./views/game/game.component"
import {ShipsToPlaceComponent} from "./views/fleet-placing/ships-to-place/ships-to-place.component"
import {DragShipService} from "./services/drag-ship/drag-ship.service"
import {BoardOfCells} from "./models/domain/board/board-of-cells"
import {ShipGenerator} from "./services/ship-generator/ship-generator.service"
import {ShipSender} from "./rest/post/ship-sender"
import {PlayerBoardComponent} from './views/boards/player-board/player-board.component'
import {OpponentBoardComponent} from './views/boards/opponent-board/opponent-board.component'
import {RequestExecutor} from "./rest/request-executor"
import {ShotSender} from "./rest/post/shot-sender"
import {LoginRequestSender} from "./rest/post/login-request"
import {PlayersService} from "./services/player-identification/players-service.service"
import {RegisteredPlayers} from "./rest/get/registered-players"
import {OpponentAsker} from "./rest/get/opponent-asker"
import {FirstRoundAsker} from "./rest/get/first-round-asker.service"
import {TranslateLoader, TranslateModule} from '@ngx-translate/core'
import {TranslateHttpLoader} from '@ngx-translate/http-loader'
import {RoomConfiguration} from './views/configuration-rooms/room-configuration/room-configuration.component'
import {RegisteredPlayersComponent} from "./views/registered-players/registered-players.component"
import {WaitingOpponentRegisterComponent} from "./views/waiting-rooms/waiting-opponent-register/waiting-opponent-register.component"
import {WaitingOpponentFleetComponent} from './views/waiting-rooms/waiting-opponent-fleet/waiting-opponent-fleet.component'
import {RoomListAsker} from "./rest/get/room-list-asker.service"
import {FleetPlacingComponent} from './views/fleet-placing/fleet-placing.component'
import {PlacingBoardComponent} from './views/fleet-placing/placing-board/placing-board.component'
import {RoomsService} from "./services/player-identification/rooms-service"
import {RoomCreateAsker} from "./rest/post/room-create-asker.service"
import {RoomJoinAsker} from "./rest/get/room-join-asker"
import {OpponentPresentAsker} from "./rest/get/opponent-present-asker"

const appRoutes: Routes = [
  {path: '', redirectTo: 'configuration/player/name', pathMatch: 'full'},
  {path: 'players/registered', component: RegisteredPlayersComponent},
  {path: 'configuration/player/name', component: PlayerNameConfiguration},
  {path: 'configuration/room', component: RoomConfiguration},
  {path: 'waiting/opponent/register', component: WaitingOpponentRegisterComponent},
  {path: 'game/fleet/placing', component: FleetPlacingComponent},
  {path: 'waiting/opponent/fleet', component: WaitingOpponentFleetComponent},
  {path: 'game/board', component: GameComponent},
]

// AoT requires an exported function for factories
export function HttpLoaderFactory(httpClient: HttpClient) {
  return new TranslateHttpLoader(httpClient)
}

@NgModule({
            declarations: [
              AppComponent,
              PlayerNameConfiguration,
              GameComponent,
              ShipsToPlaceComponent,
              PlayerBoardComponent,
              OpponentBoardComponent,
              RegisteredPlayersComponent,
              RoomConfiguration,
              WaitingOpponentRegisterComponent,
              WaitingOpponentFleetComponent,
              FleetPlacingComponent,
              PlacingBoardComponent,
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
              MatListModule,
              HttpClientModule,
              TranslateModule.forRoot({
                                        loader: {
                                          provide: TranslateLoader,
                                          useFactory: HttpLoaderFactory,
                                          deps: [HttpClient],
                                        },
                                      }),
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
              FirstRoundAsker,
              RoomListAsker,
              RoomsService,
              RoomCreateAsker,
              RoomJoinAsker,
              RoomsService,
              OpponentPresentAsker,
            ],
            bootstrap: [AppComponent],
          })
export class AppModule {

}
