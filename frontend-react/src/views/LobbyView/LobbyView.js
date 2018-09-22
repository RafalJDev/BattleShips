import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import classnames from 'classnames';

import './LobbyView.scss';
import { registerPlayer } from '../../actions'


class LobbyView extends Component {
  render() {
    return (
      <div className='view lobby-view'>
        Lobby
      </div>
    );
  }
}

function mapStateToProps({ }) {
  return {};
}
function mapDispatchToProps(dispatch) {
  return bindActionCreators({}, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(LobbyView);