package main;

import auction.*;
import utils.JsonBidLoader;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {

        Auction auction = new VickreyAuction();


        if (args.length > 0) {
            // load from external file for jar artifact when project is built
            Path inputPath = Paths.get(args[0]);
            JsonBidLoader.loadFromFile(inputPath,auction);
        } else {
            // getting data from bids.json file in resources and injecting it into the auction
            JsonBidLoader.loadFromJson("bids.json",auction);
        }

        // starting an auction
        auction.startAuction();

        // getting results needed from the auction
        Bid winningBid = auction.getWinningBid();
        double price = auction.getWinningPrice();

        if (winningBid != null) {

            System.out.println("Winner: " + winningBid.getBidder().getName()+" (With the highest bid of "+winningBid.getAmount()+")");

            System.out.println("Winning price " + price);
        } else {

            System.out.println("No winner determined.");
        }
    }
}
