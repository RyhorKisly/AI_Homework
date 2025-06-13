# API Testing: Fake Store Product Data Validator (Node.js)

A Node.js-based automated testing tool for validating product data from the Fake Store API (https://fakestoreapi.com/products). This tool identifies potential defects and anomalies in the product data.

## Features

- Validates server response code (expected 200)
- Checks product data attributes:
  - Title (must not be empty)
  - Price (must not be negative)
  - Rating (must not exceed 5)
- Generates detailed reports of found defects
- Saves test results in JSON format
- Provides console summary output
- Asynchronous operation using modern JavaScript features

## Project Structure

```
apiTestingFakeStore/
├── src/
│   └── apiTester.js    # Main testing script
├── package.json        # Project configuration and dependencies
└── README.md          # Project documentation
```

## Requirements

- Node.js 14.x or higher
- npm (Node Package Manager)

## Dependencies

- axios (HTTP client)
- jest (testing framework, dev dependency)

## Installation

1. Clone the repository
2. Navigate to the project directory:
   ```bash
   cd apiTestingFakeStore
   ```
3. Install dependencies:
   ```bash
   npm install
   ```

## Usage

Run the tests using npm:
```bash
npm start
```

Or directly with Node.js:
```bash
node src/apiTester.js
```

The application will:
1. Connect to the Fake Store API
2. Validate all products
3. Generate a test report
4. Save results to a JSON file
5. Display a summary in the console

## Test Results

The tool generates two types of output:

1. Console Summary:
   ```
   === API Test Results ===
   Server Response: OK
   Total Products: X
   Products with Defects: Y
   ```

2. Detailed JSON Report (`test_results_YYYY-MM-DDTHH-mm-ss-sssZ.json`):
   ```json
   {
     "timestamp": "2024-XX-XX...",
     "api_url": "https://fakestoreapi.com/products",
     "server_response": {
       "status": true,
       "message": ""
     },
     "data_validation": {
       "total_products": X,
       "products_with_defects": Y,
       "defects": [
         {
           "product_id": 1,
           "field": "price",
           "issue": "Negative price",
           "value": -10.99
         }
       ]
     }
   }
   ```

## Validation Rules

The tool checks for the following conditions:

1. Server Response:
   - Must return HTTP status code 200

2. Product Title:
   - Must not be empty
   - Must be a string

3. Product Price:
   - Must not be negative
   - Must be a number

4. Product Rating:
   - Must be between 0 and 5
   - Must be a number

## Error Handling

The tool handles various error scenarios:
- Network connection issues
- Invalid JSON responses
- Missing or malformed data fields
- Type mismatches in JSON data
- Promise rejections

## Development

The project uses modern JavaScript features including:
- ES6+ Classes
- Async/await
- Optional chaining
- Modern array methods
- Promise-based APIs

To extend the validator:

1. Add new validation rules in the `validateProduct` method
2. Update the `ProductDefect` class if new defect types are needed
3. Modify the report format in the `runTests` method

### Running Tests

```bash
npm test
```

## Future Enhancements

Potential improvements:
- Add more validation rules
- Add command-line arguments for configuration
- Generate HTML reports
- Add more unit tests
- Implement retry mechanism
- Add support for authentication
- Add support for different API endpoints
- Add rate limiting
- Implement caching
- Add performance metrics
- Support for custom validation rules
- WebSocket support for real-time validation

## License

This project is open source and available under the MIT License. 