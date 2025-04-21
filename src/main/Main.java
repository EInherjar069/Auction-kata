package main;

import auction.*;
import utils.JsonBidLoader;

public class Main {
    public static void main(String[] args) {
        Auction auction = new VickreyAuction();

        JsonBidLoader.loadFromJson("bids.json", auction);


        auction.startAuction();
        Bid winningBid = auction.getWinningBid();

        double price = auction.getWinningPrice();

        if (winningBid != null) {

            System.out.println("Winner: " + winningBid.getBidder().getName()+"(With the highest bid of "+winningBid.getAmount());

            System.out.println("Winning price " + price);
        } else {

            System.out.println("No winner determined.");
        }
    }
}
