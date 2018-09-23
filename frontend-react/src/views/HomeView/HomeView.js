import React, { Component } from 'react';
import { connect } from 'react-redux';
import { bindActionCreators } from 'redux';
import PropTypes from 'prop-types';
import classnames from 'classnames';

import './HomeView.scss';
import { registerPlayer } from '../../actions'

import CommonSingleForm from '../../components/CommonSingleForm/CommonSingleForm';

class HomeView extends Component {
  constructor(props) {
    super(props);

    this.handlePlayerRegister = this.handlePlayerRegister.bind(this);
  }

  static validateNameValue(value) {
    const regex = /^[a-zA-Z0-9]+([a-zA-Z0-9](_|-| )[a-zA-Z0-9])*[a-zA-Z0-9]*$/
    return regex.test(value)
  }

  handlePlayerRegister(playerName) {
    this.props.registerPlayer(playerName);
  }

  render() {
    const formProps = {
      handleSubmit: this.handlePlayerRegister,
      placeholder: 'Enter name...',
      autoFocus: 'on',
      validateMethod: HomeView.validateNameValue
    }
    return (
      <div className='view home-view'>
        <div className='home-view-form-container'>
          <CommonSingleForm {...formProps} />
        </div>
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