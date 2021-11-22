package org.pg4200.ex09;

import org.pg4200.les09.UndirectedGraph;

import java.util.*;


public class AllPathsGraph<V> extends UndirectedGraph<V> {
    public List<List<V>> findAllPaths(V start, V end) {

        if (!graph.containsKey(start) && !graph.containsKey(end)) {
            return Collections.emptyList();
        }

        if (start.equals(end)) {
            throw new IllegalArgumentException();
        }

        Deque<V> visited = new ArrayDeque<>();
        List<List<V>> paths = new ArrayList<>();

        findAllPathsDFS(paths, visited, start, end);

        return paths;
    }

    private void findAllPathsDFS(List<List<V>> paths, Deque<V> visited, V current, V end) {
        visited.push(current);

        if (isPathTo(visited, end)) {
            List<V> path = new ArrayList<>(visited);
            Collections.reverse(path);
            paths.add(path);
            return;
        }

        for (V connected : getAdjacents(current)) {
            if (visited.contains(connected)) {
                continue;
            }

            findAllPathsDFS(paths, visited, connected, end);

            // trace back
            visited.pop();
        }
    }

}
