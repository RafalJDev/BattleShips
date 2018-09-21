import React, { Component } from 'react';

import './ShipsTable.scss';

import ShipTableTopHeader from './components/ShipTableTopHeader';
import ShipTableLeftHeader from './components/ShipTableLeftHeader';
import ShipTableBody from './components/ShipTableBody';

class ShipsTable extends Component {
  render() {
    const { shipsData, player, enemy } = this.props;
    const tableBodyProps = {
      shipsData, player, enemy
    }
    return (
      <div className='ships-table-component'>
        <ShipTableTopHeader />
        <div className='ships-table-container'>
          <ShipTableLeftHeader />
          <ShipTableBody {...tableBodyProps}/>
        </div>
      </div>
    );
  }
}

export default ShipsTable;