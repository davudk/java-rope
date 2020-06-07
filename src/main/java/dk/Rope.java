package dk;

import java.util.Objects;

public interface Rope extends CharSequence {
    Rope EMPTY = new RopeLeaf("");

    @Override
    default CharSequence subSequence(int start, int end) {
        return subRope(start, end);
    }

    default Rope prepend(CharSequence... charSequences) {
        Objects.requireNonNull(charSequences);
        final CharSequence[] arr = new CharSequence[charSequences.length + 1];
        System.arraycopy(charSequences, 0, arr, 0, charSequences.length);
        arr[charSequences.length] = this;
        return new RopeNode(arr);
    }

    default Rope prepend(Rope r, int start) {
        return new RopeNode(this, r.subRope(start));
    }

    default Rope prepend(Rope r, int start, int end) {
        return new RopeNode(this, r.subRope(start, end));
    }

    default Rope append(CharSequence... charSequences) {
        Objects.requireNonNull(charSequences);
        final CharSequence[] arr = new CharSequence[charSequences.length + 1];
        arr[0] = this;
        System.arraycopy(charSequences, 0, arr, 1, charSequences.length);
        return new RopeNode(arr);
    }

    default Rope append(Rope r, int start) {
        return new RopeNode(r.subRope(start), this);
    }

    default Rope append(Rope r, int start, int end) {
        return new RopeNode(r.subRope(start, end), this);
    }

    default Rope subRope(int start) {
        return subRope(start, length());
    }

    default Rope subRope(int start, int end) {
        return new RopeLeaf(this, start, end);
    }

    default Rope insert(int index, CharSequence charSequence) {
        if (index < 0 || index > length()) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            return new RopeNode(charSequence, this);
        } else if (index == length()) {
            return new RopeNode(this, charSequence);
        }

        return new RopeNode(
                new RopeLeaf(this, 0, index),
                charSequence,
                new RopeLeaf(this, index, length())
        );
    }

    default Rope remove(int start) {
        return remove(start, length());
    }

    default Rope remove(int start, int end) {
        if (start < 0 || start > end || end > length()) {
            throw new IndexOutOfBoundsException();
        }

        final boolean anchoredToStart = start == 0;
        final boolean anchoredToEnd = end == length();

        if (anchoredToStart && anchoredToEnd) {
            return EMPTY;
        } else if (anchoredToStart) {
            return new RopeLeaf(this, end, length());
        } else if (anchoredToEnd) {
            return new RopeLeaf(this, 0, start);
        }

        return new RopeNode(
                new RopeLeaf(this, 0, start),
                new RopeLeaf(this, end, length())
        );
    }

    default Rope replace(int start, int end, CharSequence replacement) {
        if (start < 0 || start > end || end > length()) {
            throw new IndexOutOfBoundsException();
        }

        final boolean anchoredToStart = start == 0;
        final boolean anchoredToEnd = end == length();

        if (anchoredToStart && anchoredToEnd) {
            return new RopeLeaf(replacement);
        } else if (anchoredToStart) {
            return new RopeNode(
                    new RopeLeaf(replacement),
                    new RopeLeaf(this, end, length())
            );
        } else if (anchoredToEnd) {
            return new RopeNode(
                    new RopeLeaf(this, 0, start),
                    new RopeLeaf(replacement)
            );
        }

        return new RopeNode(
                new RopeLeaf(this, 0, start),
                new RopeLeaf(replacement),
                new RopeLeaf(this, end, length())
        );
    }

    default Rope trimStart() {
        return trimStart(Character::isWhitespace);
    }

    default Rope trimStart(char c) {
        int start = 0;
        while (start < length() && charAt(start) == c) start++;

        if (start == 0) return this;
        else if (start >= length()) return Rope.EMPTY;
        else return new RopeLeaf(this, start, length());
    }

    default Rope trimStart(char... chars) {
        return trimStart(c -> {
            for (char c2 : chars) {
                if (c == c2) return true;
            }
            return false;
        });
    }

    default Rope trimStart(CharPredicate predicate) {
        int start = 0;
        while (start < length() && predicate.test(charAt(start))) start++;

        if (start == 0) return this;
        else if (start >= length()) return Rope.EMPTY;
        else return new RopeLeaf(this, start, length());
    }

    default Rope trimEnd() {
        return trimEnd(Character::isWhitespace);
    }

    default Rope trimEnd(char c) {
        int end = length() - 1;
        while (end > 0 && charAt(end) == c) end--;

        if (end == length() - 1) return this;
        else if (end < 0) return Rope.EMPTY;
        else return new RopeLeaf(this, 0, end + 1);
    }

    default Rope trimEnd(char... chars) {
        return trimEnd(c -> {
            for (char c2 : chars) {
                if (c == c2) return true;
            }
            return false;
        });
    }

    default Rope trimEnd(CharPredicate predicate) {
        int end = length() - 1;
        while (end > 0 && predicate.test(charAt(end))) end--;

        if (end == length() - 1) return this;
        else if (end < 0) return Rope.EMPTY;
        else return new RopeLeaf(this, 0, end + 1);
    }

    default Rope trim() {
        return trim(Character::isWhitespace);
    }

    default Rope trim(char c) {
        int start = 0;
        while (start < length() && charAt(start) == c) start++;

        int end = length() - 1;
        while (end > 0 && end >= start && charAt(end) == c) end--;

        if (start == 0 && end == length() - 1) return this;
        else if (start >= length() || end < 0) return Rope.EMPTY;
        else return new RopeLeaf(this, start, end + 1);
    }

    default Rope trim(char... chars) {
        return trim(c -> {
            for (char c2 : chars) {
                if (c == c2) return true;
            }
            return false;
        });
    }

    default Rope trim(CharPredicate predicate) {
        int start = 0;
        while (start < length() && predicate.test(charAt(start))) start++;

        int end = length() - 1;
        while (end > 0 && end >= start && predicate.test(charAt(end))) end--;

        if (start == 0 && end == length() - 1) return this;
        else if (start >= length() || end < 0) return Rope.EMPTY;
        else return new RopeLeaf(this, start, end + 1);
    }

    default Rope intern() {
        return new RopeLeaf(toString().intern());
    }

    @Override
    String toString();

    @Override
    int hashCode();

    @Override
    boolean equals(Object obj);
}
