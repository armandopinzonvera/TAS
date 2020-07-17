package com.tracking.tas;

public interface Interface {

    interface View{
        void setValuesMapUi(float latitude, float longitude);

        void xxShowResult(String result);
    }
    interface Presenter{
       void setValuesMap(float latitude, float longitude);


        void xxshowResultPresenter(String result);
        void xxcalculus(String data);
    }
    interface Model{
        void xxcalculus(String data);

       // void putData();
    }

}
