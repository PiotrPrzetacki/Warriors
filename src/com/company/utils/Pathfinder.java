package com.company.utils;

import com.company.Constants;
import com.company.classes.CharacterClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pathfinder {

    public static int[] getNextSquare(int[] src, int[] target){
        src = new int[] {src[0]/ Constants.CHARACTER_WIDTH, src[1]/Constants.CHARACTER_HEIGHT};
        int[] dest = new int[] {target[0]/Constants.CHARACTER_WIDTH, target[1]/Constants.CHARACTER_HEIGHT};
        int[] res = aStarSearch(src, dest);
        if(res != null) {
            return new int[]{res[0] * Constants.CHARACTER_WIDTH, res[1] * Constants.CHARACTER_HEIGHT};
        }
        else{
            return new int[]{src[0]*Constants.CHARACTER_WIDTH, src[1]*Constants.CHARACTER_HEIGHT};
        }
    }

    private static List<Node> traceBack(Node lastNode){
        List<Node> res = new ArrayList<>();
        res.add(lastNode);
        while (lastNode.parent != null){
            lastNode = lastNode.parent;
            res.add(lastNode);
        }
        return res;
    }

    private static int[] aStarSearch(int[] src, int[] dest)
    {
        List<Node> openList = new ArrayList<>();
        List<Node> closedList = new ArrayList<>();
        openList.add(new Node(src[0], src[1], 0, null));

        if(src[0]==dest[0] && src[1]==dest[1]){
            return null;
        }

        while (!openList.isEmpty()) {
            Node q = openList.get(0);
            for (Node node : openList) {
                if (node.f < q.f) {
                    q = node;
                }
            }
            openList.remove(q);
            List<Node> successors = new ArrayList<>();
            successors.add(new Node(q.x, q.y - 1, 0, q));
            successors.add(new Node(q.x, q.y + 1, 0, q));
            successors.add(new Node(q.x - 1, q.y, 0, q));
            successors.add(new Node(q.x + 1, q.y, 0, q));
            Collections.shuffle(successors);
            s:
            for (Node successor : successors) {
                if (successor.x == dest[0] && successor.y == dest[1]) {
                    List<Node> path = traceBack(successor);
                    if(path.size()>2) {
                        Node nextNode = path.get(path.size() - 2);
                        return new int[]{nextNode.x, nextNode.y};
                    }else{
                        return null;
                    }
                } else {
                    successor.g = q.g + 1;
                    successor.h = Math.abs(successor.x - dest[0]) + Math.abs(successor.y - dest[1]);
                    successor.f = successor.g + successor.h;
                }
                for (Node node : openList) {
                    if (node.x == successor.x && node.y == successor.y && node.f < successor.f) {
                        continue s;
                    }
                }
                for (Node node : closedList) {
                    if (node.x == successor.x && node.y == successor.y && node.f < successor.f) {
                        continue s;
                    }
                }
                try {
                    if (CharacterClass.occupiedCells[successor.x * Constants.CHARACTER_WIDTH][successor.y * Constants.CHARACTER_HEIGHT] == -1 ||
                            CharacterClass.occupiedCells[successor.x * Constants.CHARACTER_WIDTH][successor.y * Constants.CHARACTER_HEIGHT] > 0) {
                        continue;
                    }
                } catch (ArrayIndexOutOfBoundsException ignored) {}
                openList.add(successor);
            }
            closedList.add(q);
        }
        return null;
    }
}
