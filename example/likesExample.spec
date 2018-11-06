
process Likes {

	place persons(p: person) {
		register p;
		insert (p) into human;
	}

	transition addHuman(p: person) {
		register p;
		insert (p) into human;
	}

	transition addLikes(p1: person, p2: person) {
		insert (p1, p2) into likes;
	}

	transition removeLikes(p1: person, p2: person) {
		remove (p1, p2) from likes;
	}

	transition removeHuman(p: person) {
		remove (p) from human;
	}

}