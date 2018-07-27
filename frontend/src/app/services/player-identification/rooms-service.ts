import {Injectable} from "@angular/core"
import {Observable} from "rxjs/Observable"
import {of} from "rxjs/observable/of"
import {RoomListAsker} from "../../rest/get/room-list-asker.service"
import {RoomDTO} from "../../models/dto/room/room-DTO"

@Injectable()
export class RoomsService {
  
  room: RoomDTO
  
  constructor(public roomListAsker: RoomListAsker) {
  }
  
  public getRooms(): Observable<RoomDTO[]> {
    let rooms: RoomDTO[]
    rooms = []
    this.roomListAsker.getRoomList()
        .then(response => {
          console.log("T1")
          let i = 0
          while (response['roomDTOS'][i] !== undefined) {
            rooms.push(new RoomDTO(response['roomDTOS'][i]['roomName']))
            i++
          }
          console.log("T2 rooms: " + rooms.forEach(value =>
                                                     console.log(" value: " + value)))
        })
    return of(rooms)
  }
}
