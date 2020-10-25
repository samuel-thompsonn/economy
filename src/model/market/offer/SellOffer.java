package model.market.offer;

import model.market.MarketUser;
import model.market.offer.EconOffer;
import model.market.offer.ViewableOffer;

public class SellOffer extends EconOffer {

  public static final String SELL_OFFER_CODE = "Sell";

  public SellOffer(String resourceType, int price, MarketUser owner) {
    super(resourceType, price, owner);
  }

  @Override
  public void doFulfillmentDelivery(MarketUser otherParty, int finalPrice) {
    getOwner().giveMoney(finalPrice);
    otherParty.giveResources(getResourceType(),DEFAULT_RESOURCE_AMOUNT);
  }

  @Override
  public void cancelOffer() {
    getOwner().giveResources(getResourceType(),DEFAULT_RESOURCE_AMOUNT);
  }

  @Override
  public boolean compatibleWithOffer(ViewableOffer other) {
    return (!other.getOfferType().equals(getOfferType()) &&
            other.getResourceType().equals(getResourceType()) &&
            other.getPrice() >= getPrice());
  }

  @Override
  public String getOfferType() {
    return SELL_OFFER_CODE;
  }
}
