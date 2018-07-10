package pl.krkteam.model;

import java.util.Arrays;

class Ship {

  public Coordinate[] coordinates;

  @Override
  public String toString() {
    return "Ship{" +
       "coordinates=" + Arrays.toString(coordinates) +
       '}';
  }
}