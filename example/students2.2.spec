process Student {

	place Education_Track ( t : track ) {
		insert( track(t) );
	}

	place Administrator(p: person) {
		register(p);
		insert( person(p) );
		insert( administrator( p ) );
	}

	place Course(c: course) {
		register(c);
		insert( course(c) );
	}

	place Manager(p: person) {
		register(p);
		insert( person(p) );
		insert( manager(p) );
	}

	place Lecturer(p: person) {
		register(p);
		insert( person(p) );
		insert( lecturer(p) );
	}

	place Student(p: person, t: track) {
		register(p);
		register(t);
		insert (person(p));
		insert (track(t));
		insert (enrols(p,t));
	}

	transition Register(p: person, t: track) {
		register(p);
		insert( person(p) );
		insert( registers(p, t) );
	}

	transition Accept_student(d: date, p: person, t: track, a: person) {
		register(d);
		insert( date(d) );
		insert( accepts( p, t, a, d ) );
		insert( enrols( p, t) );
	}

	transition Reject_student(d: date, p: person, t: track, a: person) {
		register(d);
		remove( registers(p, t));
		insert( rejects(p, t, a, d) );
	}

}