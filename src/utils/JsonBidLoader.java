package utils;

import auction.VickreyAuction;
import com.fasterxml.jackson.databind.ObjectMapper;

import auction.Auction;
import auction.Bid;
import participants.Bidder;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class JsonBidLoader {

    public static void loadFromFile(Path path, Auction auction) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            AuctionData data = mapper.readValue(Files.newBufferedReader(path), AuctionData.class);

            if (auction instanceof VickreyAuction vickreyAuction) {
                vickreyAuction.setReservePrice(data.reservePrice);
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

    public static void loadFromJson(String resourceName, Auction auction) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            InputStream is = JsonBidLoader.class.getClassLoader().getResourceAsStream(resourceName);
            AuctionData data = mapper.readValue(is, AuctionData.class);
            if (auction instanceof auction.VickreyAuction vickreyAuction) {
                vickreyAuction.setReservePrice(data.reservePrice);
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
