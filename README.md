

# Backend Algorithm Simulation

This is a Java Spring Boot project that provides REST API endpoints for sorting algorithms. It uses Spring's Server-Sent Events (SSE) to stream the sorting process to the client.

## Prerequisites

- JDK 11 or later
- Maven
- IDE (e.g., IntelliJ IDEA, Eclipse)

## Getting Started

1. Clone the repository or download the zip file.
2. Import the project into your IDE as a Maven project.
3. Run the `SSEofAlgorithmSimulation` class to start the server.
4. Open the client-side application that consumes the endpoints.

## Endpoints

### Cycle Sort

Endpoint: `GET /cycleSort`

This endpoint takes an array of integers as a query parameter and returns the sorting process as a stream of SSE events.

#### Request Parameters

| Parameter | Description            | Type   |
| --------- | ---------------------- | ------ |
| arr       | The array to be sorted | string |

#### SSE Event Data

The SSE event data contains an array of integers representing the state of the array after each swap operation.

### Insertion Sort

Endpoint: `GET /insertionSort`

This endpoint takes an array of integers as a query parameter and returns the sorting process as a stream of SSE events.

#### Request Parameters

| Parameter | Description            | Type   |
| --------- | ---------------------- | ------ |
| arr       | The array to be sorted | string |

#### SSE Event Data

The SSE event data contains an array of integers representing the state of the array after each swap operation.

## Technologies Used

- Java
- Spring Boot
- Maven

## Acknowledgments

This project was created as part of a group project for a Software Engineering course at XYZ University. Special thanks to my teammates for their contributions.
