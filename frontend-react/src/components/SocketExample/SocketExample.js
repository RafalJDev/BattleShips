import React, { Component } from 'react';

class SocketExample extends Component {
  constructor(props) {
    super(props);

    this.socket = null;
  }

  static socketUrl = 'ws://localhost:8080'

  componentDidMount() {
    this.socket = new WebSocket(SocketExample.socketUrl);
    console.log(this.socket);
    this.socket.addEventListener('open', function (event) {
      console.log('Cennected!');
    });
    this.socket.addEventListener('message', function (event) {
      console.log('Message from server:', event.data);
    });
    this.socket.addEventListener('close', function (event) {
      console.log('Connection closed');
    });
    this.socket.addEventListener('error', function (event) {
      console.log('Connection error');
    });
    window.setTimeout(() => {
      this.socket.send('Hello Server!');
    }, 1000);
  }

  render() {
    return null;
  }
}

export default SocketExample;