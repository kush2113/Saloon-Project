package lk.ijse.demokushan.entity;

import java.util.List;

public class AddNewHairCut {
    private HairCut hairCut;
    private List<HairCutDetails> hdList;

    public AddNewHairCut(HairCut hairCut, List<HairCutDetails> hdList) {
        this.hairCut = hairCut;
        this.hdList = hdList;
    }

    @Override
    public String toString() {
        return "AddNewHairCut{" +
                "hairCut=" + hairCut +
                ", hdList=" + hdList +
                '}';
    }

    public HairCut getHairCut() {
        return hairCut;
    }

    public void setHairCut(HairCut hairCut) {
        this.hairCut = hairCut;
    }

    public List<HairCutDetails> getHdList() {
        return hdList;
    }

    public void setHdList(List<HairCutDetails> hdList) {
        this.hdList = hdList;
    }
}
