package pl.krkteam.model;

import java.util.Arrays;

public class Ships {

  public Ship[] shipArray;

  @Override
  public String toString() {
    return "Ships{" +
       "ships=" + Arrays.toString(shipArray) +
       '}';
  }
}



