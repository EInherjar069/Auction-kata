# Vickrey Auction Simulator

This project simulates a sealed-bid **second-price (Vickrey) auction**. It allows multiple bidders, each submitting one or more bids, and determines the winner and final price according to the auction rules.

---

## Project Structure

- `src/` - Contains the Java source code
- `main/resources/` - Contains the input JSON file
- `test/` - Unit tests written with JUnit

---

## How to Run

### Option 1: Run with IntelliJ (preferably or your ide)

1. Build and run the `Main` class.
2. By default, it will load `input.json` from the `resources/` directory.

---

### Option 2: Run the JAR file from the command line

1. Open a terminal
2. Navigate to the directory containing `kata-auction.jar` file
3. Run : (where path/to/bids.json is the path to reach bids.json)

```bash
java -jar kata-auction.jar path/to/bids.json
```


 Json file format : 
```
{
"reservePrice": 200.0,
"bidders": [
{
"name": "Alice",
"bids": [150.0, 300.0]
},
{
"name": "Bob",
"bids": [250.0]
},
{
"name": "Charlie",
"bids": [180.0, 220.0]
}
]
}
```
#### Auction Rules Recap

The highest bid at or above the reserve price wins.

The winning price is the second-highest bid among other bidders, as long as it's at or above the reserve.

If no such bid exists, the reserve price is used as the final price.
