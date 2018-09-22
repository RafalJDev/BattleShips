import { SET_PLAYER_NAME } from '../actions/actions.types';

const INITIAL_STATE = '';
export default function (state = INITIAL_STATE, action) {
  const { type, payload } = action;
  switch (type) {
    case SET_PLAYER_NAME:
      return payload;
    default:
      return state;
  }
} 