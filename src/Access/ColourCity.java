package Access;

/**
 * Created by carlmccann2 on 28/01/15.
 */

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import java.io.FileNotFoundException;

public class ColourCity {



        private String consumerKey;
        private String consumerKeySecret;
        private String accessToken;
        private String accessTokenSecret;


        public ColourCity() throws FileNotFoundException{
            String currentLine;
            BufferedReader buffer = null;
            try {
                buffer = new BufferedReader(new FileReader("src/Resources/Tokens.txt"));

                consumerKey = buffer.readLine();
                consumerKeySecret = buffer.readLine();
                accessToken = buffer.readLine();
                accessTokenSecret = buffer.readLine();
//                while ( (currentLine = buffer.readLine()) != null){       // test
//                    System.out.println(currentLine);
//
//                }
            }

            catch (IOException e){
                e.printStackTrace();
            }
        }

        public void start() throws TwitterException, IOException {

                Twitter twitter = new TwitterFactory().getInstance();
                twitter.setOAuthConsumer(consumerKey, consumerKeySecret);

                // here's the difference
                String accessToken = getSavedAccessToken();
                String accessTokenSecret = getSavedAccessTokenSecret();
                AccessToken oathAccessToken = new AccessToken(accessToken,
                        accessTokenSecret);

                twitter.setOAuthAccessToken(oathAccessToken);
                // end of difference
                twitter.updateStatus(".@32_pac swiggity swooty gimme dat booty");


//                code for endless loop of tweets

//                boolean looper = true;
//                String update;
//                Scanner tweeter = new Scanner(System.in);
//                while (looper == true){
//                        System.out.print("Tweet: ");
//                        update = tweeter.nextLine();
//                        twitter.updateStatus(update + " (from Access.TWARL #MooneyIsCrap)");
//                }



                System.out.println("\nMy Timeline:");

                // I'm reading your timeline
                ResponseList <Status> list = twitter.getHomeTimeline();
                for (Status each : list) {

                        System.out.println("Sent by: @" + each.getUser().getScreenName()
                                + " - " + each.getUser().getName() + "\n" + each.getText()
                                + "\n");
                }

        }

        private String getSavedAccessTokenSecret() {
                // consider this is method to get your previously saved Access Token
                // Secret
                return accessTokenSecret;
        }

        private String getSavedAccessToken() {
                // consider this is method to get your previously saved Access Token
                return accessToken;
        }


//      code to retrieve tokens from file not fully implemented

        //private String retrieveTokens(){
        //    File file = new File("src/Tokens.txt");
           // FileReader reader = new FileReader(file);

           // String token = reader.rea
           // return token;

        //}

        public static void main(String[] args) throws Exception {
                new ColourCity().start();
        }

}