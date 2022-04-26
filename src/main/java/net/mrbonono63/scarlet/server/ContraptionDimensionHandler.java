package net.mrbonono63.scarlet.server;

import java.util.*;

public class ContraptionDimensionHandler {

    public  List<Contraption> contraptionList = new ArrayList<>();

    int previousListSize = 0;
    int ListSize = 0;

    public ContraptionDimensionHandler() {

    }

    public  void addContraption(Contraption contraption)
    {
        contraptionList.add(contraption);
    }

    public  void removeContraption(int i)
    {
        contraptionList.remove(i);
    }

    public void tick()
    {
        this.previousListSize = this.ListSize;
        this.ListSize = contraptionList.size();
    }

    //assuming that the new contraption is added on the end of the contraptionList this will get the contraption's
    //position in the list and allow it to remove itself in the future by adding
    public int getListSize()
    {
        return this.ListSize;
    }

    public Contraption getContraption(int listIndex)
    {
        return this.contraptionList.get(listIndex);
    }
}
