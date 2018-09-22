import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import rootReducer from './reducers';
import { Router, Route } from 'react-router-dom';
import createBrowserHistory from 'history/createBrowserHistory'

import './styles/style.scss';

import Header from './components/Header/Header';
import SocketExample from './components/SocketExample/SocketExample';
import HomeView from './views/HomeView/HomeView';
import LobbyView from './views/LobbyView/LobbyView';

export const history = createBrowserHistory();

const store = createStore(
  rootReducer,
  applyMiddleware(
    thunkMiddleware
  )
);

ReactDOM.render(
  <Provider store={store}>
    <Router history={history}>
      <div className='app-container'>
        <Header />
        <SocketExample />
        <main className='content-container'>
          <Route exact path="/" component={HomeView} />
          <Route path="/lobby" component={LobbyView} />
        </main>
      </div>
    </Router>
  </Provider>
  , document.getElementById('app')
);