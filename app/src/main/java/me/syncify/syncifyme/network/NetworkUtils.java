package me.syncify.syncifyme.network;

import java.util.TreeMap;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by adarshpandey on 9/14/15.
 */
public class NetworkUtils {

    public static String getHashmac(TreeMap<String, String> param) {

        StringBuilder poi = new StringBuilder();
        StringBuilder result = new StringBuilder();
        for (String key: param.keySet()) {
            String value = param.get(key);
            poi.append(value);
        }

//        String mykey = "my_private_key";
        String mykey = "8e5db4b7543537907ecd8ce080b15f03";
        String pubkey = "ec470031b76e3e421606bb1591130110";
        try {
            Mac mac = Mac.getInstance("HmacSHA1");
            SecretKeySpec secret = new SecretKeySpec(mykey.getBytes(),
                    "HmacSHA1");
            mac.init(secret);
            byte[] digest = mac.doFinal(poi.toString().getBytes());
//            s = new String(digest);
            for (byte b : digest) {
                result.append(String.format("%02x", b));
            }
            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return result.toString();
    }

    public static void main(String... args) {
        TreeMap<String, String> param = new TreeMap<>();
        param.put("pnr", "1234567890");
        param.put("format", "json");
        param.put("pbapikey", "ec470031b76e3e421606bb1591130110");
        System.out.println(getHashmac(param));
    }

}
