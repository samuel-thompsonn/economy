package model.market.offer;

import model.market.MarketUser;

import java.util.ArrayList;
import java.util.List;

public abstract class EconOffer implements MarketOffer {

  public static final int DEFAULT_RESOURCE_AMOUNT = 1;

  private String myResourceType;
  private int myPrice;
  private MarketUser myOwner;
  private List<OfferListener> myListeners;

  public EconOffer(String resourceType, int price, MarketUser owner) {
    myListeners = new ArrayList<>();
    myResourceType = resourceType;
    myPrice = price;
    myOwner = owner;
  }

  @Override
  public String getResourceType() {
    return myResourceType;
  }

  @Override
  public int getPrice() {
    return myPrice;
  }

  @Override
  public MarketUser getOwner() {
    return myOwner;
  }

  @Override
  public boolean matchesOffer(ViewableOffer other) {
    return (other.getOwner() == (this.getOwner()) &&
            other.getOfferType().equals(this.getOfferType()) &&
            other.getPrice() == getPrice() &&
            other.getResourceType().equals(getResourceType()));
  }

  @Override
  public String toString() {
    return getOfferType() + " " + getResourceType() + " for " + getPrice();
  }

  protected abstract void doFulfillmentDelivery(MarketUser otherParty, int finalPrice);

  @Override
  public void fulfillOffer(MarketUser otherParty, int finalPrice) {
    myListeners.forEach(OfferListener::reactToOfferFulfilled);
    doFulfillmentDelivery(otherParty,finalPrice);
  }

  @Override
  public void subscribe(OfferListener listener) {
    myListeners.add(listener);
  }
}
