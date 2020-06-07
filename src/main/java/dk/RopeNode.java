package dk;

import java.util.Objects;

class RopeNode implements Rope {
    private final CharSequence[] charSequences;
    private final int[] starts, ends;
    private final int length;
    private String str;

    RopeNode(CharSequence... charSequences) {
        Objects.requireNonNull(charSequences);
        for (CharSequence charSequence : charSequences) {
            Objects.requireNonNull(charSequence);
        }

        this.charSequences = charSequences;
        this.starts = new int[this.charSequences.length];
        this.ends = new int[this.charSequences.length];
        this.ends[0] = this.charSequences[0].length();
        for (int i = 1; i < this.charSequences.length; i++) {
            this.starts[i] = this.ends[i - 1];
            this.ends[i] = this.ends[i - 1] + this.charSequences[i].length();
        }
        length = this.ends[this.charSequences.length - 1];
    }

    @Override
    public int length() {
        return length;
    }

    @Override
    public char charAt(int index) {
        final int i = locateOrThrow(index);
        return charSequences[i].charAt(index - starts[i]);
    }

    @Override
    public String toString() {
        if (str == null) {
            final StringBuilder sb = new StringBuilder();
            for (CharSequence c : charSequences) {
                sb.append(c);
            }
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

    private int locateOrThrow(int index) {
        final int i = locate(index);
        if (i < 0) throw new IndexOutOfBoundsException();
        else return i;
    }

    private int locate(int index) {
        int left = 0, right = this.charSequences.length - 1;

        while (left <= right) {
            final int middle = (left + right) / 2;

            final int start = this.starts[middle];
            final int end = this.ends[middle];

            if (start <= index && index < end) {
                return middle;
            } else if (index < start) {
                right = middle - 1;
            } else /* if (index > end) */ {
                left = middle + 1;
            }
        }

        return -1;
    }
}
