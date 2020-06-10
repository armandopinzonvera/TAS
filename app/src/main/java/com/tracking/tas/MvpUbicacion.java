package com.tracking.tas;

public interface MvpUbicacion {

    interface intView{
       void showInformation();
    }
    interface intModel{
        void makeInformation();
    }
    interface presenter{
        void joinInformation();

    }


}
