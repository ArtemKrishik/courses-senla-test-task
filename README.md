#Senla Courses [ATM]
This application simulates the operations of an ATM.  
The user must enter a valid card number that matches the pattern and the correct pin code to access the account.
After successful authorization, the user can:
- check the balance of the card
- withdraw funds from the account, not more than the amount on the current account or the limit of funds at the ATM is not exceeded
- top up the balance (the top up amount should not exceed 1,000,000)
###Implemented requirements:
- data storage is in a json file
- the card number is checked against the template: "XXXX-XXXX-XXXX-XXXX"
- the program saves its state to json file after completion
- high-quality error handling with logging to a file
- console menu
- card blocking after three incorrect PIN code entered