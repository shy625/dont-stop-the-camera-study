/*
 * 풀이 미완
 */

import java.util.*;

class Solution {
    
    private static class Room {
        int num;
        int prev;
        List<Integer> links;
        
        Room(int num) {
            this.num = num;
            this.prev = -1;
            this.links = new ArrayList<>();
        }
    }
    
    public boolean solution(int n, int[][] path, int[][] order) {
        Room[] rooms = new Room[n];
        for (int i = 0; i < n; i++) {
            rooms[i] = new Room(i);
        }
        
        for (int[] p : path) {
            rooms[p[0]].links.add(p[1]);
            rooms[p[1]].links.add(p[0]);
        }
        
        for (int[] o : order) {
            rooms[o[1]].prev = o[0];
        }
        
        return exploreRoom(0, rooms);
    }
    
    private boolean exploreRoom(int start, Room[] rooms) {
        Set<Integer> visitedSet = new HashSet<>();
        Set<Integer> unvisitedSet = new HashSet<>();
        
        for (int i = 0; i < rooms.length; i++) {
            unvisitedSet.add(i);
        }
        
        boolean flag = true;
        while (unvisitedSet.size() != 0) {
            Queue<Integer> q = new LinkedList<>();
            if (unvisitedSet.contains(start)) {
                q.offer(start);
                visitedSet.add(start);
                unvisitedSet.remove(start);
            } else {
                for (Integer cur : unvisitedSet) {
                    if (visitedSet.contains(rooms[cur].prev)) {
                        q.offer(cur);
                        visitedSet.add(cur);
                        unvisitedSet.remove(cur);
                    }
                }
            }
            
            if (q.isEmpty()) {
                flag = false;
                break;
            }
            
            while (!q.isEmpty()) {
                int cur = q.poll();
                for (Integer next : rooms[cur].links) {
                    if (unvisitedSet.contains(next) && (rooms[next].prev == -1 || visitedSet.contains(rooms[next].prev))) {
                        q.offer(next);
                        visitedSet.add(next);
                        unvisitedSet.remove(next);
                    }
                }
                // for (Iterator<Integer> iterator = rooms[cur].links.iterator(); iterator.hasNext(); ) {
                //     Integer next = iterator.next();
                //     if (unvisitedSet.contains(next) && (rooms[next].prev == -1 || visitedSet.contains(rooms[next].prev))) {
                //         q.offer(next);
                //         visitedSet.add(next);
                //         unvisitedSet.remove(next);
                //     }
                // }
            }
        }
        return flag;
    }
}