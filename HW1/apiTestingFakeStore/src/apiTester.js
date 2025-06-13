const axios = require('axios');
const fs = require('fs').promises;
const path = require('path');

class ProductDefect {
    constructor(productId, field, issue, value) {
        this.productId = productId;
        this.field = field;
        this.issue = issue;
        this.value = value;
    }
}

class APITester {
    constructor(apiUrl) {
        this.apiUrl = apiUrl;
        this.defects = [];
    }

    async testServerResponse() {
        try {
            const response = await axios.get(this.apiUrl);
            return response.status === 200;
        } catch (error) {
            console.error('Error connecting to the server:', error.message);
            return false;
        }
    }

    validateProduct(product) {
        const productId = product.id || 'Unknown';

        // Check title
        if (!product.title || typeof product.title !== 'string' || product.title.trim() === '') {
            this.defects.push(new ProductDefect(
                productId,
                'title',
                'Empty or invalid title',
                product.title
            ));
        }

        // Check price
        if (typeof product.price !== 'number' || product.price < 0) {
            this.defects.push(new ProductDefect(
                productId,
                'price',
                'Negative or invalid price',
                product.price
            ));
        }

        // Check rating
        const rating = product.rating?.rate;
        if (typeof rating !== 'number' || rating < 0 || rating > 5) {
            this.defects.push(new ProductDefect(
                productId,
                'rating.rate',
                'Invalid rating (must be between 0 and 5)',
                rating
            ));
        }
    }

    async runTests() {
        const results = {
            timestamp: new Date().toISOString(),
            api_url: this.apiUrl,
            server_response: {
                status: false,
                message: ''
            },
            data_validation: {
                total_products: 0,
                products_with_defects: 0,
                defects: []
            }
        };

        // Test server response
        const responseOk = await this.testServerResponse();
        results.server_response.status = responseOk;
        
        if (!responseOk) {
            results.server_response.message = 'Failed to connect to the server';
            return results;
        }

        try {
            // Get and validate products
            const response = await axios.get(this.apiUrl);
            const products = response.data;

            results.data_validation.total_products = products.length;

            // Validate each product
            products.forEach(product => this.validateProduct(product));

            // Format defects for the report
            const productsWithDefects = new Set();
            
            this.defects.forEach(defect => {
                productsWithDefects.add(defect.productId);
                results.data_validation.defects.push({
                    product_id: defect.productId,
                    field: defect.field,
                    issue: defect.issue,
                    value: defect.value
                });
            });

            results.data_validation.products_with_defects = productsWithDefects.size;

        } catch (error) {
            results.server_response.message = `Error processing API response: ${error.message}`;
        }

        return results;
    }
}

async function main() {
    try {
        const tester = new APITester('https://fakestoreapi.com/products');
        const results = await tester.runTests();

        // Generate filename with timestamp
        const timestamp = new Date().toISOString().replace(/[:.]/g, '-');
        const filename = `test_results_${timestamp}.json`;

        // Save results to file
        await fs.writeFile(
            path.join(process.cwd(), filename),
            JSON.stringify(results, null, 2)
        );

        // Print summary to console
        console.log('\n=== API Test Results ===');
        console.log(`Server Response: ${results.server_response.status ? 'OK' : 'Failed'}`);
        console.log(`Total Products: ${results.data_validation.total_products}`);
        console.log(`Products with Defects: ${results.data_validation.products_with_defects}`);
        console.log(`\nDetailed results saved to: ${filename}`);

    } catch (error) {
        console.error('Error running tests:', error.message);
    }
}

// Run if called directly
if (require.main === module) {
    main();
}

module.exports = {
    APITester,
    ProductDefect
}; 