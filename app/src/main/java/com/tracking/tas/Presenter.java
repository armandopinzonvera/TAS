package com.tracking.tas;

import android.util.Log;

import com.tracking.tas.Fragments.FragMapa;

public class Presenter implements Interface.Presenter {

    private Interface.View view;
    private Interface.Model model;
    float latitudeP, longitudeP;

    public Presenter(Interface.View view){
        this.view = view;
        model = new FragMapa(this);
    }

    @Override
    public void setValuesMap(float latitude, float longitude) {
        this.latitudeP = latitude;
        this.longitudeP = longitude;
        view.setValuesMapUi(latitudeP, longitudeP);


    }


    ///////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////////////////////////////

    public void xxshowResultPresenter(String result){
        if (view != null){
            view.xxShowResult(result);
            //model.sentData();

        }
    }
    public void xxcalculus(String data){
        if (view != null){
            model.xxcalculus(data);
        }
    }



}
