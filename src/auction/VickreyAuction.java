package auction;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class VickreyAuction implements Auction {
    private final List<Bid> bids = new ArrayList<>();
    private Bid winningBid;
    private double winningPrice;
    private double reservePrice;

    public VickreyAuction(double reservePrice) {
        this.reservePrice = reservePrice;
    }

    // make constructor optional by setting its default value as 0
    public VickreyAuction() {
        this(0.0);
    }

    @Override
    public void submitBid(Bid bid) {
        bids.add(bid);
    }

    // function that handles auction logic.
    @Override
    public void startAuction() {

        // must have at least 2 bids to start an auction
        if (bids.size() < 2) {
            System.out.println("Not enough bids to start the auction.");
            return;
        }
        // Find all bids that are valid to the auction , as in their amount is equal or higher than the reservePrice and sort them desc.
        List<Bid> validBids = bids.stream()
                .filter(b -> b.getAmount() >= reservePrice)
                .sorted(Comparator.comparingDouble(Bid::getAmount).reversed())
                .toList();

        // Check if there is any valid bid
        if (validBids.isEmpty()) {
            winningBid = null;
            winningPrice = 0;
            return;
        }

        // first on the list is winning bid.
        winningBid = validBids.getFirst();

        // finding the winning price by finding the second-highest bid made by another bidder
        Optional<Bid> secondHighest = validBids.stream()
                .filter(b -> !b.getBidder().equals(winningBid.getBidder()))
                .findFirst();

        // get winning price.
        winningPrice = secondHighest.map(Bid::getAmount).orElseGet(() -> reservePrice);

    }

    @Override
    public Bid getWinningBid() {
        return winningBid;
    }

    @Override
    public double getWinningPrice() {
        return winningPrice;
    }

    public void setReservePrice(double reservePrice) {
        this.reservePrice=reservePrice;
    }
}
