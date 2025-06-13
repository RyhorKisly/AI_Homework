# AI Homework Prompts

This document contains the prompts and tasks that were used to generate the solutions in this repository.

## 1. Web Application: Expense Calculator

### Goal
Develop a web application to calculate the main indicators of monthly expenses based on a user's list of expenses.

### Tools Required
- CursorAI for generating HTML/JS code
- CodePen / JSFiddle for testing

### Input Format
The user enters a list of their expenses in the form of a table:

| Category       | Amount ($) |
|---------------|------------|
| Groceries     | 15,000     |
| Rent          | 40,000     |
| Transportation| 5,000      |
| Entertainment | 10,000     |
| Communication | 2,000      |
| Gym           | 3,000      |

### Required Functionality
- Adding new expenses to the list
- Calculating the total amount of expenses (example: 75,000 $)
- Calculating the average daily expense (75,000 / 30 ≈ 2,500 $)
- Displaying the top 3 largest expenses (Rent (40,000), Groceries (15,000), Entertainment (10,000))

### Expected Result
After entering data and clicking the "Calculate" button, display:
- Total amount of expenses
- Average daily expense
- Top 3 expenses

## 2. API Testing: Identifying Defects in Product Data

### Goal
Develop automated tests to validate data provided by a public API to detect errors and anomalies.

### Tools Required
- CursorAI for generating test scenarios or ChatGPT
- ReqBin (reqbin.com) or Postman for executing API requests

### API Details
- Endpoint: https://fakestoreapi.com/products (mock store)

### Test Objectives
1. Verify server response code (expected 200)
2. Confirm the presence of the following attributes for each product:
   - `title` (name) - must not be empty
   - `price` (price) - must not be negative
   - `rating.rate` - must not exceed 5
3. Generate a list of products containing defects

## 3. SQL Queries: Analyzing a Database Online

### Goal
Write SQL queries to analyze sales data for an online store.

### Tools Required
- SQLite Online
- CursorAI/ChatGPT to generate and refine SQL queries

### Input Data
```sql
CREATE TABLE orders (
    id INTEGER PRIMARY KEY,
    customer TEXT,
    amount REAL,
    order_date DATE
);

INSERT INTO orders (customer, amount, order_date) VALUES
('Alice', 5000, '2024-03-01'),
('Bob', 8000, '2024-03-05'),
('Alice', 3000, '2024-03-15'),
('Charlie', 7000, '2024-02-20'),
('Alice', 10000, '2024-02-28'),
('Bob', 4000, '2024-02-10'),
('Charlie', 9000, '2024-03-22'),
('Alice', 2000, '2024-03-30');
```

### Tasks
1. Calculate the total sales volume for March 2024
2. Find the customer who spent the most overall
3. Calculate the average order value for the last three months

### Expected Results
- Total sales for March: 27,000
- Top-spending customer: Alice (20,000)
- Average order value (total sales / number of orders): 6,000

## Project Structure

The solutions for these prompts are organized in separate directories:

```
AI_Homework/
├── expenseCalculator/     # Solution for Task 1
├── apiTestingFakeStore/   # Solution for Task 2
└── sqlAnalysis/          # Solution for Task 3
```

Each directory contains:
- Source code files
- Documentation (README.md)
- Any necessary configuration files
- Test files (where applicable)

## Implementation Notes

1. **Expense Calculator**
   - Implemented as a web application
   - Uses HTML, CSS, and JavaScript
   - Modern and responsive design
   - Real-time calculations

2. **API Testing**
   - Implemented in multiple languages (Python, Java, JavaScript)
   - Automated testing suite
   - Detailed error reporting
   - JSON output format

3. **SQL Analysis**
   - Complete SQL queries
   - Documentation with expected results
   - Additional analysis queries
   - Performance considerations

## Usage

Each solution can be found in its respective directory with detailed instructions in the corresponding README.md files. 