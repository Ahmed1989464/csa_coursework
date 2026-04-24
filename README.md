_An Overview of my API design_

The RESTful Smart Campus Sensor API was built using Java , Maven and JAX-RS. There is 3 main components of the project ;
Rooms , Sensors and Sensor Readings. Rooms stores name and capacity of a room , Sensors contains the type , status and current value of a sensor
and Sensor Readings stores previous readings of a specific sensor

The API uses versioned endpoints under - /api/v1

Data is stored in memory using HashMap and ArrayList

The API will allow the campus to manage rooms throught campus , register new sensors and keep them updated and search for sensors by type.


_Build and Run Instructions_

1. Download project from my public github repository
2. Open the project in IDE - using java 11 and Maven
3. Open pom.xml and download dependencies
4. Right click on project and press clean and build
5. Run class Smart_Campus_Coursework_1989464.java to start the server on http://localhost:8080/api/v1/
6. Press enter when done to close server

_Curl Commands_

1. Get API information , 2. Get all rooms , 3. Create a room , 4. Get all sensors  , 5. Filter sensors by type


curl http://localhost:8080/api/v1/

curl http://localhost:8080/api/v1/rooms

curl -X POST http://localhost:8080/api/v1/rooms -H "Content-Type: application/json" -d "{\"name\":\"Class1\",\"capacity\":50}"

curl http://localhost:8080/api/v1/sensors

curl http://localhost:8080/api/v1/sensors?type=CO2

_Answers to questions for each part of the coursework_

1. The default lifecycle of JAX-RS resource classes are request-scoped , this creates a new object for an incoming request. If data was stored in the class itself
   data would be lost everytime a an incoming request was recieved as a new object was created.
   In my project I store data using HashMap and ArrayList inside DataStore.java to prevent this

2.Hypermedia within the RESTful API allows the client to move through the API using links given by the server. If the endpoints are changed later on , the client is able to follow new links instead of
  changing hard coded URLs. This also benefits the developers as they do not need to rely on static documentation as the API can show what can be accessed next.

3.When returning a list of rooms , returning only room IDs over the full room objects uses less network bandwidth as the response would be smaller. Although returning the
 full room object will take up more bandwidth , sometimes a user may not be able to tell what the room is using its given ID so it will request a human readable name.

4. In my project the DELETE operation is idempotent as sending the request multiple times results in the same final state. If the room exists , the first delete request will delete the room and any
   other reqests on the same room will return a 404 Room not found error response.

5. In my project we used this so that the API only accepts JSON request bodies. If a client sends data in a different format , the API will not accept it.
   JAX-RS will reject the request and return a 415 unsupported media type error response

6.@QueryParam is superior as the request asks for the sensors list with an added filter. If the filtering is done by URL it will loke for a different resource instead of looking
  through the sensor list.

7. Using a Sub-Resoure Locator pattern keeps the API organised and helpes with readability and maintainability. In my project SensorResource handles sensors and SensorReadingResource
   handles readings for a specific sensor. This seprates the different forms of logic carried out by the API making it easier to navigate rather than having it in one large class.

8.HTTP 422 is more accurate because the request itself is valid and the endpoint exists, but one piece of data inside the JSON body is incorrect. 
  In my project, the client can send a valid POST request to create a sensor, but the roomId may not exist. A 404 status is usually used when the URL or endpoint itself cannot be found.

9. This can be a security risk as information on how the server is built can be exposed. They can see sensitive information that can help them to find errors in the code
    and allow them to hack into the API. In my project the API uses a global exception mapper that returns a 500 error message instead of exposing sensitive information

10.Using JAX-RS filters for logging is better as logging code is written once and applies to everything.
