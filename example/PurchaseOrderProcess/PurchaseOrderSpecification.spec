
process Offer {

  place products( product: universe ) {
    register product;
    insert (product) into product;
  }

  place suppliers( supplier: universe) {
  	register supplier;
  	insert (supplier) into supplier;
  }

  place provides( supplier: universe, product: universe ) {
    register product;
    register supplier;
    insert (product) into product;
    insert (supplier) into supplier;
    insert (supplier, product) into supplies;
  }

  transition CreatePO( order: universe, product: universe) {
    register order;
    insert (order) into order;
    insert (order, product) into contains;
  }

  transition SendBid( supplier: universe, order: universe ) {
    insert (supplier, order) into bids;
  }

  transition TimeOut( supplier: universe, order: universe ) {
  }

  transition SelectOffer( supplier: universe, order: universe ) {
    insert (supplier, order) into receives;
  }

  transition RequestBid( supplier: universe, order: universe ) {
  }

  transition Deliver( supplier: universe, order: universe ) {
    insert (supplier, order) into delivers;
  }
}


