import { UPDATE_PLAYERS_LIST } from '../actions/actions.types';

const INITIAL_STATE = [];
export default function (state = INITIAL_STATE, action) {
  const { type, payload } = action;
  switch (type) {
    case UPDATE_PLAYERS_LIST:
      return [...paylaod];
    default:
      return state;
  }
} 