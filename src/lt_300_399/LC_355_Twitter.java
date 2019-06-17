package lt_300_399;


import java.util.*;

/**
 * [355] Design Twitter
 * OOP design
 */
public class LC_355_Twitter {
    private class Post{
        int tweetId;
        int order;
        public Post(int tweetId, int order) {
            this.tweetId = tweetId;
            this.order = order;
        }
    }
    private class User {
        int userId;
        Set<Integer> followed;
        List<Post> tweetList;
        public User(int userId) {
            this.userId = userId;
            followed = new HashSet<>();
            tweetList = new ArrayList<>();
        }
        public void addFollow(int userId) {
            this.followed.add(userId);
        }
        public void unFollow(int userId) {
            this.followed.remove(userId);
        }
        public void addTweet(int tweetId) {
            tweetList.add(new Post(tweetId, order++));
        }
    }
    private Map<Integer, User> map;
    private int order;

    /** Initialize your data structure here. */
    public LC_355_Twitter() {
        this.map = new HashMap<>();
        this.order = 0;
    }

    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new User(userId));
        }
        map.get(userId).addTweet(tweetId);
    }

    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ret = new ArrayList<>(15);
        if (!this.map.containsKey(userId)) return ret;
        PriorityQueue<Post> priorityQueue = new PriorityQueue<>((a, b)-> a.order-b.order);
        Set<Integer> userSet = this.map.get(userId).followed;
        userSet.add(userId);
        for (Integer id: userSet) {
            if (!this.map.containsKey(id) || this.map.get(id).tweetList.size() <= 0) continue;
            List<Post> tweetList = this.map.get(id).tweetList;
            for (int i = tweetList.size()-1; i>=0; i--) {
                Post post = tweetList.get(i);
                if (priorityQueue.size() >= 10 && priorityQueue.peek().order >= post.order) break;
                priorityQueue.offer(post);
                if (priorityQueue.size() > 10) {
                    priorityQueue.poll();
                }
            }
        }
        while (!priorityQueue.isEmpty()) {
            ret.add(0, priorityQueue.poll().tweetId);
        }
        return ret;
    }

    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!map.containsKey(followerId)) {
            map.put(followerId, new User(followerId));
        }
        map.get(followerId).addFollow(followeeId);
    }

    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (map.containsKey(followerId)) {
            map.get(followerId).unFollow(followeeId);
        }
    }

    public static void main(String ...args) {
        LC_355_Twitter twitter = new LC_355_Twitter();
        twitter.postTweet(1, 5);
        twitter.getNewsFeed(1);
        twitter.follow(1, 2);
        twitter.postTweet(2, 6);
        twitter.getNewsFeed(1);
        twitter.unfollow(1,2);
        twitter.getNewsFeed(1);
    }
}
