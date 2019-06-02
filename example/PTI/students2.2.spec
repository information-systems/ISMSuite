process Student {

place Education_Track ( t : track ) {
register t;
insert ( t ) into track;
}

place Administrator(p: person) {
register p;
insert (p) into person;
insert (p) into administrator;
}

place Course(c: course) {
register c;
insert (c) into course;
}

place Manager(p: person) {
register p;
insert (p) into person;
insert (p) into manager;
}

place Lecturer(p: person) {
register p;
insert (p) into person;
insert (p) into lecturer;
}

place Student(p: person, t: track) {
register p;
register t;
insert (p) into person;
insert (t) into track;
insert (p,t) into enrols;
}

transition Register(p: person, t: track) {
register p;
insert (p) into person;
insert (p,t) into registers;
}

transition Accept_student(d: date, p: person, t: track, a: person) {
register d;
insert (d) into date;
insert (p,t,a,d) into accepts;
insert (p,t) into enrols;
}

transition Reject_student(d: date, p: person, t: track, a: person) {
register d;
remove (p, t) from registers;
insert (p, t, a, d) into rejects;
}

}