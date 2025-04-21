package utils;

import java.util.List;

public class AuctionData {
    public double reservePrice;
    public List<BidderData> bidders;

    public static class BidderData {
        public String name;
        public List<Double> bids;
    }
}
