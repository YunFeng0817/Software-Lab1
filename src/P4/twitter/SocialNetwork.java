/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.lang.reflect.Array;
import java.util.*;

/**
 * SocialNetwork provides methods that operate on a social network.
 * <p>
 * A social network is represented by a Map<String, Set<String>> where map[A] is
 * the set of people that person A follows on Twitter, and all people are
 * represented by their Twitter usernames. Users can't follow themselves. If A
 * doesn't follow anybody, then map[A] may be the empty set, or A may not even exist
 * as a key in the map; this is true even if A is followed by other people in the network.
 * Twitter usernames are not case sensitive, so "ernie" is the same as "ERNie".
 * A username should appear at most once as a key in the map or in any given
 * map[A] set.
 * <p>
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class SocialNetwork {

    /**
     * Guess who might follow whom, from evidence found in tweets.
     *
     * @param tweets a list of tweets providing the evidence, not modified by this
     *               method.
     * @return a social network (as defined above) in which Ernie follows Bert
     * if and only if there is evidence for it in the given list of
     * tweets.
     * One kind of evidence that Ernie follows Bert is if Ernie
     * @-mentions Bert in a tweet. This must be implemented. Other kinds
     * of evidence may be used at the implementor's discretion.
     * All the Twitter usernames in the returned social network must be
     * either authors or @-mentions in the list of tweets.
     */
    public static Map<String, Set<String>> guessFollowsGraph(List<Tweet> tweets) {
        List<String> authors = Filter.getAuthor(tweets);
        List<Tweet> writtenByAuthor;
        Map<String, Set<String>> result = new HashMap<>();
        for (String author : authors) {
            writtenByAuthor = Filter.writtenBy(tweets, author);
            result.put(author, Extract.getMentionedUsers(writtenByAuthor));
        }
        return result;
    }

    /**
     * Find the people in a social network who have the greatest influence, in
     * the sense that they have the most followers.
     *
     * @param followsGraph a social network (as defined above)
     * @return a list of all distinct Twitter usernames in followsGraph, in
     * descending order of follower count.
     */
    public static List<String> influencers(Map<String, Set<String>> followsGraph) {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Set<String>> author : followsGraph.entrySet()) {
            for (String following : author.getValue()) {
                if (result.containsKey(following)) {
                    int temp = result.get(following);
                    result.remove(following);
                    result.put(following, temp + 1);
                } else {
                    result.put(following, 1);
                }
            }
        }
        List<Map.Entry<String, Integer>> sortResult = new ArrayList<Map.Entry<String, Integer>>(result.entrySet());
        Collections.sort(sortResult, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });
        List<String> authorList = new ArrayList<>();
        for (int i = 0; i < sortResult.size(); i++) {
            authorList.add(sortResult.get(i).getKey());
        }
        return authorList;
    }

}
