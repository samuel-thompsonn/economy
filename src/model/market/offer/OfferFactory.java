package model.market.offer;

import model.market.MarketUser;

public class OfferFactory {

  public static final String BUY_CODE = "Buy";
  public static final String SELL_CODE = "Sell";

  public static MarketOffer createOffer(String type, String resourceType, int price, MarketUser owner) {
    if (type.equals(BUY_CODE)) {
      return new BuyOffer(resourceType,price,owner);
    }
    else if (type.equals(SELL_CODE)) {
      return new SellOffer(resourceType,price,owner);
    }
    return null;
  }
}
