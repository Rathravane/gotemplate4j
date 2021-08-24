package com.github.verils.gotemplate.parse;

import java.util.LinkedList;
import java.util.List;

public class ListNode implements Node {

    private final List<Node> nodes = new LinkedList<>();

    public void append(Node node) {
        nodes.add(node);
    }

    public Node getLast() {
        return nodes.get(nodes.size() - 1);
    }

    public void removeLast() {
        nodes.remove(nodes.size() - 1);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        nodes.forEach(sb::append);
        return sb.toString();
    }
}
