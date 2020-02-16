package fanjoker.vips.utils;

public class RandomString {

    public static String getString(int lenght){
        StringBuilder builder = new StringBuilder();
        String s = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int i = 0; i<lenght; i++){
            double index = Math.random() * s.length();
            builder.append(s.charAt((int)index));
        }
        return builder.toString();
    }
}
