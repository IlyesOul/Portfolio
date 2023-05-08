import GraphStocks
import sklearn
from sklearn.ensemble import RandomForestClassifier
import pandas as pd
import numpy as np


data = pd.read_csv("train.csv")

data["Credit_Mix"] = data["Credit_Mix"].map({"Standard": 1,
                               "Good": 2,
                               "Bad": 0})

x = np.array(data[["Annual_Income", "Monthly_Inhand_Salary",
                   "Num_Bank_Accounts", "Num_Credit_Card",
                   "Interest_Rate", "Num_of_Loan",
                   "Delay_from_due_date", "Num_of_Delayed_Payment",
                   "Credit_Mix", "Outstanding_Debt",
                   "Credit_History_Age", "Monthly_Balance"]])
y = np.array(data["Credit_Score"])

x_train, x_test, y_train, y_test = sklearn.model_selection.train_test_split(x, y, random_state=0)
# Fine-tuning model to prevent overfitting and get robust classifications
forest = RandomForestClassifier(max_features=10)


forest.fit(x_train, y_train)

# END OF TRAINING


print("Welcome to the Loan-To-Invest 9000! \n")
print("You will input several features to determine your credit-score, and based off that"
      "a loan will be given to you. \nWith that amount of money you will be given a budget "
      "to invest in, \nchoose some stocks, be able to graph their performances, and get a "
      "thorough analysis of your portfolio.\n")

# Collecting attributes ued to classify
print("\nIn order to begin our classification for your credit score, we need some basic information:\n")
income = float(input("Annual Income: "))
cash_salary = float(input("Monthly Inhand Salary: "))
num_of_bank_acc = float(input("Number of Bank Accounts: "))
num_cards = float(input("Number of Credit cards: "))
rate = float(input("Interest rate: "))
num_loans = float(input("Number of Loans: "))
days_delayed = float(input("Average number of days delayed by the person: "))
num_payments = float(input("Number of delayed payments: "))
credit = input("Credit Mix (Bad: 0, Standard: 1, Good: 3) : ")
debt = float(input("Outstanding Debt: "))
age = float(input("Credit History Age: "))
bal = float(input("Monthly Balance: "))

# Feeding information to model
result = forest.predict([[income, cash_salary, num_of_bank_acc,
                          num_cards, rate, num_loans, days_delayed, num_payments, credit, debt, age, bal]])

loan = int(input("\nNow that we have your credit score, how large do you want your loan\n"
                 "for investing to be?"))

# Gives the loan based off the credit score of the user
if result[0] == "Poor":
    print("\n Your credit score was low, so your loan will be a third of your requested amount:", loan*3)
    loan *= .3
elif result[0] == "Standard":
    print("\n Your credit score was standard, so your loan will be a three fourths of your requested amount:", loan * 3)
    loan *= .75
else:
    print("\n Your credit score was good, so your loan will be your requested amount:", loan)
    loan *= 1

# Instantiate class for graphing a stock, and passing in loan amount
graph_and_analyze = GraphStocks
graph_and_analyze.GraphStocks(loan).start_function()
