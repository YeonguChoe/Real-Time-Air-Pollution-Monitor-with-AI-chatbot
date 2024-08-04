# Backend API

## Getting Pollutant information
### Request
- Use `Get` method on `/api/v1/pollutant` with the following query parameters
    - latitude: `Double`
    - longitude: `Double`
- Example
  ```url
  GET {url}/api/v1/pollutant?latitude=37.5665&longitude=126.9780
  ```
### Response
- Example
  ```json
  {
      "carbonMonoxide": 401.45,
      "nitrogenMonoxide": 12.64,
      "ozone": 20.97,
      "inhalableParticulateMatter": 31.07,
      "fineParticulateMatter": 27.22,
      "sulfurDioxide": 2.99
  }
  ```

## Getting reply from AI on environment
### Request
- Use `Get` method on `/api/v1/ai` with the following query parameters.
    - city: `String`
    - question: `String`
    - CO: `Double`
    - NO2: `Double`
    - O3: `Double`
    - PM10: `Double`
    - PM25: `Double`
    - SO2: `Double`
- Example
    ```url
    GET {url}/api/v1/ai?city=North York&question=Where is the the city located in Country?&CO=1.2&NO2=2.2&O3=1.1&PM10=1.1&PM25=1.1&SO2=1.1
    ```
### Response
- Example
  ```json
  {
      "reply": "North York is a district located in the city of Toronto, Ontario, Canada. It is situated in the northern part of Toronto and is known for its diverse communities, parks, and amenities. If you have specific questions about environmental issues or actions related to North York, feel free to ask!"
  }
  ```
