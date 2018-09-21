import React, { Component } from 'react';

class ShipTableTopHeader extends Component {
  renderCells() {
    const textArray = [
      '', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
    ]

    return textArray.map((text) => {
      return (
        <div key={text} className='ships-table--top-header-cell'>
          {text}
        </div>
      );
    })
  }

  render() {
    return (
      <div className='ships-table--top-header'>
        {this.renderCells()}
      </div>
    );
  }
}

export default ShipTableTopHeader;