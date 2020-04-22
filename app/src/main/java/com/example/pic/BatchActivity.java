package com.example.pic;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Adapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class BatchActivity extends AppCompatActivity {
    private FloatingActionButton add;
    private FloatingActionButton submit;
    String path;
    List list;
    List<String> pathList;
    GetExfi getExfi;
    TextView first;
    FilelistAdapter adapter;
    RecyclerView recyclerView;
    int num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);
        getExfi=new GetExfi();
        pathList=new ArrayList<String>();
        add=(FloatingActionButton)findViewById(R.id.batch_add);
        submit=(FloatingActionButton)findViewById(R.id.batch_ok);
        //
        first=(TextView)findViewById(R.id.batch_first);
        recyclerView=(RecyclerView)findViewById(R.id.banch_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter =new FilelistAdapter(pathList);
        recyclerView.setAdapter(adapter);

        num=0;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,1);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(num>1){
                    if(batchcopy()==1){
                        Toast.makeText(BatchActivity.this,"克隆成功！",Toast.LENGTH_SHORT).show();
                        list.clear();
                        pathList.clear();
                        first.setText("  请点击右下角加号添加源文件与目标文件");
                        adapter.setlist(pathList);
                        recyclerView.setAdapter(adapter);
                        num=0;
                    }
                    else{
                        Toast.makeText(BatchActivity.this,"发生未知错误",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(BatchActivity.this,"请先点击右下角加号添加源文件和目标文件",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case 1:
                if(resultCode==RESULT_OK){
                    try {
                        //Toast.makeText(MainActivity.this,"返回",Toast.LENGTH_SHORT).show();
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        Cursor cursor = getContentResolver().query(selectedImage,
                                filePathColumn, null, null, null);//从系统表中查询指定Uri对应的照片
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        path = cursor.getString(columnIndex);  //获取照片路径
                        cursor.close();
                        Log.d("BatchActivity", "num: "+num+"path:"+path);
                        if(num==0){
                            list=getExfi.getExfi(path);
                            Log.d("BatchActivity", "getlist ");
                            first.setText(path);
                        }
                        else{
                            pathList.add(path);
                            adapter.notifyItemInserted(pathList.size()-1);
                            recyclerView.scrollToPosition(pathList.size()-1);
                        }
                        num++;
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
    protected int batchcopy(){
        try {
            Msg d;
            for(String path:pathList){
                for(int i=0;i<=13;i++){
                    d=(Msg)list.get(i);
                    getExfi.setexif(i,path,d.getMsg());
                }
            }
            return 1;
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
