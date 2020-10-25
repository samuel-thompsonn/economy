package model.market;

import model.market.offer.MarketOffer;
import model.market.offer.OfferFactory;
import model.market.offer.ViewableOffer;

import java.util.ArrayList;
import java.util.List;

public class EconMarket implements Market {

  private List<MarketOffer> myOffers;
  private List<MarketOffer> myOldOffers;
  private List<MarketListener> myListeners;

  public EconMarket() {
    myOffers = new ArrayList<>();
    //TODO: Implement offer history
    myOldOffers = new ArrayList<>();
    myListeners = new ArrayList<>();
  }

  @Override
  public void makeOffer(String type, String resourceType, int price, MarketUser owner) {
    MarketOffer newOffer = OfferFactory.createOffer(type,resourceType,price,owner);
    if (newOffer == null) {
      return;
    }
    addOffer(newOffer);
  }

  @Override
  public List<ViewableOffer> fetchOffers(MarketUser owner) {
    return new ArrayList(myOffers);
  }

  @Override
  public void subscribe(MarketListener listener) {
    myListeners.add(listener);
  }

  private void addOffer(MarketOffer newOffer) {
    System.out.println(newOffer.toString());
    //TODO: Find a name that portrays the fact that we are resolving the offer
    for (MarketOffer existingOffer : myOffers) {
      if (newOffer.compatibleWithOffer(existingOffer) && existingOffer.compatibleWithOffer(newOffer)) {
        int finalPrice = (existingOffer.getPrice() + newOffer.getPrice()) / 2;
        newOffer.fulfillOffer(existingOffer.getOwner(),finalPrice);
        existingOffer.fulfillOffer(newOffer.getOwner(),finalPrice);
        myOffers.remove(existingOffer);
        return;
      }
    }
    myOffers.add(newOffer);
    myListeners.forEach(marketListener -> {marketListener.reactToNewOffer(newOffer);});
  }
}
