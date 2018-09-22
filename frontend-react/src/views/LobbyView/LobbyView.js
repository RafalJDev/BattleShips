import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import classnames from 'classnames';

import './LobbyView.scss';
import { getPlayers, getRooms } from '../../actions'


class LobbyView extends Component {
  constructor(props) {
    super(props);

    this.dataTimer = null;

    this.getData = this.getData.bind(this);
  }

  componentDidMount() {
    this.dataTimer = window.setInterval(this.getData, 2000);
    this.getData();
  }

  componentWillUnmount() {
    window.clearInterval(this.dataTimer);
  }

  getData() {
    const { getPlayers, getRooms } = this.props;
    getPlayers();
    getRooms();
  }

  render() {
    return (
      <div className='view lobby-view'>
        Lobby
      </div>
    );
  }
}

function mapStateToProps({ players, rooms }) {
  return { players, rooms };
}
function mapDispatchToProps(dispatch) {
  return bindActionCreators({ getPlayers, getRooms }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(LobbyView);