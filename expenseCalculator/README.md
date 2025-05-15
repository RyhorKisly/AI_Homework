# Expense Calculator

A simple and modern web application for tracking and analyzing monthly expenses. This application helps users manage their expenses by providing features to add, delete, and analyze spending patterns.

## Features

- Add new expenses with category and amount
- Delete existing expenses
- Calculate total monthly expenses
- Calculate average daily expenses
- Display top 3 largest expenses
- Responsive design for mobile and desktop
- Modern and clean user interface

## Screenshots

```
+-------------------------+
|   Expense Calculator    |
+-------------------------+
| Add New Expense        |
| [Category] [Amount] [+] |
+-------------------------+
| Expenses List          |
| Category  Amount  Action|
| ...       ...     [Del] |
+-------------------------+
| [Calculate]            |
+-------------------------+
| Total: $XXX           |
| Daily Avg: $XXX       |
| Top 3:                |
| 1. Category: $XXX     |
| 2. Category: $XXX     |
| 3. Category: $XXX     |
+-------------------------+
```

## Technologies Used

- HTML5
- CSS3 (with Flexbox and Grid)
- JavaScript (Vanilla)
- Google Fonts (Roboto)

## Project Structure

```
expenseCalculator/
├── index.html      # Main HTML structure
├── styles.css      # Styling and layout
├── script.js       # Application logic
└── README.md       # Project documentation
```

## How to Use

1. Open `index.html` in a web browser
2. Add expenses:
   - Enter the expense category (e.g., "Groceries")
   - Enter the amount in dollars
   - Click "Add Expense"
3. Delete expenses by clicking the "Delete" button next to any expense
4. Click "Calculate" to see:
   - Total expenses
   - Average daily expense (based on 30 days)
   - Top 3 largest expenses

## Sample Data

You can load sample data by opening the browser console and running:
```javascript
addSampleData()
```

This will populate the table with the following expenses:
- Rent: $40,000
- Groceries: $15,000
- Entertainment: $10,000
- Transportation: $5,000
- Gym: $3,000
- Communication: $2,000

## Features in Detail

### 1. Expense Management
- Add new expenses with category and amount
- Delete unwanted expenses
- View all expenses in a clean, tabulated format

### 2. Calculations
- Total amount of all expenses
- Average daily expense (total ÷ 30 days)
- Automatic sorting and display of top 3 expenses

### 3. User Interface
- Responsive design that works on both desktop and mobile devices
- Clean and modern interface with intuitive controls
- Clear presentation of results in cards
- Input validation to prevent invalid entries

## Browser Compatibility

The application is compatible with modern browsers including:
- Google Chrome
- Mozilla Firefox
- Safari
- Microsoft Edge

## Development

To modify the application:

1. Edit `index.html` for structure changes
2. Modify `styles.css` for styling updates
3. Update `script.js` for functionality changes

## Future Enhancements

Potential features that could be added:
- Data persistence using localStorage
- Export expenses to CSV
- Monthly expense comparison
- Expense categories with icons
- Budget setting and tracking
- Expense statistics and charts

## License

This project is open source and available for personal and commercial use. 