package exercise;

<<<<<<< HEAD
class ReversedSequence implements CharSequence{

    private String s;

    public ReversedSequence(String s) {
        this.s = s;
    }

    @Override
    public int length() {
        return s.length();
    }

    @Override
    public char charAt(int index) {
        char[] a = s.toCharArray();
        if (index >= s.length()) {
            throw new StringIndexOutOfBoundsException("index out of bound for length of string!");
        }
        return a[index];
    }

    @Override
    public CharSequence subSequence(int start, int end) {
        if(s.length() == 0) {
            return "";
        }
        StringBuilder b = new StringBuilder();
        for (int i = end; i >= start; i--) {
            b.append(this.charAt(i));
        }
        return b.toString();
    }
}
=======
// BEGIN

// END
>>>>>>> 3ce96c619a548ad576c1464074fba742a8bdf1d0
