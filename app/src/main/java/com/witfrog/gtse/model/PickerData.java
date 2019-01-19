package com.witfrog.gtse.model;

import java.util.ArrayList;

public class PickerData {

    private ArrayList<JsonArea>                       options1Items = new ArrayList<>();
    private ArrayList<ArrayList<JsonArea>>            options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<JsonArea>>> options3Items = new ArrayList<>();

    public ArrayList<JsonArea> getOptions1Items() {
        return options1Items;
    }

    public void setOptions1Items(ArrayList<JsonArea> options1Items) {
        this.options1Items = options1Items;
    }

    public ArrayList<ArrayList<JsonArea>> getOptions2Items() {
        return options2Items;
    }

    public void setOptions2Items(ArrayList<ArrayList<JsonArea>> options2Items) {
        this.options2Items = options2Items;
    }

    public ArrayList<ArrayList<ArrayList<JsonArea>>> getOptions3Items() {
        return options3Items;
    }

    public void setOptions3Items(ArrayList<ArrayList<ArrayList<JsonArea>>> options3Items) {
        this.options3Items = options3Items;
    }

}
