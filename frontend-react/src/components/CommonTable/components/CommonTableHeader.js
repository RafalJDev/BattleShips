import React, { Component } from 'react';
import PropTypes from 'prop-types';

export default class CommonTableHeader extends Component {
  constructor(props) {
    super(props);

  }

  renderCells() {
    const { columns, columnsOrder } = this.props;
    return columnsOrder.map((column) => {
      const { label, id, textAlign, columnWidth } = columns[column];
      const cellStyles = {
        minWidth: columnWidth,
        maxWidth: columnWidth,
        textAlign: textAlign.toLowerCase()
      }
      const cellProps = {
        key: id
      }
      return (
        <div className='common-table-cell' style={cellStyles} {...cellProps}>
          {label}
        </div>
      );
    });
  }

  render() {
    return (
      <div className='common-table-header'>
        {this.renderCells()}
      </div>
    );
  }
}
CommonTableHeader.propTypes = {
  columns: PropTypes.shape()
}
