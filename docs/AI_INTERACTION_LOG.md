**Prompt 1**
I want to create a generic  class in Java called Bag that implements the container interface below:

public interface Container<E> extends Iterable<E> {
void add(E item);
boolean remove(E item);
boolean contains(E item);
int size();
boolean isEmpty();
}

It must use ArrayList as the backing data structure and we are not allowed to make changes to the provided 
interface. Include comments as needed to help engineers understand its purpose/function. Please use standard 
Java naming conventions.  

*** Didn't specify package name






**Prompt 2**
I need a Junit 5 test class for the Bag class you created for me. The test should cover edge cases, normal operations,
iterator functionality, empty bags, single items and removals. Please include comments as needed to help engineers 
understand individual test purposes.