package com.yolo.model;

public class RoomModel {
	private int roomID;
	private String roomName;
	private String roomType;
	
	public RoomModel() {
		
	}

	public RoomModel(int roomID, String roomName, String roomType) {
		super();
		this.roomID = roomID;
		this.roomName = roomName;
		this.roomType = roomType;
	}

	public RoomModel(String roomName, String roomType) {
		super();
		this.roomName = roomName;
		this.roomType = roomType;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
	
	
	
}
