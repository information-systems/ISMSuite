
transaction addPerson(p: universe) {
    register p;
    insert (p) into person;
}

transaction addFemale(f: universe) {
  register f;
  insert (f) into f;
}

transaction addMale(m: universe) {
  register m;
  insert (m) into m;
}

transaction addLikes(p1: universe, p2: universe) {
  insert (p1, p2) into likes;
}

transaction removeLikes(p1: universe, p2: universe) {
  remove (p1, p2) from likes;
}

transaction addCouple(m: universe, f: universe) {
  register m;
  register f;

  insert (m) into m;
  insert (f) into f;

  insert (m,f) into likes;
  insert (f,m) into likes;
}
