import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';

import './Room.scss';

import ShipsTable from '../../components/ShipsTable/ShipsTable';

class RoomView extends Component {
  render() {
    const { playerShips, enemyShips } = this.props;
    return (
      <div className='view room-view'>
        <div className='room-ship-table'>
          Player
          <br /><br />
          <ShipsTable shipsData={playerShips} player />
        </div>
        <div className='room-ship-table'>
          Enemy
          <br /><br />
          <ShipsTable shipsData={enemyShips} enemy />
        </div>
      </div>
    );
  }
}

function mapStateToProps({ playerShips, enemyShips }) {
  return { playerShips, enemyShips };
}

export default connect(mapStateToProps)(RoomView);