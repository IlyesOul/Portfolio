Welcome to my Credit Score Classification and Loan Giver!

This is really 2 programs in one, so I will describe their behaviors as so.

The first portion of the program deals with credit score classification using Machine Learning. Using an online dataset entailing credit score information, this program classifies people into 
"Good", "Bad", or "Standard" credit scores.
For classification, I used a Random Forest classifier. I fine-tuned its parameters to prevent overfitting and make robust predictions. 
The program accepts user input gathering the appropriate attributes to make a classification.
It will then ask the user what they want for a loan for investing, and based off their score a fraction (or whole) of the requested loan will be given.

The second half of the program entails graphing stock preformances and analyzes stock preformances.
An object of the second half is instantiated, and the loan amount orginally given is passed into it. 
The program then asks the user for a stock ticker, an interval of time, and a certain aspect (Opening price, closing price, low price, high price, volume of shares sold).
To fetch this information, it makes JSON requests to the Yahoo! Finance API.
It then graphs this information.

The next function of the behavior is a portfolio analysis.
Gathering some basic info from the user, the program will know the users prefered risk/volatillity in a stock. The budget for this portfolio is the orgininal loan amount from the first program.
Iterating through the portfolio, the program asks how many shares of the stock the user would like, then sees if its too expensive as per the budget (loan amount).
It remove any risky or expensive stocks from the portfolio.

Overall information: This program utilizes JSON API requests, dictionaries, user input, classes and objects, lists, Machine Learning, Random Forest Ensembles (Machine Learning), matplotlib, sklearn, and functions.

NOTE: This program heavily utilizes user input, so please make sure that no mismatches are given, time intervals are actually within legitimate boundaries (correct amount of days for months), and giving what the program requests for input.

Run the "Credit_Score_Classifer.py", as it includes the "GraphStocks.py" behavior
