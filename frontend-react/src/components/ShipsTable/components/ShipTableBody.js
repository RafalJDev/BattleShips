import React, { Component } from 'react';
import classnames from 'classnames';

const textArrayY = [
  'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'
]

const textArrayX = [
  '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
]

class ShipTableBody extends Component {
  handleCellClick = (rowId, cellId) => {
    console.log(rowId + cellId);
  }
  
  renderCells(rowId, rowData) {
    return textArrayX.map((cellId, index) => {
      const cellData = rowData[index];
      let cellProps = {
        key: `cell-${cellId}`
      };
      if (this.props.enemy) {
        cellProps = {
          onClick: () => this.handleCellClick(rowId, cellId)
        };
      }
      const cellClassName = classnames(
        'ships-table--body-cell',
        {'cell-hit': cellData === 'H'},
        {'cell-water': cellData === 'W'},
        {'cell-ship': cellData === 'S'},
        {'cell-miss': cellData === 'M'}
      );
      return (
        <div {...cellProps} className={cellClassName}>
          <div className='ships-table--body-cell-indicator'></div>
        </div>
      );
    });
  }

  renderRows() {
    return textArrayY.map((rowId, index) => {
      return (
        <div key={`row-${rowId}`} className='ships-table--body-row'>
          {this.renderCells(rowId, this.props.shipsData[index])}
        </div>
      );
    });
  }

  render() {
    const { player, enemy } = this.props;
    const bodyClassname = classnames(
      'ships-table--body',
      {player},
      {enemy}
    );

    return (
      <div className={bodyClassname}>
        {this.renderRows()}
      </div>
    );
  }
}

export default ShipTableBody;