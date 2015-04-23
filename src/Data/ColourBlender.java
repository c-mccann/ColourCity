package Data;

import java.io.FileNotFoundException;

/**
 * Created by carlmccann2 on 25/02/15.
 */
public class ColourBlender {

    public String blender(String RGB1, String RGB2){           // 50/50 split

        String mix1, mix2;
        int res1,res2;
        double mixed;
        String value = "";

        for (int i=0; i < 5; i+=2){
            mix1 = RGB1.substring(i,i+2);
            mix2 = RGB2.substring(i,i+2);

            res1 = Integer.parseInt(mix1,16);
            res2 = Integer.parseInt(mix2,16);

            mixed = (res1 * 0.5) + (res2 * (0.5));

            value = value + Integer.toHexString((int)mixed);

            if( value.length() < 2){
                value = "0" + value;
            }
        }

        System.out.println("-------------------------------------------------------");
        System.out.println(RGB1+" + "+RGB2+"    (50/50) "+"  = "+value);
        System.out.println("-------------------------------------------------------");

        return value;
    }

    public String blender(String RGB1, String RGB2, Double alpha){         // weighted with alpha

        String mix1, mix2;
        int res1,res2;
        double mixed;
        String value = "";

        for (int i=0; i < 5; i+=2){
            mix1 = RGB1.substring(i,i+2);
            mix2 = RGB2.substring(i,i+2);

            res1 = Integer.parseInt(mix1,16);
            res2 = Integer.parseInt(mix2,16);

            mixed = (res1 * alpha) + (res2 * (1 - alpha));

            value = value + Integer.toHexString((int)mixed);

            if( value.length() < 2){
                value = "0" + value;
            }
        }

        System.out.println("-------------------------------------------------------");
        System.out.println(RGB1+" + "+RGB2+" mixed at "+alpha+" = "+value);
        System.out.println("-------------------------------------------------------");

        return value;
    }

    public double distanceCalculator(String RGB1, String RGB2 ){

        String component1, component2;
        int res1, res2;
        double distance = 0;

        for (int i=0; i < 5; i+=2){                             // similar functionality to blender
            component1 = RGB1.substring(i,i+2);
            component2 = RGB2.substring(i,i+2);

            res1 = Integer.parseInt(component1,16);
            res2 = Integer.parseInt(component2,16);

            distance = distance + Math.pow((double)res2 - (double)res1, 2);
        }

        distance = Math.sqrt(distance);

        System.out.println("-------------------------------------------------------");
        System.out.println("Distance between " + RGB1 + " and "+RGB2+" = "+ distance );
        System.out.println("-------------------------------------------------------");

        return distance;
    }

    public static void main(String[] args) throws FileNotFoundException {

        ColourMap cm = new ColourMap();
        ColourBlender cb = new ColourBlender();

        String a = "bordeaux\tred";
        String b = "canvas\twhite";
        Double ratio = 0.9;


        cm.FileToHash();
        cb.blender(cm.colourHash.get(a), cm.colourHash.get(b));
        cb.blender(cm.colourHash.get(a), cm.colourHash.get(b), ratio);
        cb.distanceCalculator(cm.colourHash.get(a), cm.colourHash.get(b));
        cb.blender("02108B","FFFF55");
    }
}
