import axios from 'axios';
import { hostUrl } from '../config';
import {
  UPDATE_PLAYERS_LIST, UPDATE_ROOMS_LIST,
  SET_PLAYER_NAME, SET_ROOM_NAME
} from './actions.types';
import { history } from '../index';

export const registerPlayer = (playerName) => async (dispatch) => {
  const url = `${hostUrl}/login`;
  const payload = {
    name: playerName
  }
  try {
    const response = await axios.post(url, payload);
    if (response && response.data && response.data.result === true) {
      dispatch({ type: SET_PLAYER_NAME, payload: playerName });
      history.push('/lobby');
    }
  }
  catch (error) {
    console.log('registerPlayer ERROR:', error);
  }
}

export const getPlayers = () => async (dispatch) => {
  const url = `${hostUrl}/registered`;
  try {
    const response = await axios.get(url);
    dispatch({ type: UPDATE_PLAYERS_LIST, payload: response.data });
  }
  catch (error) {
    console.log(error);
  }
}

export const getRooms = () => async (dispatch) => {
  const url = `${hostUrl}/room/list`;
  try {
    const response = await axios.get(url);
    dispatch({ type: UPDATE_ROOMS_LIST, payload: response.data });
  }
  catch (error) {
    console.log(error);
  }
}

export const joinRoom = (playerName, roomName) => async (dispatch) => {
  const url = `${hostUrl}/room/join?playerName=${playerName}&roomName=${roomName}`;
  try {
    const response = await axios.get(url);
    // TODO handle response 
    // dispatch({ type: SET_ROOM_NAME, payload: response.data });
  }
  catch (error) {
    console.log(error);
  }
}

export const createRoom = (playerName, roomName) => async (dispatch) => {
  const url = `${hostUrl}/room/create?playerName=${playerName}`;
  const payload = {
    roomName
  };
  try {
    const response = await axios.post(url, payload);
    // TODO handle response 
  }
  catch (error) {
    console.log(error);
  }
}

export const sendShipsConfiguration = (playerName, roomName, shipsConfiguration) => async (dispatch) => {
  const url = `${hostUrl}/room/join?playerName=${playerName}&roomName=${roomName}`;
  const payload = shipsConfiguration;
  try {
    const response = await axios.post(url, payload);
    // TODO handle response 
  }
  catch (error) {
    console.log(error);
  }
}