import {Component, OnDestroy, OnInit} from '@angular/core'
import {RoomsService} from "../../../services/player-identification/rooms-service"
import {RoomCreateAsker} from "../../../rest/post/room-create-asker.service"
import {RoomDTO} from "../../../models/dto/room/room-DTO"
import {Rooms} from "../../../models/dto/room/rooms"
import {RoomJoinAsker} from "../../../rest/get/room-join-asker"
import {Router} from "@angular/router"

@Component({
             selector: 'app-room-configuration',
             templateUrl: './room-configuration.component.html',
             styleUrls: ['./room-configuration.component.css'],
           })
export class RoomConfiguration implements OnDestroy, OnInit {
  
  rooms: Rooms
  
  roomToCreate: RoomDTO
  
  responseRoomCreated: boolean = false
  responseJoinedToRoom: boolean = false
  
  askerInterval
  
  constructor(public roomsService: RoomsService, private createRoom: RoomCreateAsker,
              private roomJoinAsker: RoomJoinAsker, private router: Router) {
    this.rooms = Rooms.ofRoomForTest()
    this.roomToCreate = new RoomDTO("")
    
    this.askForRoomListInConstructor()
  }
  
  ngOnDestroy() {
    clearInterval(this.askerInterval)
  }
  
  ngOnInit(): void {
    this.askerInterval =
      setInterval(() =>
                    this.roomsService.getRooms()
                        .subscribe(roomArr => {
                          this.rooms.roomArray = roomArr
                        }), 2000)
  }
  
  tryCreateRoom() {
    let roomToCreateJson = JSON.stringify(this.roomToCreate)
    
    this.createRoom.postCreateRoom(roomToCreateJson)
        .then(roomCreateResponse => {
          this.responseRoomCreated = this.createRoom.responseToBoolean(roomCreateResponse)
          console.log("isRoomCreated: " + this.responseRoomCreated)
          
          if (this.responseRoomCreated) {
            this.roomsService.room = this.roomToCreate
            this.router.navigate(['/waiting/opponent/register'])
          }
        })
  }
  
  joinThatRoom(roomName: string) {
    this.roomsService.room = new RoomDTO(roomName)
    
    this.roomJoinAsker.getCanPlayerJoinRoom()
        .then(joinResponse => {
      
          this.responseJoinedToRoom = this.roomJoinAsker.responseToBoolean(joinResponse)
      
          if (this.responseJoinedToRoom) {
            this.router.navigate(['/game/fleet/placing'])
          }
        })
  }
  
  askForRoomListInConstructor() {
    this.roomsService.getRooms()
        .subscribe(roomArr => {
          this.rooms.roomArray = roomArr
        })
  }
  
}
