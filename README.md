# Backend Algorithm Simulation

This repository contains the code for a backend algorithm simulation application. The application provides APIs for streaming the step-by-step execution of various sorting algorithms using Server-Sent Events (SSE).

## Technologies Used

- Java
- Spring Boot

## Installation and Setup
1. Clone the repository:

   ```bash
   git clone https://github.com/rahulnayanegali/backend-algos-simulation.git
   ```

2. Open the project in your preferred IDE.

3. Build the project using Maven or your IDE's build tools.

4. Run the `SSEofAlgorithmSimulation` class located in the `com.groupone.backendalgo` package.

5. The application will start running on `http://localhost:8080`.

## Available APIs

### Cycle Sort

Endpoint: `/cycleSort`

#### Request

- Method: `GET`
- Query Parameter: `arr` (comma-separated integer array)

#### Response

The response will be streamed as SSE events, where each event will contain an updated array representing a step in the sorting process. The last event will contain the string `"completed"` to indicate the completion of sorting.

### Insertion Sort

Endpoint: `/insertionSort`

#### Request

- Method: `GET`
- Query Parameter: `arr` (comma-separated integer array)

#### Response

The response will be streamed as SSE events, where each event will contain an updated array representing a step in the sorting process. The last event will contain the string `"completed"` to indicate the completion of sorting.

### Selection Sort

Endpoint: `/selectionSort`

#### Request

- Method: `GET`
- Query Parameter: `arr` (comma-separated integer array)

#### Response

The response will be streamed as SSE events, where each event will contain an updated array representing a step in the sorting process. The last event will contain the string `"completed"` to indicate the completion of sorting.

### Bubble Sort

Endpoint: `/bubbleSort`

#### Request

- Method: `GET`
- Query Parameter: `arr` (comma-separated integer array)

#### Response

The response will be streamed as SSE events, where each event will contain an updated array representing a step in the sorting process. The last event will contain the string `"completed"` to indicate the completion of sorting.

### Quick Sort

Endpoint: `/quickSort`

#### Request

- Method: `GET`
- Query Parameter: `arr` (comma-separated integer array)

#### Response

The response will be streamed as SSE events, where each event will contain an updated array representing a step in the sorting process. The last event will contain the string `"completed"` to indicate the completion of sorting.

## Usage

1. Make a GET request to one of the sorting endpoints mentioned above.
2. Pass the input array as the `arr` query parameter.
3. The response will be streamed as SSE events, allowing you to observe the step-by-step execution of the sorting algorithm.

Feel free to explore and experiment with different arrays and algorithms!
