## Problem Statement
Design and Implement a split wise system which allows us to add users,
add expenses, show expenses, etc.

## Commands
### Add user
```
ADD_USER <userId> <userName>

example:
ADD_USER u1 Paritosh

output:
user added successfully
```

### Add expense
There are two types of expenses: EXACT, EQUAL (split type)
```
Equal expense command:
EXPENSE <paidBy> <amount> <numParticipants> <participant1> <participant2> ... EQUAL

output:
expense added successfully

Exact expense command:
EXPENSE <paidBy> <amount> <numParticipants> <participant1> <participant2> ... EXACT <share1> <share2> ... 

output:
expense added successfully
```

### Show all balances
```
SHOW

example output:
u2 owes u1: 120
u3 owes u2: 200
u3 owes u4: 150

output format is like:
<debtor> owes <creditor>: <amount>
```

### Show balances for one user
```
SHOW <userId>

ex: SHOW u2

example output (whereever u2 is involved):
u2 owes u1: 120
u3 owes u2: 200
u2 owes u4: 300

output format is like:
<debtor> owes <creditor>: <amount>
```


## Project structure
```
splitwise/
│
├── Main.java
│
├── controller/
│   └── ExpenseController.java
│
├── service/
│   ├── ExpenseService.java
│   ├── BalanceService.java
│   └── UserService.java
│
├── model/
│   ├── User.java
│   ├── Expense.java
│   ├── Split.java
│   └── Balance.java
│
├── strategy/
│   ├── SplitStrategy.java
│   ├── EqualSplitStrategy.java
│   ├── ExactSplitStrategy.java
│   └── PercentSplitStrategy.java
│
├── factory/
│   └── SplitStrategyFactory.java
│
├── repository/
│   ├── UserRepository.java
│   └── BalanceRepository.java
│
├── exception/
│   ├── SplitwiseException.java
│   └── InvalidSplitException.java
│
├── enums/
│   └── SplitTypeEnum.java
```

### This is not yet best.