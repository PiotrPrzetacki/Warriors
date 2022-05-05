package com.company.utils;

public class Node {
    public int x, y;
    public double f, g, h;
    public Node parent;

    public Node(int x, int y, double f, Node parent) {
        this.x = x;
        this.y = y;
        this.f = f;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
