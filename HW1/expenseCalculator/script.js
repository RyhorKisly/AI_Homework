// Store expenses in an array
let expenses = [];

// Function to add a new expense
function addExpense() {
    const category = document.getElementById('category').value;
    const amount = parseFloat(document.getElementById('amount').value);

    if (category && amount) {
        expenses.push({ category, amount });
        updateExpensesTable();
        clearForm();
    } else {
        alert('Please fill in both category and amount!');
    }
}

// Function to clear the form
function clearForm() {
    document.getElementById('category').value = '';
    document.getElementById('amount').value = '';
}

// Function to delete an expense
function deleteExpense(index) {
    expenses.splice(index, 1);
    updateExpensesTable();
}

// Function to update the expenses table
function updateExpensesTable() {
    const tbody = document.getElementById('expensesList');
    tbody.innerHTML = '';

    expenses.forEach((expense, index) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${expense.category}</td>
            <td>$${expense.amount.toLocaleString()}</td>
            <td>
                <button class="delete-btn" onclick="deleteExpense(${index})">Delete</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

// Function to calculate and display results
function calculateExpenses() {
    if (expenses.length === 0) {
        alert('Please add some expenses first!');
        return;
    }

    // Calculate total amount
    const totalAmount = expenses.reduce((sum, expense) => sum + expense.amount, 0);
    
    // Calculate average daily expense (assuming 30 days per month)
    const averageDaily = totalAmount / 30;
    
    // Get top 3 expenses
    const topExpenses = [...expenses]
        .sort((a, b) => b.amount - a.amount)
        .slice(0, 3);

    // Update the UI
    document.getElementById('totalAmount').textContent = `$${totalAmount.toLocaleString()}`;
    document.getElementById('averageExpense').textContent = `$${averageDaily.toLocaleString(undefined, {
        minimumFractionDigits: 2,
        maximumFractionDigits: 2
    })}`;

    const topExpensesList = document.getElementById('topExpenses');
    topExpensesList.innerHTML = topExpenses
        .map(expense => `<li>${expense.category}: $${expense.amount.toLocaleString()}</li>`)
        .join('');

    // If there are fewer than 3 expenses, fill with placeholder
    for (let i = topExpenses.length; i < 3; i++) {
        topExpensesList.innerHTML += '<li>-</li>';
    }
}

// Add sample data (optional)
function addSampleData() {
    const sampleData = [
        { category: 'Groceries', amount: 15000 },
        { category: 'Rent', amount: 40000 },
        { category: 'Transportation', amount: 5000 },
        { category: 'Entertainment', amount: 10000 },
        { category: 'Communication', amount: 2000 },
        { category: 'Gym', amount: 3000 }
    ];
    
    expenses = [...sampleData];
    updateExpensesTable();
} 