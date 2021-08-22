package com.github.verils.gotemplate.parse;

public class ActionNode implements Node {

    private PipeNode pipeNode;

    public PipeNode getPipeNode() {
        return pipeNode;
    }

    public void setPipeNode(PipeNode pipeNode) {
        this.pipeNode = pipeNode;
    }

    @Override
    public String toString() {
        return "{{" + pipeNode + "}}";
    }
}
