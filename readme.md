## Rope data structure implementation in Java

The [Rope data structure](https://en.wikipedia.org/wiki/Rope_(data_structure) is a construct that represents text as a tree rather than the traditional char-array. This means mutation operations are performed faster, since the product of a mutation (e.g. an insertion or deletion) does not require a new char-array to be created for storage.

For example, the image below (taken from Wikipedia) represents the text "Hello_my_name_is_Simon" in the form a tree. If the rope were created from the string as-is, then the tree would consist of a single node. The image below represents the structure of the rope after multiple mutations.

![Sample tree diagram of a rope](https://upload.wikimedia.org/wikipedia/commons/thumb/8/8a/Vector_Rope_example.svg/640px-Vector_Rope_example.svg.png)


## Features

Creating a new `Rope` instance is easy:

```java
Rope r = Ropes.from("Hello, world!");
```

The ropes class has some useful static functions that allow initializing a `Rope` from a `CharSequence`. Note: in Java `String` class implements `CharSequence`... so does `StringBuilder` and other classes.

```java
public final class Ropes {
    
    public static Rope from(CharSequence s);

    public static Rope concat(CharSequence delimiter, CharSequence... charSequences)

    public static Rope from(CharSequence... charSequences);
    public static Rope from(CharSequence s, int start);
    public static Rope from(CharSequence s, int start, int end);
}
```

Here is the rope interface:

```java
public interface Rope extends CharSequence {

    Rope prepend(CharSequence... charSequences);
    Rope prepend(Rope r, int start);
    Rope prepend(Rope r, int start, int end);

    Rope append(CharSequence... charSequences);
    Rope append(Rope r, int start);
    Rope append(Rope r, int start, int end);

    Rope subRope(int start);
    Rope subRope(int start, int end);

    Rope insert(int index, CharSequence charSequence);

    Rope remove(int start);
    Rope remove(int start, int end);

    Rope replace(int start, int end, CharSequence replacement);

    Rope trimStart();
    Rope trimStart(char c);
    Rope trimStart(char... chars);
    Rope trimStart(CharPredicate predicate);

    Rope trimEnd();
    Rope trimEnd(char c);
    Rope trimEnd(char... chars);
    Rope trimEnd(CharPredicate predicate);

    Rope trim();
    Rope trim(char c);
    Rope trim(char... chars);
    Rope trim(CharPredicate predicate);
}
```

## Todo

- [x] Prepend, append and insert
- [x] Remove within range
- [x] Replace range with another
- [x] Trim start, end and both
- [ ] Implement `Comparable<Rope>` (function `compareTo(Rope other)`)
- [ ] Compare function with optional start and end indices
- [ ] Starts with / ends with
- [ ] Convert to uppercase / lowercase
- [ ] Contains, index of, last index of char/charsequence
- [ ] Replace char/charsequence with another
- [ ] Split by char/charsequence
- [ ] Case insensitive variants for the functions above