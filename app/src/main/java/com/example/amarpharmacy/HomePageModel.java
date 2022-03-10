package com.example.amarpharmacy;

import java.util.List;

public class HomePageModel {
    public static final int BANNER_SLIDER = 0;
    public static final int HORIZONTAL_PRODUCT_VIEW = 1;
    public static final int GRID_PRODUCT_VIEW = 2;

    private int type;
    private String backgroundColor;
    //Banner Slider Start
    private List<SliderModel> sliderModelList;

    public HomePageModel(int type, List<SliderModel> SliderModelList) {
        this.type = type;
        this.sliderModelList = SliderModelList;
    }

    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public List<SliderModel> getSliderModelList() {
        return sliderModelList;
    }
    public void setSliderModelList(List<SliderModel> sliderModelList) {
        this.sliderModelList = sliderModelList;
    }
    //Banner Slider End


    /////horizontal product Layout && GRID Product Layout

    private String title;
    private List<HorizontalProductModel> horizontalProductModelList;

    public HomePageModel(int type, String title,String backgroundColor, List<HorizontalProductModel> horizontalProductModelList) {
        this.type = type;
        this.title = title;
        this.backgroundColor = backgroundColor;
        this.horizontalProductModelList = horizontalProductModelList;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(String backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<HorizontalProductModel> getHorizontalProductModelList() {
        return horizontalProductModelList;
    }
    public void setHorizontalProductModelList(List<HorizontalProductModel> horizontalProductModelList) {
        this.horizontalProductModelList = horizontalProductModelList;
    }

    /////horizontal product Layout && GRID Product Layout





}
