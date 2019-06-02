

process Philosophers { 
	place Philosopher(p: person) {
		register p;
		insert (p) into human;
		insert (p) into philosopher;
	}

	transition newHuman(nu1: person) {
		register nu1;
		insert (nu1) into human;
	}

	transition newPerson(nu1: person, p: person) {
		register nu1;
		insert (nu1) into human;
		insert (nu1, p) into likes;
	}

	transition newPhilosopher(nu1: person) {
		register nu1;
		insert (nu1) into human;
		insert (nu1) into philosopher;
	}

	transition Reads(r: person, p: person) {
		insert (r, p) into likes;
	}

	transition Discuss(r: person, p: person) {
		remove (r, p) from likes;
	}

}
