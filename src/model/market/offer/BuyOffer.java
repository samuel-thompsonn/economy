package model.market.offer;

import model.market.MarketUser;

public class BuyOffer extends EconOffer {

  public static final String BUY_OFFER_CODE = "Buy";

  public BuyOffer(String resourceType, int price, MarketUser owner) {
    super(resourceType, price, owner);
  }

  @Override
  public void doFulfillmentDelivery(MarketUser otherParty, int finalPrice) {
    //Note: this doesn't take on the responsibility of getting the resource FROM the seller.
    getOwner().giveResources(getResourceType(), DEFAULT_RESOURCE_AMOUNT);
    getOwner().giveMoney(getPrice() - finalPrice);
  }

  @Override
  public void cancelOffer() {
    getOwner().giveMoney(getPrice());
  }

  @Override
  public boolean compatibleWithOffer(ViewableOffer other) {
    return (!other.getOfferType().equals(getOfferType()) &&
            other.getResourceType().equals(getResourceType()) &&
            other.getPrice() <= getPrice());
  }

  @Override
  public String getOfferType() {
    return BUY_OFFER_CODE;
  }
}
