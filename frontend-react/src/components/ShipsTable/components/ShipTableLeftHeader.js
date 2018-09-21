import React, { Component } from 'react';

class ShipTableTopHeader extends Component {
  renderCells() {
    const textArray = [
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'
    ]

    return textArray.map((text) => {
      return (
        <div key={text} className='ships-table--left-header-cell'>
          {text}
        </div>
      );
    })
  }

  render() {
    return (
      <div className='ships-table--left-header'>
        {this.renderCells()}
      </div>
    );
  }
}

export default ShipTableTopHeader;