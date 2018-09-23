import React, { Component } from 'react';
import PropTypes from 'prop-types';
import classnames from 'classnames';

import './CommonSingleForm.scss';

export default class CommonSingleForm extends Component {
  constructor(props) {
    super(props);
    this.state = {
      value: '',
      isValueValidated: true
    };

    this.handleValueChange = this.handleValueChange.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }

  validateValue(value) {
    return this.props.validateMethod(value)
  }

  handleValueChange(event) {
    const value = event.target.value
    this.setState({
      value,
      isValueValidated: this.validateValue(value)
    });
  }

  handleSubmit(event) {
    event.preventDefault();
    this.setState((prevState) => {
      if (!this.validateValue(prevState.value)) {
        return { isValueValidated: false }
      }
      this.props.handleSubmit(prevState.value);
      return null;
    });
  }

  render() {
    const { placeholder, autoFocus } = this.props;

    const inputClassName = classnames(
      'common-single-form-input',
      { 'error': !this.state.isValueValidated }
    );
    const buttonClassName = classnames(
      'common-single-form-button',
      { 'error': !this.state.isValueValidated }
    );

    return (
      <form onSubmit={this.handleSubmit} className='common-single-form'>
        <input
          className={inputClassName}
          type="text"
          autoComplete={autoFocus}
          autoFocus
          placeholder={placeholder}
          value={this.state.value}
          onChange={this.handleValueChange}
        />
        <button className={buttonClassName}>
          Submit
      </button>
      </form>
    );
  }
}
CommonSingleForm.propTypes = {
  placeholder: PropTypes.string,
  autoFocus: PropTypes.oneOf(['off', 'on']),
  handleSubmit: PropTypes.func.isRequired,
  validateMethod: PropTypes.func
}
CommonSingleForm.defaultProps = {
  placeholder: '',
  autoFocus: 'off',
  validateMethod: () => true
}
