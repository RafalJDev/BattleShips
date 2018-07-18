import {Coordinate} from "../../models/domain/ship/coordinate/coordinate";
import {IndexVerification} from "./index-verification";

export class CoordinateVerifier {
  
  static isCoordinateNotInRange(coordinate: Coordinate): boolean {
    return !(this.isCoordinateInRange(coordinate));
  }
  
  static isCoordinateInRange(coordinate: Coordinate): boolean {
    return IndexVerification.isInRegularRange(coordinate.rowIndex) && IndexVerification.isInRegularRange(
      coordinate.columnIndex);
  }
}
