import { combineReducers } from 'redux';

import playerShipsReducer from './playerShips.reducer';
import enemyShipsReducer from './enemyShips.reducer';
import playersReducer from './players.reducer';
import roomsReducer from './rooms.reducer';

const rootReducer = combineReducers({
  playerShips: playerShipsReducer,
  enemyShips: enemyShipsReducer,
  player: playersReducer,
  rooms: roomsReducer
});

export default rootReducer;