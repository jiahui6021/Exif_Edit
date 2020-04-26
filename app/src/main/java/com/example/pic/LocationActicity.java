package com.example.pic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.LocationSource;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;

public class LocationActicity extends AppCompatActivity implements LocationSource, AMap.OnMapClickListener {

    private MapView mapview;
    private AMap amap;
    private TextView textView;
    private Button button;
    private Marker marker;
    private String gpsx;
    private String gpsy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_acticity);
        mapview = findViewById(R.id.location_map);
        mapview.onCreate(savedInstanceState);
        textView = findViewById(R.id.location_text);
        button = findViewById(R.id.location_button);
        if(amap==null){
            amap=mapview.getMap();
            setMapAttribute();
        }
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                intent.putExtra("x",gpsx);
                intent.putExtra("y",gpsy);
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void setMapAttribute(){
        amap.setOnMapClickListener(this);
    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(marker!=null){
            marker.remove();
        }
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(latLng);
        marker=amap.addMarker(markerOptions);
        textView.setText("纬度："+latLng.latitude+"经度"+latLng.longitude);
        gpsx=makegps(latLng.latitude);
        gpsy=makegps(latLng.longitude);
    }
    private String makegps(double x){
        String xx=String.valueOf(x);
        String[] strings=xx.split("\\.");
        String ret=strings[0]+"°";
        x=Double.parseDouble("0."+strings[1]);
        x*=60;
        xx=String.valueOf(x);
        strings=xx.split("\\.");
        ret+=strings[0];
        ret+="′";
        x=Double.parseDouble("0."+strings[1]);
        x*=60;
        xx=String.valueOf(x);
        strings=xx.split("\\.");
        ret+=strings[0];
        ret+="″";
        return ret;
    }
    @Override
    public void activate(OnLocationChangedListener onLocationChangedListener) {

    }

    @Override
    public void deactivate() {

    }
}
