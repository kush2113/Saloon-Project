package lk.ijse.demokushan.dto;

import java.util.List;

public class AddNewHairCutDTO {
    private HairCutDTO hairCut;
    private List<HairCutDetailsDTO> hdList;

    public AddNewHairCutDTO(HairCutDTO hairCut, List<HairCutDetailsDTO> hdList) {
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

    public HairCutDTO getHairCut() {
        return hairCut;
    }

    public void setHairCut(HairCutDTO hairCut) {
        this.hairCut = hairCut;
    }

    public List<HairCutDetailsDTO> getHdList() {
        return hdList;
    }

    public void setHdList(List<HairCutDetailsDTO> hdList) {
        this.hdList = hdList;
    }
}
