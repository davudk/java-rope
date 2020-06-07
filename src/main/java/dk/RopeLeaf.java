package dk;

import java.util.Objects;

class RopeLeaf implements Rope {
    private final CharSequence s;
    private final int start, end, length;
    private String str;

    RopeLeaf(CharSequence s) {
        Objects.requireNonNull(s);
        this.s = s;
        this.start = 0;
        this.end = s.length();
        this.length = s.length();
    }

    RopeLeaf(CharSequence s, int start, int end) {
        Objects.requireNonNull(s);
        this.s = s;

        final int actualLength = s.length();

        if (start < 0 || end < start || end > actualLength) {
            throw new IndexOutOfBoundsException();
        }

        this.start = start;
        this.end = end;
        this.length = end - start;
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public char charAt(int index) {
        return s.charAt(validateAndOffset(index));
    }

    @Override
    public Rope subRope(int start, int end) {
        return new RopeLeaf(s.subSequence(validateAndOffset(start), validateAndOffset(end)));
    }

    @Override
    public String toString() {
        if (str == null) {
            final StringBuilder sb = new StringBuilder();
            sb.append(s.subSequence(start, end));
            str = sb.toString();
        }
        return str;
    }

    @Override
    public int hashCode() {
        return RopeUtil.hashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof CharSequence) {
            return RopeUtil.equals(this, (CharSequence) obj);
        } else return false;
    }

    private int validateAndOffset(int index) {
        if (index < 0 || index > length) {
            throw new IndexOutOfBoundsException();
        } else return start + index;
    }
}
