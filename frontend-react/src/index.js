import React, { Component } from 'react';
import ReactDOM from 'react-dom';
import { Provider } from 'react-redux';
import { createStore, applyMiddleware } from 'redux';
import thunkMiddleware from 'redux-thunk';
import rootReducer from './reducers';
import { BrowserRouter as Router, Route } from 'react-router-dom';

import './styles/style.scss';

import Header from './components/Header/Header';
import SocketExample from './components/SocketExample/SocketExample';
import HomeView from './views/HomeView/HomeView';

const store = createStore(
  rootReducer,
  applyMiddleware(
    thunkMiddleware
  )
);

ReactDOM.render(
  <Provider store={store}>
    <Router>
      <div className='app-container'>
        <Header />
        <SocketExample />
        <main className='content-container'>
          <Route exact path="/" component={HomeView} />
        </main>
      </div>
    </Router>
  </Provider>
  , document.getElementById('app')
);