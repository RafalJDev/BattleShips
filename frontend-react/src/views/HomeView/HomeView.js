import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import classnames from 'classnames';

import './HomeView.scss';
import { registerPlayer } from '../../actions'


class HomeView extends Component {
  constructor(props) {
    super(props);
    this.state = {
      playerName: '',
      isPlayerNameValidated: true
    };

    this.handleNameChange = this.handleNameChange.bind(this);
    this.handleNameSubmit = this.handleNameSubmit.bind(this);
  }

  validatePlayerName(value) {
    const regex = /^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]*$/
    return regex.test(value)
  }

  handleNameChange(event) {
    const value = event.target.value
    this.setState({
      playerName: value,
      isPlayerNameValidated: this.validatePlayerName(value)
    });
  }

  handleNameSubmit(event) {
    event.preventDefault();
    this.setState((prevState) => {
      if (!this.validatePlayerName(prevState.playerName)) {
        return { isPlayerNameValidated: false }
      }
      this.props.registerPlayer(prevState.playerName);
      return null;
    });
  }

  render() {
    const inputClassName = classnames(
      'form-input',
      { 'error': !this.state.isPlayerNameValidated }
    );
    const buttonClassName = classnames(
      'form-button',
      { 'error': !this.state.isPlayerNameValidated }
    );

    return (
      <div className='view home-view'>
        <form onSubmit={this.handleNameSubmit} className='form'>
          <input
            className={inputClassName}
            type="text"
            autoComplete="off"
            autoFocus
            placeholder="Enter name..."
            value={this.state.playerName}
            onChange={this.handleNameChange}
          />
          <button className={buttonClassName}>
            Submit
          </button>
        </form>
      </div>
    );
  }
}

function mapStateToProps({ }) {
  return {};
}
function mapDispatchToProps(dispatch) {
  return bindActionCreators({ registerPlayer }, dispatch);
}

export default connect(mapStateToProps, mapDispatchToProps)(HomeView);