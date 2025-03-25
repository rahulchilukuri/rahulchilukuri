# Portfolio Management

## Problem Statement
 - Build a Java REST application to analyze a portfolio of financial assets in a given currency code.
 - The portfolio contains three types of assets:
   - Stock
   - Crypto
   - Bond
 - Each asset type has different properties and risk factors.
 - The input will be provided as CSV files containing the 
   - portfolio holdings
   - current market data.

Application should:
1. Read the portfolio holdings and current market data from CSV files.
2. Convert all currency amounts to a display currency (user input) using an external API.
3. Calculate the summary of the portfolio by each asset type and individual holdings.
4. Calculate the profit/loss for each asset type and individual holdings.
5. Calculate the risk score for each individual holding based on specific logic for each asset type.
6. Identify the top and worst performing asset types and individual holdings.


## Requirements
1. Currency Conversion:
    - Use External Public API to get the latest exchange rates for Currency Conversion.
    - The application / API should support Currency code and display the result of calculation in user provided display currency.
    - External API URL https://api.exchangerate-api.com/v4/latest/${input_currency}

2. Portfolio Summary Calculation:
    - Calculate the total value and profit/loss for individual holdings ( `i.e. AAPL, MFST`), each asset type ( `ex Stock, Bond`) and overall portfolio level includes all asset type.
      - For Stock, include Dividend amount in the profit/loss calculation.
    - Identify the top and worst performing asset type.
    - Identify the top and worst performing individual holdings for each asset type.

3. Risk Score Calculation:
    - Calculate the risk score for each individual holding using the following logic:
        - **Crypto**: `Risk score = (Volatility * Quantity * Price) * 1`
        - **Stock**: `Risk score = (Dividend Yield * Quantity * Price) * 0.5`
        - **Bond**: `Risk score = (Remaining months to maturity * Quantity * Price) * 0.1`

## Data Files

Files are in CSV format. Files are attached in the `src/main/resources/data` folder.

1. Portfolio Holdings / Assets data:
    - **You can assume each ticker is unique across all asset types. IE AAPL ticker will not appear in both Stock & Crypto asset classes**
    - Contains the list of assets in the portfolio.

        | Column Name     | Column Details                                                          |
        |-----------------|-------------------------------------------------------------------------|
        | Type            | Asset Type. Possible Values are Stock, Bond, Crypto                     |
        | Name            | Asset Name. Example - Apple, Microsoft, BitCoin, US Treasury Bond       |
        | Ticker          | Asset's Ticker. Example -  AAPL, MSFT, BTC, UTWO                        |
        | Quantity        | Number of Assets purchased / available in Portfolio                     |
        | Date            | Asset Purchased data.                 |
        | Price           | Asset Purchase Price                                                    |

2. Current Market data for Assets:
    - Contains current market data for Assets.
    - These data would be used to calculate the current value of the portfolio.
    - These data captured at the end of the market close on the day.

        | Column Name | Column Details                                      |
        |-------------|-----------------------------------------------------|
        | Ticker      | Asset's Ticker. Example -  AAPL, MSFT, BTC, UTWO    |
        | Type        | Asset Type. Possible Values are Stock, Bond, Crypto |
        | Latest Closing Date       | Date of asset's data in the row                     |
        | Latest Closing Price    | Asset's Price on the end of the day                 |
        | Currency  | Asset's Price Currency code                         |
        | Latest Dividend Yield    | Dividend amount provided for Stock Asset type       |
        | Volatility    | Volatity data in number for Crypto Asset type       |
        | Maturity Date    | Bond Maturity date                                  |
      - Below fields applicable based on asset type and other asset type fields will be empty.
        - Stock Asset Type
            - Latest Dividend Yield
        - Crypto Asset Type
            - Volatility
        - Bond Asset Type
            - Maturity Date

3. Response / Result data:
    - Sample response data in below to provide the list of fields required. 
    - Values in the response is not expected value. Just for reference.
   ```json
        {
      "portfolioSummary": {
        "assetTypeSummary": {
          "stock": {
            "profitLoss": 5000,
            "topPerformingHolding": "AAPL",
            "worstPerformingHolding": "MSFT"
          },
          "crypto": {
            "profitLoss": -2000,
            "topPerformingHolding": "BTC",
            "worstPerformingHolding": "BTC"
          },
          "fixedIncome": {
            "profitLoss": 1000,
            "topPerformingHolding": "UTWO",
            "worstPerformingHolding": "UTWO"
          }
        },
        "overallSummary": {
          "topPerformingAssetType": "Stock",
          "worstPerformingAssetType": "Crypto",
          "totalProfitLoss": -1983.93,
          "totalPortfolioValue": 10217.07
        },
        "holdingSummary": [
          {
            "name": "AAPL",
            "quantity": 100,
            "profitLoss": 5000
          },
          {
            "name": "MSFT",
            "quantity": 200,
            "profitLoss": -2000
          },
          {
            "name": "BTC",
            "quantity": 1,
            "profitLoss": -2000
          },
          {
            "name": "UTWO",
            "quantity": 1000,
            "profitLoss": 1000
          }
        ]
      },
      "riskScore": {
        "AAPL": 0.625,
        "MSFT": 1.86,
        "BTC": 115.5,
        "UTWO": 0.96
      },
      "displayCurrency": "USD"
    }
   ```

## Assumptions
1. All date in the file is in the format ISO date format.
2. Use the latest exchange rate for currency conversion.
3. Use the latest market data for the calculation.
4. Required additional dependencies can be added in build.gradle
4. If anything is not clear, Please make assumptions and document them in the code.
