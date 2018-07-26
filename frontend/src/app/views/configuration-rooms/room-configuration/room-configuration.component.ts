import {Component, OnDestroy} from '@angular/core'
import {RoomListAsker} from "../../../rest/get/room-list-asker.service"

@Component({
             selector: 'app-room-configuration',
             templateUrl: './room-configuration.component.html',
             styleUrls: ['./room-configuration.component.css'],
           })
export class RoomConfiguration implements OnDestroy{
  
  rooms: Rooms
  
  askerInterval;
  
  constructor(public roomListAsker: RoomListAsker) {
    this.rooms = Rooms.ofEmpty()
  
    this.rooms = Rooms.ofRoomForTest()
  
    this.askForRoomListInInterval(roomListAsker)
  }
  
  ngOnDestroy() {
    clearInterval(this.askerInterval);
  }
  
  askForRoomListInInterval(roomListAsker: RoomListAsker) {
    this.askerInterval = setInterval(() => {
      console.log("Callback to get room list");
  
      this.rooms = Rooms.ofRoomForTest()
  
      clearInterval(this.askerInterval);
    }, 500);
  }
  
  isThereRooms(): boolean {
    return this.rooms.isRoomArrayNotEmpty()
  }
}



export class Rooms {
  
  roomArray: Array<Room>
  
  constructor(roomArray: Array<Room>) {
    this.roomArray = roomArray
  }
  
  static ofEmpty(): Rooms {
    return new Rooms([])
  }
  
  static ofRoomForTest(): Rooms {
    let rooms = this.ofEmpty()
    rooms.putRoomOfName("First Room")
    rooms.putRoomOfName("Second Room")
    return rooms
  }
  
  putRoom(room: Room) {
    this.roomArray.push(room)
  }
  
  putRoomOfName(roomName: string) {
    this.roomArray.push(new Room(roomName))
  }
  
  isRoomArrayNotEmpty(): boolean {
    return !this.isRoomArrayEmpty()
  }
  
  isRoomArrayEmpty(): boolean {
    return this.roomArray.length == 0
  }
}

export class Room {
  name: string
  
  constructor(name: string) {
    this.name = name
  }
}
