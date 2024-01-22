package test_shit;

public class testForMe {
    public static void main(String[] args) {
        System.out.println(isPalindrom("abcdef"));
        System.out.println(isPalindrom("abccba"));
        System.out.println(isPalindrom("abc1cba"));
    }

    public static boolean isPalindrom(String toTest) {
        for (int i = 0; i < toTest.length(); i++) {
            if (toTest.charAt(i) != toTest.charAt(toTest.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }


}




