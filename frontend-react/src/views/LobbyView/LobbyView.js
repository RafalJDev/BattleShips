import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import classnames from 'classnames';

import './LobbyView.scss';
import { getPlayers, getRooms, joinRoom } from '../../actions';
import { history } from '../../index';

import CommonTable from '../../components/CommonTable/CommonTable';

class LobbyView extends Component {
  constructor(props) {
    super(props);

    this.dataTimer = null;

    this.getData = this.getData.bind(this);
    this.handleRoomRowClick = this.handleRoomRowClick.bind(this);
  }

  componentDidMount() {
    if (!this.props.playerName) {
      history.push('/');
      return;
    }

    this.dataTimer = window.setInterval(this.getData, 2000);
    this.getData();
  }

  componentWillUnmount() {
    window.clearInterval(this.dataTimer);
  }

  getData() {
    const { getPlayers, getRooms, rooms } = this.props;
    getPlayers();
    getRooms();
  }

  handleRoomRowClick(roomName) {
    const { playerName } = this.props;
    this.props.joinRoom(playerName, roomName);
  }

  renderCreateRoomForm() {
    return (
      <form className='form'>

      </form>
    );
  }

  renderRoomsTable() {
    const { rooms } = this.props;

    const columns = {
      roomName: {
        id: 'roomName',
        textAlign: 'LEFT',
        columnWidth: 350,
        label: 'Room name'
      },
      status: {
        id: 'status',
        textAlign: 'Center',
        columnWidth: 100,
        label: 'Status'
      },
      roomOwner: {
        id: 'roomOwner',
        textAlign: 'Center',
        columnWidth: 150,
        label: 'Room Owner'
      },
      playersAmount: {
        id: 'playersAmount',
        textAlign: 'Center',
        columnWidth: 100,
        label: 'Players'
      }
    }

    const columnsOrder = ['roomName', 'roomOwner', 'status', 'playersAmount'];

    const data = rooms.map((room) => {
      return {
        id: room.roomName,
        roomName: room.roomName,
        status: 'N/A',
        roomOwner: 'N/A',
        playersAmount: 'N/A'
      }
    });

    const roomsTableProps = {
      tableWidth: 700,
      columns,
      columnsOrder,
      data,
      handleRowClick: this.handleRoomRowClick,
      hoverable: true
    }

    return <CommonTable {...roomsTableProps} />;
  }

  renderPlayersTable() {
    const { players } = this.props;

    const columns = {
      playerName: {
        id: 'playerName',
        textAlign: 'LEFT',
        columnWidth: 350,
        label: 'Player name'
      },
      inRoom: {
        id: 'inRoom',
        textAlign: 'CENTER',
        columnWidth: 100,
        label: 'In room'
      }
    }

    const columnsOrder = ['playerName', 'inRoom'];

    const data = players.map((player) => {
      return {
        id: player.name,
        playerName: player.name,
        inRoom: 'N/A',
      }
    });

    const playersTableProps = {
      tableWidth: 450,
      columns,
      columnsOrder,
      data,
      handleRowClick: () => { }
    }

    return <CommonTable {...playersTableProps} />;
  }

  render() {
    return (
      <div className='view lobby-view'>
        <div className='lobby-container'>
          <div className='form-container'>
            {this.renderCreateRoomForm()}
          </div>
          <div className='data-container'>
            {this.renderRoomsTable()}
            {this.renderPlayersTable()}
          </div>
        </div>
      </div>
    );
  }
}

function mapStateToProps({ players, rooms, playerName }) {
  return { players, rooms, playerName };
}
function mapDispatchToProps(dispatch) {
  return bindActionCreators({ getPlayers, getRooms, joinRoom }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(LobbyView);