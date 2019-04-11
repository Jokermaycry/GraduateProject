package com.wangweiming.myapplication.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wangweiming.myapplication.R;
import com.wangweiming.myapplication.model.Bookbean;
import com.wangweiming.myapplication.model.ShopbusProducbean;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class ProductDetailActivity extends AppCompatActivity {
    private Toolbar tb;
    private ImageView mIvProductImage;
    private TextView mTvProductTitle;
    private TextView mTvProductSubTitle;
    private TextView mTvPrice;
    private Bookbean bookbean;
    private String productId;
    private ShopbusProducbean shopbusProducbean;
    private boolean isAddShopBus = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
        init();
        initObjectID();
        getData();
        initToolBar();
        initGoBackClickListner();
        initaddShopBusClickListener();
    }
    private void addtoShopBus() {
        shopbusProducbean = new ShopbusProducbean(BmobUser.getCurrentUser(BmobUser.class),bookbean,1);
        shopbusProducbean.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null){
                    Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),"添加失败",Toast.LENGTH_SHORT).show();
                    Log.e("qpf","添加购物车失败 -- " + e.toString());
                }
            }
        });
    }


    private void showNormalDialog() {
        final AlertDialog.Builder normalDialog = new AlertDialog.Builder(getApplicationContext());
        normalDialog.setIcon(R.drawable.ic_launcher);
        normalDialog.setTitle("提示");
        normalDialog.setMessage("该商品已在您的购物车中,是否继续添加？");
        normalDialog.setPositiveButton("确定",
            new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        dialog.dismiss();
                        ShopbusProducbean shopbusProducbean=new ShopbusProducbean();
                        shopbusProducbean.setObjectId(shopbusProducbean.getObjectId());
                        shopbusProducbean.increment("num",1);
                        shopbusProducbean.update(shopbusProducbean.getObjectId(), new UpdateListener() {
                            @Override
                            public void done(BmobException e) {
                                if (e == null){
                                    Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(),"添加失败",Toast.LENGTH_SHORT).show();
                                    Log.e("qpf","加入购物车失败 -- " + e.toString());
                                }
                            }
                        });
                    }
                });

        // 显示
        normalDialog.show();
    }

    private void addShopBus(){

        //TODO
        if(BmobUser.getCurrentUser(BmobUser.class)!=null)
        {
            BmobQuery<ShopbusProducbean> query = new BmobQuery<>();
            query.addWhereEqualTo("user",BmobUser.getCurrentUser(BmobUser.class));
            query.findObjects(new FindListener<ShopbusProducbean>() {
                @Override
                public void done(final List<ShopbusProducbean> list, BmobException e) {
                    if (e == null){
                        for (int i = 0;i < list.size();i++){
                            ProductDetailActivity.this.shopbusProducbean = list.get(i);
                            if (productId.equals(shopbusProducbean.getBookbean().getObjectId())){
                                isAddShopBus = true;
                                break;
                            }else {
                                isAddShopBus = false;
                            }
                        }


                        if (!isAddShopBus) {
                        addtoShopBus();
                        }else {
                        showNormalDialog();
                        }


                    }
                    else
                    {
                        Log.e("wangweiming","not found");
                    }
                }
            });
        }
        else
        { gotoLogin();}
    }
    private void gotoLogin() {

        final AlertDialog.Builder normalDialog =
                new AlertDialog.Builder(getApplicationContext());
        normalDialog.setIcon(R.drawable.ic_launcher);
        normalDialog.setTitle("未登录");
        normalDialog.setMessage("请先登陆");
        normalDialog.setPositiveButton("确定",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //...To-do
                        startActivity(new Intent(getApplicationContext(), user_denglu.class));
                    }
                });

        // 显示
        normalDialog.show();


    }
    private void initaddShopBusClickListener() {
        findViewById(R.id.tv_add_shop_bus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addShopBus();
                Log.e("wangweiming","addShopBus");
            }
        });
    }
    private void initGoBackClickListner() {
        findViewById(R.id.tv_go_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void initToolBar() {
        tb=(Toolbar)findViewById(R.id.toolbar);
        tb.setTitle("商品详情");
        tb.setNavigationIcon(R.drawable.bar_goback);
        tb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }
    private  void getData(){
        BmobQuery<Bookbean> query = new BmobQuery<>();
        query.addWhereEqualTo("objectId",productId);
        query.findObjects(new FindListener<Bookbean>() {
            @Override
            public void done(List<Bookbean> list, BmobException e) {
                if (e == null){
                    bookbean = list.get(0);
                    Glide.with(ProductDetailActivity.this).load(bookbean.getImageurl()).into(mIvProductImage);
                    mTvProductTitle.setText(bookbean.getName());

                    mTvProductSubTitle.setText(bookbean.getDetail());

                    mTvPrice.setText("￥"+bookbean.getPrice());
                }
                else {
                    Log.e("wangweiming","error");
                }

            }
        });
    }
    private  void init() {
        mIvProductImage = findViewById(R.id.iv_product_image);
        mTvProductTitle = findViewById(R.id.tv_product_title);
        mTvProductSubTitle = findViewById(R.id.tv_product_sub_title);
        mTvPrice = findViewById(R.id.tv_price);
    }
    private  void initObjectID() {
        productId = getIntent().getStringExtra("productId");

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        productId = intent.getStringExtra("productId");
        getData();
    }
}
