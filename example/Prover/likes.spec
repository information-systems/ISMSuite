

process likes {

  transition addPerson(p: universe) {
    register p;
    insert (p) into person;
  }

  transition addFemale(v: universe) {
    register v;
    insert (v) into v;
  }

  transition addMale(m: universe) {
    register m;
    insert (m) into m;
  }

  transition addLikes(p1: universe, p2: universe) {
    insert (p1, p2) into likes;
  }

  transition removeLikes(p1: universe, p2: universe) {
    remove (p1, p2) from likes;
  }

  transition addCouple(m: universe, f: universe) {
    register m;
    register f;

    insert (m) into m;
    insert (f) into f;

    insert (m,f) into likes;
    insert (f,m) into likes;
  }

}
