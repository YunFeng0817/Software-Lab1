/* Copyright (c) 2007-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P4.twitter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.time.Instant;
import java.util.regex.*;

/**
 * Extract consists of methods that extract information from a list of tweets.
 * <p>
 * DO NOT change the method signatures and specifications of these methods, but
 * you should implement their method bodies, and you may add new public or
 * private methods or classes if you like.
 */
public class Extract {

    /**
     * Get the time period spanned by tweets.
     *
     * @param tweets list of tweets with distinct ids, not modified by this method.
     * @return a minimum-length time interval that contains the timestamp of
     * every tweet in the list.
     */
    public static Timespan getTimespan(List<Tweet> tweets) {
        if (tweets.isEmpty())
            return null;
        Instant previos = tweets.get(0).getTimestamp(), latest = tweets.get(0).getTimestamp();
        for (int i = 0; i < tweets.size(); i++) {
            if (previos.isAfter(tweets.get(i).getTimestamp())) {
                previos = tweets.get(i).getTimestamp();
            }
            if (latest.isBefore(tweets.get(i).getTimestamp())) {
                latest = tweets.get(i).getTimestamp();
            }
        }
        Timespan time = new Timespan(previos, latest);
        return time;
    }

    /**
     * Get usernames mentioned in a list of tweets.
     *
     * @param tweets list of tweets with distinct ids, not modified by this method.
     * @return the set of usernames who are mentioned in the text of the tweets.
     * A username-mention is "@" followed by a Twitter username (as
     * defined by Tweet.getAuthor()'s spec).
     * The username-mention cannot be immediately preceded or followed by any
     * character valid in a Twitter username.
     * For this reason, an email address like bitdiddle@mit.edu does NOT
     * contain a mention of the username mit.
     * Twitter usernames are case-insensitive, and the returned set may
     * include a username at most once.
     */
    public static Set<String> getMentionedUsers(List<Tweet> tweets) {
        Set<String> MentionedUsers = new HashSet<>();
        Pattern regex = Pattern.compile("[^\\w\\-]@[\\w\\-]+");
        Matcher matcher;
        for (int i = 0; i < tweets.size(); i++) {
            String temp = tweets.get(i).getText();
            matcher = regex.matcher(" " + temp + " ");
            while (matcher.find()) {
                String methionedUsers = matcher.group().substring(matcher.group().indexOf("@")+1).toLowerCase();
                if (!MentionedUsers.contains(methionedUsers)){
                    MentionedUsers.add(methionedUsers);
                }
            }
        }
        return MentionedUsers;
    }

}
