package com.bw.anmoationdemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

/**
 * Created by 贾秀坤 on 2017/6/9.
 */

public class ZxingActivity extends Activity {
    private TextView textView;
    private ImageView qrImgImageView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.zxing);

        //打开扫描界面扫描条形码或二维码

        textView = (TextView) findViewById(R.id.result_button_view);

        qrImgImageView = (ImageView)findViewById(R.id.gen_id);

    }
    //扫描
    public void scannner(View view){
        //开启相机
        Intent openCameraIntent = new Intent(this, CaptureActivity.class);
        startActivityForResult(openCameraIntent, 0);
    }

    public void gener_pic(View view){
        String contentString = "可以了噢~";
        if (!contentString.equals("")) {
            //根据字符串生成二维码图片并显示在界面上，第二个参数为图片的大小（350*350）
            Bitmap qrCodeBitmap = EncodingUtils.createQRCode(contentString, 350, 350,
                    BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
            qrImgImageView.setImageBitmap(qrCodeBitmap);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            String scanResult = bundle.getString("result");
            textView.setText(scanResult);
        }
    }
}
