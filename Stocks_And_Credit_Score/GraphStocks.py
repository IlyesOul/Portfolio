import matplotlib.pyplot as plt
import requests
import datetime


class GraphStocks:

    def __init__(self, loan_amount):
        self.loan_amount = loan_amount

    def print_list(self, li):
        for j in range(0, len(li) - 1):
            if j == (len(li) - 1):
                print(li[j], end='')
            else:
                print(f"{li[j]}, ", end='')

    def tounix(self, day, month, year):
        date2 = datetime.datetime(year, month, day)
        unix_timestamp = datetime.datetime.timestamp(date2)
        return str(int(unix_timestamp))

    def start_function(self):
        global len
        print("\nWelcome to the second half of this program")
        desc = int(input("\nWould you like to (0) graph a companies performance in a certain time interval \n,"
                         "or (1) determine if your portfolio is risky/pricey as per your loan?"))

        if desc == 0:
            print("You've selected graphing")
            comp = input("What company would you like to search for? Enter its NASDAQ sticker now:")
            year1 = int(input('What is the starting year?'))
            month1 = int(input('What is the starting month?'))
            day1 = int(input('What is the starting day of the month?'))

            year2 = int(input('\nWhat is the ending year?'))
            month2 = int(input('What is the ending month?'))
            day2 = int(input('What is the ending day of the month?'))

            period1 = self.tounix(day1, month1, year1)
            period2 = self.tounix(day2, month2, year2)
            interval = '1mo'
            includeAdjustedClose = 'false'

            url = f'https://query1.finance.yahoo.com/v8/finance/chart/{comp.lower()}?period1=' + period1 + '&period2=' + period2 + '&interval=' + interval + '&includeAdjustedClose=' + includeAdjustedClose
            r = requests.get(url, headers={'User-agent': 'Mozilla/5.0'})
            req_dict = r.json()

            closes, low, opens, high, dates, volumes = [], [], [], [], [], []
            for price in req_dict['chart']['result'][0]['indicators']['quote'][0]:
                for i in range(0, len(req_dict['chart']['result'][0]['indicators']['quote'][0][price])):
                    thisOption = req_dict['chart']['result'][0]['indicators']['quote'][0][price][i]
                    if price == "high":
                        high.append(thisOption)
                    elif price == "low":
                        low.append(thisOption)
                    elif price == "volume":
                        volumes.append(thisOption)
                    elif price == "close":
                        closes.append(thisOption)
                    elif price == "open":
                        opens.append(thisOption)

            for unix in req_dict['chart']['result'][0]['timestamp']:
                 date = datetime.datetime.fromtimestamp(unix)
                 dates.append(date)

            option = input("What would you like to plot? (close, high, volume, low, open)")

            if option == "high":
                yValues = high
            elif option == "low":
                 yValues = low
            elif option == "volume":
                 yValues = volumes
            elif option == "close":
                 yValues = closes
            elif option == "open":
                 yValues = opens


        # Styling and plotting the desired info
            fig, ax = plt.subplots()
            ax.plot(dates, yValues)

            ax.set_xlabel("Dates")
            ax.set_ylabel(f"Prices for {option} (If volume then in million)")
            fig.autofmt_xdate()
            ax.tick_params(axis='both', which='major', labelsize=16)
            plt.show()

        if desc == 1:
            # Beginning of advising

            portfolio = []
            riskyOrNot = []
            priceyOrNot = []
            len_comp = int(input("How many companies are you in interested in investing into?"))
            while len(portfolio) != len_comp:
                portfolio.append(input("Enter company that you're interested now (Ticker)").upper())

            cap = self.loan_amount
            risk = input("Do you prefer risky trades? (Yes or no)")
            isRisky = (risk.lower() == "yes")
            nToInvest = int(input("How many of these companies do you PREFERABLY want to invest in?"))

            stockToShare = {}
            # Get the amount of shares the user wants for each stock, check if the prices are adequate to invest
            for comp in portfolio:
                stockToShare[comp] = int(input(f"How many shares of {comp} would you like to buy?"))
                # Fetch information for the closing price of each company
                req_dict = requests.get(f'https://api.polygon.io/v2/aggs/ticker/{comp}/prev?adjusted=true&apiKey=VFSwKNWbH7pv7Yp98ayguccA6KVAJYjr').json()
                thisClose = req_dict['results'][0]
                if float(cap/thisClose*stockToShare[comp]) > cap/nToInvest:
                    priceyOrNot.append(comp)
                    portfolio.remove(comp)
            # Separate loop for risk factor: Needs a separate api call
            for comp in portfolio:
                req_dict = r = requests.get(
                    f'https://query1.finance.yahoo.com/v11/finance/quoteSummary/{comp.upper()}?modules=assetProfile').json()
                risk = req_dict['quoteSummary']['result'][0]['overallRisk']
                if risk > 4 and not isRisky or (risk <= 4 and isRisky):
                    portfolio.remove(comp)
                    riskyOrNot.append(comp)

            print("After some thorough analysis, we've concluded the following:")
            print(f"The most pricey stocks are {self.print_list(priceyOrNot)}")
            print(f"Based on your lack of, or desire for risk, and portfolio budget, we suggest you invest in {self.print_list(portfolio)}")
