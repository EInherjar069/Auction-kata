### Requirements:
- Java 17+
- IntelliJ IDEA (preferably or any other ide)
- Maven (for dependencies)

### Compile and Run

- Open the project
- Right-click `Main.java` → **Run 'Main.main()'**

Or via terminal (from root folder):
```bash

javac -d out src/**/*.java
java -cp out Main

```
---

### How it works :

The program reads from a JSON file named bids.json (in src/main/resources):
```
{
  "reservePrice": 100,   // is reserved price of auction
  "bidders": [    // list of bidders
    {
      "name": "A",
      "bids": [110, 130, 150]
    },
    {
      "name": "B",
      "bids": []
    },
    {
      "name": "C",
      "bids": [120]
    },
    {
      "name": "D",
      "bids": [105, 115, 90]
    }
    , {
      "name": "E",
      "bids": [132, 135,140]
    }
  ]
}
```
---

#### Auction Rules

    1.One item is auctioned with a reserve price (minimum acceptable price).

    2.Each bidder can place multiple bids.

    3.The winner is the bidder with the highest bid ≥ reserve price.

    4.The winning price is:

        The highest bid by a non-winning bidder ≥ reserve price, or

         The reserve price if no such bid exists.


---

##### Tests

JUnit 5 tests are included in `src/test/`.
To run them :

Right-click VickreyAuctionTest.java → Run All Tests


or 

```bash
mvn test 
```


