# SQL Analysis: Online Store Sales Data

This project contains SQL queries for analyzing sales data from an online store. The analysis includes calculations of total sales, customer spending patterns, and average order values.

## Project Structure

```
sqlAnalysis/
├── sales_analysis.sql  # SQL queries for data analysis
└── README.md          # Project documentation
```

## Database Schema

### Orders Table
```sql
CREATE TABLE orders (
    id INTEGER PRIMARY KEY,
    customer TEXT,
    amount REAL,
    order_date DATE
);
```

## Sample Data

The analysis uses the following sample data:

| ID | Customer | Amount | Order Date  |
|----|----------|---------|------------|
| 1  | Alice    | 5000    | 2024-03-01 |
| 2  | Bob      | 8000    | 2024-03-05 |
| 3  | Alice    | 3000    | 2024-03-15 |
| 4  | Charlie  | 7000    | 2024-02-20 |
| 5  | Alice    | 10000   | 2024-02-28 |
| 6  | Bob      | 4000    | 2024-02-10 |
| 7  | Charlie  | 9000    | 2024-03-22 |
| 8  | Alice    | 2000    | 2024-03-30 |

## Analysis Queries

### 1. Total Sales for March 2024
```sql
SELECT 
    strftime('%Y-%m', order_date) as month,
    SUM(amount) as total_sales
FROM orders 
WHERE strftime('%Y-%m', order_date) = '2024-03'
GROUP BY strftime('%Y-%m', order_date);
```
**Expected Result:**
- Total sales for March: 27,000

### 2. Top Spending Customer
```sql
SELECT 
    customer,
    SUM(amount) as total_spent
FROM orders
GROUP BY customer
ORDER BY total_spent DESC
LIMIT 1;
```
**Expected Result:**
- Top customer: Alice with total spending of 20,000

### 3. Average Order Value
```sql
SELECT 
    ROUND(AVG(amount), 2) as average_order_value,
    ROUND(SUM(amount), 2) as total_sales,
    COUNT(*) as number_of_orders
FROM orders;
```
**Expected Result:**
- Average order value: 6,000

## Additional Analysis

### 4. Monthly Sales Breakdown
```sql
SELECT 
    strftime('%Y-%m', order_date) as month,
    COUNT(*) as number_of_orders,
    ROUND(SUM(amount), 2) as total_sales,
    ROUND(AVG(amount), 2) as average_order_value
FROM orders
GROUP BY strftime('%Y-%m', order_date)
ORDER BY month;
```

### 5. Customer Spending by Month
```sql
SELECT 
    customer,
    strftime('%Y-%m', order_date) as month,
    COUNT(*) as number_of_orders,
    ROUND(SUM(amount), 2) as total_spent
FROM orders
GROUP BY customer, strftime('%Y-%m', order_date)
ORDER BY customer, month;
```

### 6. Customers Ranked by Total Spending
```sql
SELECT 
    customer,
    COUNT(*) as number_of_orders,
    ROUND(SUM(amount), 2) as total_spent,
    ROUND(AVG(amount), 2) as average_order_value
FROM orders
GROUP BY customer
ORDER BY total_spent DESC;
```

## How to Use

1. Open [SQLite Online](https://sqliteonline.com/)
2. Copy and paste the contents of `sales_analysis.sql`
3. Execute the queries one by one to see the results
4. Each query is documented with comments explaining its purpose

## Query Features

- Use of SQLite's `strftime()` for date formatting
- Aggregation functions (`SUM`, `AVG`, `COUNT`)
- Grouping and sorting
- Date filtering
- Rounding of decimal values
- Multiple table joins (for future expansion)

## Results Analysis

1. **March 2024 Sales**
   - Total sales: 27,000
   - Number of orders: 4

2. **Customer Analysis**
   - Top customer: Alice (20,000 total)
   - Second: Charlie (16,000 total)
   - Third: Bob (12,000 total)

3. **Order Values**
   - Average order value: 6,000
   - Total orders: 8
   - Total sales: 48,000

## Future Enhancements

Potential additions to the analysis:
- Daily sales trends
- Customer segmentation
- Product category analysis (requires additional tables)
- Payment method analysis
- Seasonal trends
- Customer retention metrics
- Order frequency analysis
- Geographic analysis

## Notes

- All amounts are in a common currency unit
- Dates are in YYYY-MM-DD format
- The sample data spans February and March 2024

## License

This project is open source and available under the MIT License. 