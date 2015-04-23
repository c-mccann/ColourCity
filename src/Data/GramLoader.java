package Data;

import org.apache.commons.collections.MultiHashMap;
//import com.google.common.collect.HashMultimap;            // will test later

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ListIterator;

/**
 * Created by carlmccann2 on 25/02/15.
 */


/*  Implemented MultiHashMap functionality to test it out, need to remove either those or the hashmaps
    to clean up code that does the same thing twice, everything stores fully with them bar the unigrams,
    which i think is down to the method is searching for matches in the ColourMap cHash.
 */

public class GramLoader {
    HashMap<String,String> unigramHash = new HashMap<String,String>();
    HashMap<String,String> unBracketedHash = new HashMap<String,String>();
    HashMap<String,String> bracketedHash = new HashMap<String,String>();
    HashMap<String,String> pluralHash = new HashMap<String,String>();

    //HashMap<String, String> gramHexHash = new HashMap<String, String>();

    ColourMap cm = new ColourMap();

    MultiHashMap unimhm = new MultiHashMap();
    MultiHashMap unBrackmhm = new MultiHashMap();
    MultiHashMap brackmhm = new MultiHashMap();
    MultiHashMap pluralmhm = new MultiHashMap();

    MultiHashMap gramHexMhm = new MultiHashMap();


    GramLoader(ColourMap cm){
        this.cm = cm;


    }



    public void unigramsToHash() throws FileNotFoundException {         // unfinished
        String currentLine;
        String[] splitLine = new String[2];
        BufferedReader buffer = null;
        String tempLine = "";


        try {
            buffer = new BufferedReader(new FileReader("src/Resources/gram_unigrams.txt"));

            while ( (currentLine = buffer.readLine()) != null){
                currentLine = buffer.readLine();
                for (int i = 0; i < currentLine.length() ; i++) {
                    tempLine += currentLine.charAt(i);

                    if( cm.cHash.containsKey(tempLine) ){
                        unigramHash.put(tempLine,currentLine.substring(i+1));
                        unimhm.put(tempLine, currentLine.substring(i+1));
                        tempLine = "";
                        break;
                    }

                }
            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void unbracketedBigramsToHash() throws FileNotFoundException{
        String currentLine;
        String[] splitLine = new String[2];
        BufferedReader buffer = null;

        try {
            buffer = new BufferedReader(new FileReader("src/Resources/gram_unbracketedBigrams.txt"));

            while ( (currentLine = buffer.readLine()) != null){
                splitLine = currentLine.split("\t");


                unBracketedHash.put(splitLine[0], splitLine[1]);
                unBrackmhm.put(splitLine[0], splitLine[1]);

            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void bracketedBigramsToHash() throws FileNotFoundException{
        String currentLine;
        String[] splitLine = new String[2];
        BufferedReader buffer = null;

        try {
            buffer = new BufferedReader(new FileReader("src/Resources/gram_bracketedBigrams.txt"));

            while ( (currentLine = buffer.readLine()) != null){
                splitLine = currentLine.split("\t");

                bracketedHash.put(splitLine[0], splitLine[1]);
                brackmhm.put(splitLine[0], splitLine[1]);

            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void pluralBigramsToHash() throws FileNotFoundException{         // unfinished
        String currentLine;
        String[] splitLine = new String[2];
        BufferedReader buffer = null;

        try {
            buffer = new BufferedReader(new FileReader("src/Resources/gram_pluralBigrams.txt"));

            while ( (currentLine = buffer.readLine()) != null){
                splitLine = currentLine.split("\t");

                pluralHash.put(splitLine[0], splitLine[1]);
                pluralmhm.put(splitLine[0], splitLine[1]);

            }
        }

        catch (IOException e) {
            e.printStackTrace();
        }



    }

    public boolean isInColourHash(String s1, String s2){                        // possibly delete
        boolean match = false;

        if(cm.colourHash.containsKey(s1) && cm.colourHash.containsValue(s2)){
            match = true;
        }

        return match;
    }


    public void hexAssign(){

       // gramHexHash.put()
       // ifcm.cHash.containsKey(tempLine)

//        for (int i = 0; i < unimhm.totalSize(); i++) {
//            if( cm.cHash.containsKey(unimhm.forEach();))
//
//        }



    }




    public static void main(String[] args) throws FileNotFoundException {



        ColourMap cm = new ColourMap();

        GramLoader gl = new GramLoader(cm);

        cm.FileToHash();
        //System.out.println(ColourMap.colourHash);

        //System.out.println(ColourMap.colourHash);

        gl.unbracketedBigramsToHash();
        gl.unigramsToHash();
        gl.pluralBigramsToHash();
        gl.bracketedBigramsToHash();

        //System.out.println(gl.unBracketedHash);
        //System.out.println(gl.unigramHash);
        //System.out.println(gl.pluralHash);
        //System.out.println(gl.bracketedHash);


        System.out.println("unBracketed Bigram Hash Size: " + gl.unBracketedHash.size());
        System.out.println("unigram Hash Size:            " + gl.unigramHash.size());
        System.out.println("Plural Bigram Hash Size:      " + gl.pluralHash.size());
        System.out.println("Bracketed Bigram Hash Size:   " + gl.bracketedHash.size());

        System.out.println(gl.unBracketedHash.get("ghost"));

        System.out.println("MHM unbracketed Keys:         " + gl.unBrackmhm.size());
        System.out.println("MHM unbracketed Values:       " + gl.unBrackmhm.totalSize());
        System.out.println("MHM unigram Keys:             " + gl.unimhm.size());
        System.out.println("MHM unigram Values:           " + gl.unimhm.totalSize());
        System.out.println("MHM plural Keys:              " + gl.pluralmhm.size());
        System.out.println("MHM plural Values:            " + gl.pluralmhm.totalSize());
        System.out.println("MHM bracketed Keys:           " + gl.brackmhm.size());
        System.out.println("MHM bracketed Values:         " + gl.brackmhm.totalSize());

        System.out.println(gl.unBrackmhm.getCollection("ghost"));
        System.out.println(gl.unBrackmhm.get("ghost").toString());
        String s = gl.unBrackmhm.get("ghost").toString();
        System.out.println(s);

        String[] a = (String[]) gl.unBrackmhm.getCollection("ghost").toArray();
//        String testy[];
//        testy = gl.unBrackmhm.getCollection("ghost").toArray();



    }

}
