const INITIAL_STATE = [
  ['W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'H', 'M'],
  ['M', 'S', 'S', 'S', 'S', 'W', 'W', 'M', 'H', 'W'],
  ['M', 'W', 'W', 'W', 'W', 'W', 'W', 'M', 'W', 'W'],
  ['W', 'M', 'W', 'M', 'W', 'S', 'W', 'W', 'W', 'W'],
  ['W', 'S', 'M', 'H', 'M', 'S', 'W', 'W', 'W', 'W'],
  ['W', 'S', 'M', 'H', 'W', 'W', 'W', 'W', 'M', 'W'],
  ['W', 'S', 'W', 'S', 'W', 'W', 'W', 'W', 'W', 'W'],
  ['W', 'W', 'W', 'W', 'W', 'W', 'W', 'S', 'S', 'W'],
  ['W', 'M', 'W', 'W', 'H', 'W', 'W', 'M', 'W', 'W'],
  ['M', 'W', 'S', 'W', 'W', 'W', 'W', 'M', 'M', 'W']
];

export default function(state = INITIAL_STATE, action) {
  const { type, payload } = action;
  switch(type) {
    default: 
      return state;
  }
} 