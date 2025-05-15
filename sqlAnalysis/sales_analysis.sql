-- Create the orders table
CREATE TABLE orders (
    id INTEGER PRIMARY KEY,
    customer TEXT,
    amount REAL,
    order_date DATE
);

-- Populate the table with sample data
INSERT INTO orders (customer, amount, order_date) VALUES
('Alice', 5000, '2024-03-01'),
('Bob', 8000, '2024-03-05'),
('Alice', 3000, '2024-03-15'),
('Charlie', 7000, '2024-02-20'),
('Alice', 10000, '2024-02-28'),
('Bob', 4000, '2024-02-10'),
('Charlie', 9000, '2024-03-22'),
('Alice', 2000, '2024-03-30');

-- Analysis Queries

-- 1. Calculate total sales volume for March 2024
SELECT 
    strftime('%Y-%m', order_date) as month,
    SUM(amount) as total_sales
FROM orders 
WHERE strftime('%Y-%m', order_date) = '2024-03'
GROUP BY strftime('%Y-%m', order_date);

-- 2. Find the customer who spent the most overall
SELECT 
    customer,
    SUM(amount) as total_spent
FROM orders
GROUP BY customer
ORDER BY total_spent DESC
LIMIT 1;

-- 3. Calculate the average order value for all orders
SELECT 
    ROUND(AVG(amount), 2) as average_order_value,
    ROUND(SUM(amount), 2) as total_sales,
    COUNT(*) as number_of_orders
FROM orders;

-- Additional useful queries

-- 4. Monthly sales breakdown
SELECT 
    strftime('%Y-%m', order_date) as month,
    COUNT(*) as number_of_orders,
    ROUND(SUM(amount), 2) as total_sales,
    ROUND(AVG(amount), 2) as average_order_value
FROM orders
GROUP BY strftime('%Y-%m', order_date)
ORDER BY month;

-- 5. Customer spending by month
SELECT 
    customer,
    strftime('%Y-%m', order_date) as month,
    COUNT(*) as number_of_orders,
    ROUND(SUM(amount), 2) as total_spent
FROM orders
GROUP BY customer, strftime('%Y-%m', order_date)
ORDER BY customer, month;

-- 6. Customers ranked by total spending
SELECT 
    customer,
    COUNT(*) as number_of_orders,
    ROUND(SUM(amount), 2) as total_spent,
    ROUND(AVG(amount), 2) as average_order_value
FROM orders
GROUP BY customer
ORDER BY total_spent DESC; 