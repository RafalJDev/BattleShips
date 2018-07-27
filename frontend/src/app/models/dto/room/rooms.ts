import {RoomDTO} from "./room-DTO"

export class Rooms {
  
  roomArray: Array<RoomDTO>
  
  constructor(roomArray: Array<RoomDTO>) {
    this.roomArray = roomArray
  }
  
  static ofEmpty(): Rooms {
    return new Rooms([])
  }
  
  static ofRoomForTest(): Rooms {
    let rooms = this.ofEmpty()
    rooms.putRoomOfName("First RoomDTO")
    rooms.putRoomOfName("Second RoomDTO")
    return rooms
  }
  
  putRoom(room: RoomDTO) {
    this.roomArray.push(room)
  }
  
  putRoomOfName(roomName: string) {
    this.roomArray.push(new RoomDTO(roomName))
  }
  
  isRoomArrayNotEmpty(): boolean {
    return !this.isRoomArrayEmpty()
  }
  
  isRoomArrayEmpty(): boolean {
    return this.roomArray.length == 0
  }
}
