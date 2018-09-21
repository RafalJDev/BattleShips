const INITIAL_STATE = [
  ['W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'H', 'M'],
  ['M', 'W', 'W', 'W', 'W', 'W', 'W', 'M', 'H', 'W'],
  ['M', 'W', 'W', 'W', 'W', 'W', 'W', 'M', 'W', 'W'],
  ['W', 'M', 'W', 'M', 'W', 'W', 'W', 'W', 'W', 'W'],
  ['W', 'W', 'M', 'H', 'M', 'W', 'W', 'W', 'W', 'W'],
  ['W', 'W', 'M', 'H', 'W', 'W', 'W', 'W', 'M', 'W'],
  ['W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'],
  ['W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W'],
  ['W', 'M', 'W', 'W', 'H', 'W', 'W', 'M', 'W', 'W'],
  ['M', 'W', 'W', 'W', 'W', 'W', 'W', 'M', 'M', 'W']
];

export default function(state = INITIAL_STATE, action) {
  const { type, payload } = action;
  switch(type) {
    default: 
      return state;
  }
} 