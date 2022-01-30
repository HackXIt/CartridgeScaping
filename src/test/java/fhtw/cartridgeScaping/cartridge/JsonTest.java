package fhtw.cartridgeScaping.cartridge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import fhtw.cartridgeScaping.gameplay.items.Item;
import fhtw.cartridgeScaping.gameplay.rooms.Door;
import fhtw.cartridgeScaping.gameplay.rooms.Room;
import fhtw.cartridgeScaping.gameplay.text.DoorDescription;
import fhtw.cartridgeScaping.gameplay.text.ItemDescription;
import fhtw.cartridgeScaping.gameplay.text.RoomDescription;
import fhtw.cartridgeScaping.gameplay.util.Direction;
import fhtw.cartridgeScaping.json.Json;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {
    private static Cartridge cartridge;
    private String jsonParseTest = "{\n" +
            "  \"filename\" : \"test-cartridge.json\",\n" +
            "  \"gameTitle\" : \"Test Cartridge\",\n" +
            "  \"spawn\" : {\n" +
            "    \"X\" : 0,\n" +
            "    \"Y\" : 0\n" +
            "  },\n" +
            "  \"map\" : {\n" +
            "    \"771105389\" : {\n" +
            "      \"X\" : 1,\n" +
            "      \"Y\" : 2\n" +
            "    },\n" +
            "    \"85415531\" : {\n" +
            "      \"X\" : 0,\n" +
            "      \"Y\" : 1\n" +
            "    },\n" +
            "    \"768185844\" : {\n" +
            "      \"X\" : 0,\n" +
            "      \"Y\" : 0\n" +
            "    },\n" +
            "    \"1689730682\" : {\n" +
            "      \"X\" : 1,\n" +
            "      \"Y\" : 0\n" +
            "    },\n" +
            "    \"87060781\" : {\n" +
            "      \"X\" : 1,\n" +
            "      \"Y\" : 1\n" +
            "    },\n" +
            "    \"size\" : {\n" +
            "      \"sizeX\" : 1,\n" +
            "      \"sizeY\" : 2\n" +
            "    }\n" +
            "  },\n" +
            "  \"items\" : {\n" +
            "    \"709133385\" : {\n" +
            "      \"objectTypeReference\" : \"fhtw.cartridgeScaping.gameplay.items.Item\",\n" +
            "      \"originalID\" : null,\n" +
            "      \"itemDescription\" : {\n" +
            "        \"name\" : \"A test-kit\",\n" +
            "        \"shortDescription\" : \"A corona test-kit.\",\n" +
            "        \"longDescription\" : \"A test-kit for PCR testing against the corona disease.\",\n" +
            "        \"detailedDescription\" : \"The expiration date on the test-kit is not overdue and the kit is ready to use.\",\n" +
            "        \"placedDescription\" : \"A test-kit is laying packaged on the floor.\",\n" +
            "        \"inventoryDescription\" : \"You're holding a packaged corona test-kit.\"\n" +
            "      },\n" +
            "      \"name\" : \"A test-kit\",\n" +
            "      \"canBeHeld\" : true\n" +
            "    }\n" +
            "  },\n" +
            "  \"rooms\" : {\n" +
            "    \"771105389\" : {\n" +
            "      \"objectTypeReference\" : \"fhtw.cartridgeScaping.gameplay.rooms.Room\",\n" +
            "      \"originalID\" : null,\n" +
            "      \"roomDescription\" : {\n" +
            "        \"name\" : \"The exit\",\n" +
            "        \"shortDescription\" : \"The room that leads outside.\",\n" +
            "        \"longDescription\" : \"Feel free to roam the world, once you go through the exit.\",\n" +
            "        \"detailedDescription\" : null\n" +
            "      },\n" +
            "      \"doors\" : [ 579294521 ],\n" +
            "      \"directions\" : { },\n" +
            "      \"items\" : [ ],\n" +
            "      \"location\" : {\n" +
            "        \"key\" : 1,\n" +
            "        \"value\" : 2\n" +
            "      },\n" +
            "      \"name\" : \"The exit\",\n" +
            "      \"canBeHeld\" : false\n" +
            "    },\n" +
            "    \"85415531\" : {\n" +
            "      \"objectTypeReference\" : \"fhtw.cartridgeScaping.gameplay.rooms.Room\",\n" +
            "      \"originalID\" : null,\n" +
            "      \"roomDescription\" : {\n" +
            "        \"name\" : \"Room A\",\n" +
            "        \"shortDescription\" : \"Another Room called A.\",\n" +
            "        \"longDescription\" : \"Just another room, let's not be creative and assign it the letter A.\",\n" +
            "        \"detailedDescription\" : null\n" +
            "      },\n" +
            "      \"doors\" : [ ],\n" +
            "      \"directions\" : {\n" +
            "        \"SOUTH\" : 768185844\n" +
            "      },\n" +
            "      \"items\" : [ ],\n" +
            "      \"location\" : {\n" +
            "        \"key\" : 0,\n" +
            "        \"value\" : 1\n" +
            "      },\n" +
            "      \"name\" : \"Room A\",\n" +
            "      \"canBeHeld\" : false\n" +
            "    },\n" +
            "    \"768185844\" : {\n" +
            "      \"objectTypeReference\" : \"fhtw.cartridgeScaping.gameplay.rooms.Room\",\n" +
            "      \"originalID\" : null,\n" +
            "      \"roomDescription\" : {\n" +
            "        \"name\" : \"Starting room\",\n" +
            "        \"shortDescription\" : \"A room to start.\",\n" +
            "        \"longDescription\" : \"It is rough to start, but this room definitely is a start.\",\n" +
            "        \"detailedDescription\" : null\n" +
            "      },\n" +
            "      \"doors\" : [ ],\n" +
            "      \"directions\" : {\n" +
            "        \"NORTH\" : 85415531,\n" +
            "        \"EAST\" : 1689730682\n" +
            "      },\n" +
            "      \"items\" : [ 709133385 ],\n" +
            "      \"location\" : {\n" +
            "        \"key\" : 0,\n" +
            "        \"value\" : 0\n" +
            "      },\n" +
            "      \"name\" : \"Starting room\",\n" +
            "      \"canBeHeld\" : false\n" +
            "    },\n" +
            "    \"1689730682\" : {\n" +
            "      \"objectTypeReference\" : \"fhtw.cartridgeScaping.gameplay.rooms.Room\",\n" +
            "      \"originalID\" : null,\n" +
            "      \"roomDescription\" : {\n" +
            "        \"name\" : \"Room B\",\n" +
            "        \"shortDescription\" : \"Another Room called B.\",\n" +
            "        \"longDescription\" : \"Just another room, let's not be creative and assign it the letter B.\",\n" +
            "        \"detailedDescription\" : null\n" +
            "      },\n" +
            "      \"doors\" : [ ],\n" +
            "      \"directions\" : {\n" +
            "        \"NORTH\" : 87060781,\n" +
            "        \"WEST\" : 768185844\n" +
            "      },\n" +
            "      \"items\" : [ ],\n" +
            "      \"location\" : {\n" +
            "        \"key\" : 1,\n" +
            "        \"value\" : 0\n" +
            "      },\n" +
            "      \"name\" : \"Room B\",\n" +
            "      \"canBeHeld\" : false\n" +
            "    },\n" +
            "    \"87060781\" : {\n" +
            "      \"objectTypeReference\" : \"fhtw.cartridgeScaping.gameplay.rooms.Room\",\n" +
            "      \"originalID\" : null,\n" +
            "      \"roomDescription\" : {\n" +
            "        \"name\" : \"Room C\",\n" +
            "        \"shortDescription\" : \"Another Room called C.\",\n" +
            "        \"longDescription\" : \"Just another room, let's not be creative and assign it the letter C.\",\n" +
            "        \"detailedDescription\" : null\n" +
            "      },\n" +
            "      \"doors\" : [ 579294521 ],\n" +
            "      \"directions\" : {\n" +
            "        \"SOUTH\" : 1689730682\n" +
            "      },\n" +
            "      \"items\" : [ ],\n" +
            "      \"location\" : {\n" +
            "        \"key\" : 1,\n" +
            "        \"value\" : 1\n" +
            "      },\n" +
            "      \"name\" : \"Room C\",\n" +
            "      \"canBeHeld\" : false\n" +
            "    }\n" +
            "  },\n" +
            "  \"doors\" : {\n" +
            "    \"579294521\" : {\n" +
            "      \"doorDescription\" : {\n" +
            "        \"name\" : \"Steel door\",\n" +
            "        \"shortDescription\" : \"A steel door blocks your way.\",\n" +
            "        \"longDescription\" : \"Blocking your way is a chunky steel door, irresistible to force.\",\n" +
            "        \"detailedDescription\" : null,\n" +
            "        \"insideDescription\" : \"The steel door is locked from the other side.\",\n" +
            "        \"outsideDescription\" : \"The steel door is locked.\",\n" +
            "        \"inside\" : false\n" +
            "      },\n" +
            "      \"source\" : 87060781,\n" +
            "      \"destination\" : 771105389,\n" +
            "      \"isLocked\" : true,\n" +
            "      \"isOpen\" : false\n" +
            "    }\n" +
            "  },\n" +
            "  \"secrets\" : { }\n" +
            "}";

    @BeforeAll
    static void setupCartridge() {
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
                "Blocking your way is a chunky steel door, irresistible to force.",
                "The steel door is locked from the other side.",
                "The steel door is locked."
        ), true);
        steelDoor.setRooms(roomC, exit);
        Item testKit = new Item(true,
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
        cartridge = new Cartridge(
                "test-cartridge.json",
                "Test Cartridge",
                4,4
        );
        cartridge.addRoom(0, 0, startingRoom);
        cartridge.addRoom(0, 1, roomA);
        cartridge.addRoom(1, 0, roomB);
        cartridge.addRoom(1,1, roomC);
        cartridge.addRoom(1, 2, exit);
        cartridge.addItem(testKit);
        cartridge.addDoor(steelDoor);
    }

    @Test
    void parse() throws IOException {
        JsonNode node = Json.getInstance().parse(jsonParseTest);
        System.out.println(Json.getInstance().prettyStringify(node));
    }

    @Test
    void fromJson() throws IOException {
        String json1 = Json.getInstance().toJson(cartridge).toPrettyString();
        JsonNode node = Json.getInstance().parse(json1);
        Cartridge cart = Json.getInstance().fromJson(node, Cartridge.class);
        String json2 = Json.getInstance().toJson(cart).toPrettyString();
        assertEquals(json1, json2);
    }

    @Test
    void toJson() throws JsonProcessingException {
        JsonNode node = Json.getInstance().toJson(cartridge);
        System.out.println(Json.getInstance().prettyStringify(node));
    }

    @Test
    void stringify() {
    }

    @Test
    void prettyStringify() {
    }
}