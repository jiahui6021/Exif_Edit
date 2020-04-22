package com.example.pic;

import android.content.Intent;
import androidx.exifinterface.media.ExifInterface;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class GetExfi {
    List list;
    public List getExfi(String path){
        try{
            list = new ArrayList();
            ExifInterface exifInterface = new ExifInterface(path);
            String Orientation=exifInterface.getAttribute(ExifInterface.TAG_ORIENTATION);
            String DateTime=exifInterface.getAttribute(ExifInterface.TAG_DATETIME);
            String Make=exifInterface.getAttribute(ExifInterface.TAG_MAKE);
            String Model=exifInterface.getAttribute(ExifInterface.TAG_MODEL);
            String Flash=exifInterface.getAttribute(ExifInterface.TAG_FLASH);
            String ImageWidth=exifInterface.getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
            String ImageLength=exifInterface.getAttribute(ExifInterface.TAG_IMAGE_LENGTH);
            String ExposureTime=exifInterface.getAttribute(ExifInterface.TAG_EXPOSURE_TIME);
            String FNumber=exifInterface.getAttribute(ExifInterface.TAG_APERTURE_VALUE);
            String ISOSpeedRatings=exifInterface.getAttribute(ExifInterface.TAG_ISO_SPEED);
            String GPSLatitude=exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
            String GPSLongitude=exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);

            list.add(new Msg("方向",0,Orientation));
            list.add(new Msg("文件日期",1,DateTime));
            list.add(new Msg("制造商",2,Make));
            list.add(new Msg("设备型号",3,Model));
            list.add(new Msg("闪光灯",4,Flash));
            list.add(new Msg("图像宽度",5,ImageWidth));
            list.add(new Msg("图像长度",6,ImageLength));
            list.add(new Msg("曝光时间",7,ExposureTime));
            list.add(new Msg("光圈值",8,FNumber));
            list.add(new Msg("感光度",9,ISOSpeedRatings));
            list.add(new Msg("纬度",10,getgps(GPSLatitude)));
            list.add(new Msg("经度",11,getgps(GPSLongitude)));
            list.add(new Msg("数字化时间",12,exifInterface.getAttribute(ExifInterface.TAG_DATETIME_DIGITIZED)));
            list.add(new Msg("拍摄时间",13,exifInterface.getAttribute(ExifInterface.TAG_DATETIME_ORIGINAL)));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
    public void setexif(int id,String path,String data){
        try{
            ExifInterface exifInterface = new ExifInterface(path);
            Log.d("setexfi", "id:"+id+"path:"+path+"data:"+data);
            switch(id){
                case 0:
                    exifInterface.setAttribute(ExifInterface.TAG_ORIENTATION,data);
                    break;
                case 1:
                    exifInterface.setAttribute(ExifInterface.TAG_DATETIME,data);
                    break;
                case 2:
                    exifInterface.setAttribute(ExifInterface.TAG_MAKE,data);
                    break;
                case 3:
                    exifInterface.setAttribute(ExifInterface.TAG_MODEL,data);
                    break;
                case 4:
                    exifInterface.setAttribute(ExifInterface.TAG_FLASH,data);
                    break;
                case 5:
                    exifInterface.setAttribute(ExifInterface.TAG_IMAGE_WIDTH,data);
                    break;
                case 6:
                    exifInterface.setAttribute(ExifInterface.TAG_IMAGE_LENGTH,data);
                    break;
                case 7:
                    exifInterface.setAttribute(ExifInterface.TAG_EXPOSURE_TIME,data);
                    break;
                case 8:
                    exifInterface.setAttribute(ExifInterface.TAG_APERTURE_VALUE,data);
                    break;
                case 9:
                    exifInterface.setAttribute(ExifInterface.TAG_ISO_SPEED,data);
                    break;
                case 10:
                    data=regetgps(data);
                    exifInterface.setAttribute(ExifInterface.TAG_GPS_LATITUDE,data);
                    break;
                case 11:
                    data=regetgps(data);
                    exifInterface.setAttribute(ExifInterface.TAG_GPS_LONGITUDE,data);
                    break;
                case 12:
                    exifInterface.setAttribute(ExifInterface.TAG_DATETIME_DIGITIZED,data);
                case 13:
                    exifInterface.setAttribute(ExifInterface.TAG_DATETIME_ORIGINAL,data);
            }
            exifInterface.saveAttributes();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    private String getgps(String x){
        if(x==null)
            return null;
        int d,dd;
        try {
            String[] data=x.split("/");
            d=Integer.parseInt(data[0]);
            dd=Integer.parseInt(data[1].split(",")[0]);
            d/=dd;
            String xx=String.valueOf(d);
            String[] data2=data[1].split(",");
            d=Integer.parseInt(data2[1]);
            dd=Integer.parseInt(data[2].split(",")[0]);
            d/=dd;
            String yy=String.valueOf(d);
            data2=data[2].split(",");
            d= Integer.parseInt(data2[1]);
            dd=Integer.parseInt(data[3]);
            d/=dd;
            String zz=String.valueOf(d);
            return xx+"°"+yy+"′"+zz+"″";
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    private String regetgps(String x){
        if(x==""){
            return null;
        }
        if(x=="null")
            return null;
        try{
            String[] data=x.split("°");
            String xx=data[0];
            String[] data2=data[1].split("′");
            String yy=data2[0];
            data=data2[1].split("″");
            String zz=data[0];
            Log.d("regetgps", "regetgps: "+xx+"/1,"+yy+"/1,"+zz+"0000/10000");
            return xx+"/1,"+yy+"/1,"+zz+"00/100";
        } catch (Exception e){
            e.printStackTrace();
        }
        return "1";
    }
}
