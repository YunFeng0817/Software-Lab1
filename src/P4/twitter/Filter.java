/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Filter consists of methods that filter a list of tweets for those matching a
 * condition.
 * <p>
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Filter {

    /**
     * Find tweets written by a particular user.
     *
     * @param tweets   a list of tweets with distinct ids, not modified by this method.
     * @param username Twitter username, required to be a valid Twitter username as
     *                 defined by Tweet.getAuthor()'s spec.
     * @return all and only the tweets in the list whose author is username,
     * in the same order as in the input list.
     */
    public static List<Tweet> writtenBy(List<Tweet> tweets, String username) {
        List<Tweet> filterResult = new ArrayList();
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getAuthor() == username)
                filterResult.add(tweets.get(i));
        }
        return filterResult;
    }

    /**
     * @param tweets a list of tweets with distinct ids, not modified by this method.
     * @return all the author of these tweets and every author username in return list is universal
     */
    public static List<String> getAuthor(List<Tweet> tweets) {
        List<String> result = new ArrayList<String>();
        for (Tweet tweet : tweets) {
            if (!result.contains(tweet.getAuthor())) {
                result.add(tweet.getAuthor());
            }
        }
        return result;
    }

    /**
     * Find tweets that were sent during a particular timespan.
     *
     * @param tweets   a list of tweets with distinct ids, not modified by this method.
     * @param timespan timespan
     * @return all and only the tweets in the list that were sent during the timespan,
     * in the same order as in the input list.
     */
    public static List<Tweet> inTimespan(List<Tweet> tweets, Timespan timespan) {
        List<Tweet> filterResult = new ArrayList<>();
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).getTimestamp().isAfter(timespan.getStart()) && tweets.get(i).getTimestamp().isBefore(timespan.getEnd()))
                ;
            filterResult.add(tweets.get(i));
        }
        return filterResult;
    }

    /**
     * Find tweets that contain certain words.
     *
     * @param tweets a list of tweets with distinct ids, not modified by this method.
     * @param words  a list of words to search for in the tweets.
     *               A word is a nonempty sequence of nonspace characters.
     * @return all and only the tweets in the list such that the tweet text (when
     * represented as a sequence of nonempty words bounded by space characters
     * and the ends of the string) includes *at least one* of the words
     * found in the words list. Word comparison is not case-sensitive,
     * so "Obama" is the same as "obama".  The returned tweets are in the
     * same order as in the input list.
     */
    public static List<Tweet> containing(List<Tweet> tweets, List<String> words) {
        List<Tweet> filterResult = new ArrayList<>();
        for (Tweet tweet : tweets) {
            for (String regex : words) {
                Pattern rule = Pattern.compile("\\s" + regex.toLowerCase() + "\\s");
                Matcher result = rule.matcher(" " + tweet.getText().toLowerCase() + " ");
                if (result.find()) {
                    filterResult.add(tweet);
                    break;
                }
            }
        }
        return filterResult;
    }

}
