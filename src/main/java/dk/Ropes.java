package dk;

import java.util.Objects;

public final class Ropes {
    private Ropes() {}

    public static Rope from(CharSequence s) {
        return new RopeLeaf(s);
    }

    public static Rope concat(CharSequence delimiter, CharSequence... charSequences) {
        Objects.requireNonNull(delimiter);
        Objects.requireNonNull(charSequences);
        if (charSequences.length == 0) {
            return Rope.EMPTY;
        } else if (charSequences.length == 1) {
            return new RopeLeaf(charSequences[0]);
        } else if (delimiter.length() == 0) {
            return from(charSequences);
        }

        final CharSequence[] arr = new CharSequence[charSequences.length * 2 - 1];
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0) arr[i] = charSequences[i >> 1];
            else arr[i] = delimiter;
        }
        return new RopeNode(arr);
    }

    public static Rope from(CharSequence... charSequences) {
        Objects.requireNonNull(charSequences);
        if (charSequences.length == 0) {
            return Rope.EMPTY;
        } else if (charSequences.length == 1) {
            return new RopeLeaf(charSequences[0]);
        } else return new RopeNode(charSequences.clone());
    }

    public static Rope from(CharSequence s, int start) {
        return new RopeLeaf(s, start, s.length() - 1);
    }

    public static Rope from(CharSequence s, int start, int end) {
        return new RopeLeaf(s, start, end);
    }
}
