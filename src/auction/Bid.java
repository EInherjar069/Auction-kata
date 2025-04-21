package auction;

import participants.Bidder;

public class Bid {
    private final Bidder bidder;
    private final double amount;

    public Bid(Bidder bidder, double amount) {
        this.bidder = bidder;
        this.amount = amount;
    }

    public Bidder getBidder() {
        return bidder;
    }

    public double getAmount() {
        return amount;
    }
}
