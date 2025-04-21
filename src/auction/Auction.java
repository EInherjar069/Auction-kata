package auction;

public interface Auction {
    void submitBid(Bid bid);
    void startAuction();
    Bid getWinningBid();
    double getWinningPrice();
}
