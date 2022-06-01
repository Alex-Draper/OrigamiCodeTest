# Forgetting Map

The Forgetting Map is a Key Value pair structure that can store any type as both key and value.
An association can be made with the Add() method and an association can be retrieved using the Find() method.


## Design Considerations

### Tie Break Situation
If multiple least used associations are found then the oldest age of the association is used to break the tie. 

e.g. map.add(key1, val1), map.add(key2, val2), map.add(key3, val3)
key1 association was added before key2 and key3 and so technically is the oldest association and will be the "winner" 
of the tie-break.

### Generics
1. Use of generics to allow for use of different types and not limiting the key or value to one define type.

### Thread Safety
1. Use of synchronized keyword to ensure thread safety when accessing the Add and Find methods.
2. Use of final keywords where needed to again ensure thread safety.
3. Use of AtomicInteger counter to keep track of insertion order.
4. Use of HashTable as this is thread safe itself.

### Internal Design
The use of an access queue and a reference map was to avoid the need to iterate. 

We can use a priority queue to sort the associations with the least amount of accesses. 
We need to be able to update access values so a reference map is used to immediately find an entry in the queue no matter 
where it is located.


