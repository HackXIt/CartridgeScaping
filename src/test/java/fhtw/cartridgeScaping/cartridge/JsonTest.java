package fhtw.cartridgeScaping.cartridge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import fhtw.cartridgeScaping.gameplay.GameObject;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Door;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.text.DoorDescription;
import fhtw.cartridgeScaping.gameplay.text.ItemDescription;
import fhtw.cartridgeScaping.gameplay.text.RoomDescription;
import fhtw.cartridgeScaping.gameplay.util.Direction;
import fhtw.cartridgeScaping.json.Json;
import javafx.util.Pair;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class JsonTest {

    @Test
    void parse() {
    }

    @Test
    void fromJson() {
    }

    @Test
    void toJson() throws JsonProcessingException {
        Room startingRoom = new Room(new RoomDescription(
                "Starting room",
                "A room to start.",
                "It is rough to start, but this room definitely is a start."
        ));
        Room roomA = new Room(new RoomDescription(
                "Room A",
                "Another Room called A.",
                "Just another room, let's not be creative and assign it the letter A."
        ));
        Room roomB = new Room(new RoomDescription(
                "Room B",
                "Another Room called B.",
                "Just another room, let's not be creative and assign it the letter B."
        ));
        Room roomC = new Room(new RoomDescription(
                "Room C",
                "Another Room called C.",
                "Just another room, let's not be creative and assign it the letter C."
        ));
        Room exit = new Room(new RoomDescription(
                "The exit",
                "The room that leads outside.",
                "Feel free to roam the world, once you go through the exit."
        ));
        startingRoom.addDirection(Direction.NORTH, roomA);
        startingRoom.addDirection(Direction.EAST, roomB);
        roomB.addDirection(Direction.NORTH, roomC);
        Door steelDoor = new Door(new DoorDescription(
                "Steel door",
                "A steel door blocks your way.",
                "Blocking your way is a chunky steel door, irresistable to force.",
                "The steel door is locked from the other side.",
                "The steel door is locked."
        ), true);
        steelDoor.setRooms(roomC, exit);
        Item testKit = new Item(
                new ItemDescription(
                        "A test-kit",
                        "A corona test-kit.",
                        "A test-kit for PCR testing against the corona disease.",
                        "The expiration date on the test-kit is not overdue and the kit is ready to use.",
                        "A test-kit is laying packaged on the floor.",
                        "You're holding a packaged corona test-kit."
                )
        );
        testKit.setCanBeHeld(true);
        startingRoom.addItem(testKit);
        Cartridge cart = new Cartridge(
                "test-cartridge.json",
                "Test Cartridge",
                4,4
        );
        cart.addRoom(0, 0, startingRoom);
        cart.addRoom(0, 1, roomA);
        cart.addRoom(1, 0, roomB);
        cart.addRoom(1,1, roomC);
        cart.addRoom(1, 2, exit);
        cart.addItem(testKit);
        cart.addDoor(steelDoor);
        JsonNode node = Json.getInstance().toJson(cart);
        System.out.println(Json.getInstance().prettyStringify(node));
    }

    @Test
    void stringify() {
    }

    @Test
    void prettyStringify() {
    }
}