import { UPDATE_ROOMS_LIST } from '../actions/actions.types';

const INITIAL_STATE = [];
export default function (state = INITIAL_STATE, action) {
  const { type, payload } = action;
  switch (type) {
    case UPDATE_ROOMS_LIST:
      return [...payload.roomDTOS]
    default:
      return state;
  }
} 