package com.example.mapandlocationexample;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

public class Geo {
    Geocoder gc;

    LatLng geo(String addr, Context context) throws IOException {
        gc=new Geocoder(context);
        if (!(gc.isPresent())){
            return null;
        }
        List<Address> list=gc.getFromLocationName(addr,1);
        Address address=list.get(0);
        return new LatLng(address.getLatitude(),address.getLongitude());
    }
    Address reversegeo(double lat,double lng,Context context) throws IOException {
        gc=new Geocoder(context);
        if (!(gc.isPresent())){
            return null;
        }
        List<Address> list = gc.getFromLocation(lat,lng,1);
        Address address=list.get(0);
        return address;


    }

}
