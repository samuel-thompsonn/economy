package model.market.offer;

import model.market.MarketUser;

public interface ViewableOffer {

  public String getResourceType();

  public String getOfferType();

  public int getPrice();

  public MarketUser getOwner();

  public boolean matchesOffer(ViewableOffer other);

  public void subscribe(OfferListener listener);
}
