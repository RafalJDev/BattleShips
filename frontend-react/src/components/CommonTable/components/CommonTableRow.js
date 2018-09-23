import React, { Component } from 'react';
import PropTypes from 'prop-types';
import classnames from 'classnames';

export default class CommonTableHeader extends Component {
  constructor(props) {
    super(props);

    this.handleRowClick = this.handleRowClick.bind(this);
  }

  handleRowClick() {
    this.props.handleRowClick(this.props.id);
  }

  renderCells() {
    const { columns, columnsOrder, rowData } = this.props;
    return columnsOrder.map((column) => {
      const { label, id, textAlign, columnWidth } = columns[column];
      const cellText = rowData[column];
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
          {cellText}
        </div>
      );
    });
  }

  render() {
    const { hoverable } = this.props;
    const rowClassNames = classnames(
      'common-table-row',
      { 'hoverable': hoverable }
    );
    return (
      <div className={rowClassNames} onClick={this.handleRowClick}>
        {this.renderCells()}
      </div>
    );
  }
}
CommonTableHeader.propTypes = {

}
