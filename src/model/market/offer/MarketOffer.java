package model.market.offer;

import model.market.MarketUser;

public interface MarketOffer extends ViewableOffer {

  public void fulfillOffer(MarketUser otherParty, int finalPrice);

  public void cancelOffer();

  public boolean compatibleWithOffer(ViewableOffer other);

}
