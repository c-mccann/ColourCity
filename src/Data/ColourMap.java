package Data;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * Created by carlmccann2 on 04/02/15.
 */

/*  need to remove either cHash or colourHash, using both at the moment to not complicate things with
    colourBlender.
 */


public class ColourMap {


    HashMap<String, HashMap<String,String>> cHash = new HashMap<String, HashMap<String,String>>();
    HashMap<String,String> colourHash = new HashMap<String, String>();

    public void FileToHash()throws FileNotFoundException {

        String currentLine;
        String[] splitLine = new String[2];
        String[] splitLine2 = new String[2];
        BufferedReader buffer = null;

        try {
            buffer = new BufferedReader(new FileReader("src/Resources/Stereotype-Colour-RGB.txt"));
            while ( (currentLine = buffer.readLine()) != null){
                splitLine = currentLine.split("\t#");
                splitLine2 = splitLine[0].split("\t");

                cHash.put(splitLine2[0], new HashMap<String, String>());
                cHash.get(splitLine2[0]).put(splitLine2[1], splitLine[1]);
                colourHash.put(splitLine[0],splitLine[1]);
            }
        }


        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("-------------------------------------------------------");
        System.out.println("colourHash populated");
        System.out.println("-------------------------------------------------------");
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

        cb.blender(cm.cHash.get("bordeaux").get("red"), cm.cHash.get("canvas").get("white"), ratio);

        //System.out.println(colourHash);
        //System.out.println(cHash);
    }
}
