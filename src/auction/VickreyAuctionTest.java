package test;

import auction.Bid;
import auction.VickreyAuction;
import participants.Bidder;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VickreyAuctionTest {

    @Test
    void winnerPaysSecondHighestBid() {
        VickreyAuction auction = new VickreyAuction(150);
        Bidder alice = new Bidder("Alice");
        Bidder bob = new Bidder("Bob");

        auction.submitBid(new Bid(alice, 250));
        auction.submitBid(new Bid(bob, 180));
        auction.startAuction();

        assertEquals("Alice", auction.getWinningBid().getBidder().getName());
        assertEquals(180, auction.getWinningPrice());
    }

    @Test
    void winnerPaysReserveIfNoOtherBidderMetReserve() {
        VickreyAuction auction = new VickreyAuction(200);
        Bidder alice = new Bidder("Alice");
        Bidder bob = new Bidder("Bob");

        auction.submitBid(new Bid(alice, 250));
        auction.submitBid(new Bid(bob, 180)); // below reserve
        auction.startAuction();

        assertEquals("Alice", auction.getWinningBid().getBidder().getName());
        assertEquals(200, auction.getWinningPrice()); // falls back to reserve
    }

    @Test
    void noWinnerIfNoBidMeetsReserve() {
        VickreyAuction auction = new VickreyAuction(300);
        Bidder alice = new Bidder("Alice");
        Bidder bob = new Bidder("Bob");

        auction.submitBid(new Bid(alice, 250));
        auction.submitBid(new Bid(bob, 290));
        auction.startAuction();

        assertNull(auction.getWinningBid());
        assertEquals(0, auction.getWinningPrice());
    }

    @Test
    void correctBidSelectedWhenMultipleBidsPerBidder() {
        VickreyAuction auction = new VickreyAuction(150);
        Bidder alice = new Bidder("Alice");
        Bidder bob = new Bidder("Bob");

        auction.submitBid(new Bid(alice, 100));
        auction.submitBid(new Bid(alice, 250));
        auction.submitBid(new Bid(alice, 170));
        auction.submitBid(new Bid(bob, 160));
        auction.submitBid(new Bid(bob, 180));

        auction.startAuction();

        assertEquals("Alice", auction.getWinningBid().getBidder().getName());
        assertEquals(180, auction.getWinningPrice());
    }
}
