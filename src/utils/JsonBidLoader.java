package utils;

import com.fasterxml.jackson.databind.ObjectMapper;

import auction.Auction;
import auction.Bid;
import participants.Bidder;

import java.io.InputStream;

public class JsonBidLoader {

    public static void loadFromJson(String resourceName, Auction auction) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = JsonBidLoader.class.getClassLoader().getResourceAsStream(resourceName);
            AuctionData data = mapper.readValue(is, AuctionData.class);

            // Set reserve price if your Auction class supports it via constructor
            if (auction instanceof auction.VickreyAuction vickreyAuction) {
                vickreyAuction.setReservePrice(data.reservePrice); // if setter available
            }

            for (AuctionData.BidderData bd : data.bidders) {
                Bidder bidder = new Bidder(bd.name);
                for (Double bidAmount : bd.bids) {
                    auction.submitBid(new Bid(bidder, bidAmount));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
