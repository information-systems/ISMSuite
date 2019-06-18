
process Offer {

	place products(p: product) {
		register p;
		insert (p) into product;
	}
	
	place provides(p: product, s: supplier) {
		register p;
		register s;
		insert (p) into product;
		insert (s) into supplier;
		insert (p, s) into provides;
	}
	
	transition CreatePO(r: purchaseRequest, p: product) {
		register r;
		insert (r) into purchaseRequest;
		insert (r,p) into for;
	}
	
	transition RequestBid(r: purchaseRequest, s: supplier) {
	}

	transition SendBid(r: purchaseRequest, s: supplier) {
		insert (r,s) into bid;
	}
	
	transition SelectOffer(r: purchaseRequest, s: supplier) {
		insert (r,s) into orders;
	}
	
	transition TimeOut(r: purchaseRequest, s: supplier) {
	}
	
	transition Deliver(r: purchaseRequest, s: supplier) {
		insert (r,s) into delivers;
	}
	
	
}