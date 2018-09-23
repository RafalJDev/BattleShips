import React, { Component } from 'react';
import PropTypes from 'prop-types';

import './CommonTable.scss';

import CommonTableHeader from './components/CommonTableHeader';
import CommonTableRow from './components/CommonTableRow';

export default class CommonTable extends Component {
  constructor(props) {
    super(props);

  }

  renderHeader() {
    const { columns, columnsOrder } = this.props;
    const headerProps = {
      columns,
      columnsOrder
    }
    return <CommonTableHeader {...headerProps} />
  }
  renderRows() {
    const { data, columns, columnsOrder, handleRowClick, hoverable } = this.props;
    return data.map((row) => {
      const { id } = row;
      const rowProps = {
        key: id,
        id,
        rowData: row,
        columns,
        columnsOrder,
        hoverable,
        handleRowClick
      }
      return <CommonTableRow {...rowProps} />
    });

  }

  render() {
    const { tableWidth } = this.props;
    const commontableStyles = {
      minWidth: tableWidth,
      maxWidth: tableWidth
    };
    return (
      <div className='common-table' style={commontableStyles}>
        <div className='common-table-container'>
          {this.renderHeader()}
          {this.renderRows()}
        </div>
      </div>
    );
  }
}
CommonTable.propTypes = {
  handleRowClick: PropTypes.func.isRequired,
  hoverable: PropTypes.bool,
  data: PropTypes.arrayOf(PropTypes.shape()).isRequired,
  columns: PropTypes.shape().isRequired,
  columnsOrder: PropTypes.arrayOf(PropTypes.string).isRequired,
  tableWidth: PropTypes.number.isRequired
}
