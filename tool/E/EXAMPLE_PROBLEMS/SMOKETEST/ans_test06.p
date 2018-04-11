%fof(socrates,  axiom, (philosopher(socrates)|philosopher(plato))).
%fof(hume,      axiom, (philosopher(hume))).
%fof(phil_wise, axiom, (![X]:(philosopher(X) => wise(X)))).
%fof(is_there_wisdom, conjecture, (?[X]:wise(X))).

fof(all_created_equal,axiom,( 
    ! [H1,H2] : 
      ( ( human(H1) 
         & human(H2) ) 
     => created_equal(H1,H2) ) )). 

fof(john,axiom,( 
    human(john) )). 

fof(john_failed,axiom,( 
    grade(john) = f )). 

fof(someone_got_an_a,axiom,( 
    ? [H] : 
      ( human(H) 
      & grade(H) = a ) )). 

fof(distinct_grades,axiom,( 
    a != f )). 

fof(grades_not_human,axiom,( 
    ! [G] : ~ human(grade(G)) )). 

fof(someone_not_john,conjecture,( 
    ? [H] : 
      ( human(H) 
      & H = john ) )). 


