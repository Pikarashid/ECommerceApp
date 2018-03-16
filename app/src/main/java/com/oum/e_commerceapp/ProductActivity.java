package com.oum.e_commerceapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.oum.e_commerceapp.adapter.CategoryAdapter;
import com.oum.e_commerceapp.adapter.ProductAdapter;
import com.oum.e_commerceapp.modal.ProductDomain;

import java.util.ArrayList;

public class ProductActivity extends Activity {
    GridView gridView;
    TextView txtItemCount;
    int itemCount;

    ArrayList<ProductDomain> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        productList = new ArrayList<>();

        gridView = findViewById(R.id.grid_product);

        int position = getIntent().getIntExtra("position", 0);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.description_layout, null));
        builder.create();

        Toast.makeText(getApplicationContext(), String.valueOf(position), Toast.LENGTH_LONG).show();

        ProductDomain productDomain;

        switch (position) {
            case 0:

                String[] clothList = {"SHIRT", "BLOUSE", "SWEATER", "CAP", "TSHIRT", "SWEATSHIRT"};
                int[] clothimageList = {R.drawable.shirt, R.drawable.blouse, R.drawable.sweater, R.drawable.cap, R.drawable.tshirt, R.drawable.sweatshirt};
                String[] clothpriceList = {"RM 55", "RM 60", "RM 100", "RM 30", "RM 45", "RM 65"};

                for (int i = 0; i < clothList.length; i++) {

                    productDomain = new ProductDomain(clothList[i], clothpriceList[i], clothimageList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;


            case 1:

                String[] electronicsList = {"SAMSUNG", "PANASONIC", "TOSHIBA", "SONY"};
                int[] electroimageList = {R.drawable.tv, R.drawable.fan, R.drawable.washer, R.drawable.headphone};
                String[] electropriceList = {"RM 2200", "RM 200", "RM 1800", "RM 800"};

                for (int i = 0; i < electronicsList.length; i++) {

                    productDomain = new ProductDomain(electronicsList[i], electropriceList[i], electroimageList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;


            case 2:

                String[] softwareList = {"MICROSOFT", "ADOBE", "ANTIVIRUS", "WINDOW"};
                int[] softwareimageList = {R.drawable.microsoft, R.drawable.photoshop, R.drawable.antivirus, R.drawable.window};
                String[] softwarepriceList = {"RM 100", "RM 60", "RM 80", "RM 150"};

                for (int i = 0; i < softwareList.length; i++) {

                    productDomain = new ProductDomain(softwareList[i], softwarepriceList[i], softwareimageList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;


            case 3:

                String[] cellphonesList = {"SAMSUNG", "HUAWEI", "SONY", "IPHONEX"};
                int[] cellimageList = {R.drawable.galaxy, R.drawable.huawei, R.drawable.xperia, R.drawable.iphone};
                String[] cellpriceList = {"RM 2200", "RM 1400", "RM 1800", "RM 9990"};

                for (int i = 0; i < cellphonesList.length; i++) {

                    productDomain = new ProductDomain(cellphonesList[i], cellpriceList[i], cellimageList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;


            default:

                String[] automobilesList = {"TOYOTA", "PROTON", "HONDA", "HYUNDAI"};
                int[] autoimageList = {R.drawable.vios, R.drawable.persona, R.drawable.civic, R.drawable.hyundai};
                String[] autopriceList = {"RM 77120", "RM 49325", "RM 96540", "RM 114200"};

                for (int i = 0; i < automobilesList.length; i++) {

                    productDomain = new ProductDomain(automobilesList[i], autopriceList[i], autoimageList[i]);
                    productList.add(productDomain);
                }

                gridView.setAdapter(new ProductAdapter(productList, getApplicationContext()));

                break;
        }
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //Toast.makeText(getApplicationContext(),"clicked",Toast.LENGTH_LONG).show();
                productDetails(productList.get(i).getProductName(), productList.get(i).getProductPrice()
                        , productList.get(i).getImageId(), i);
            }
        });
    }

    public void productDetails(String productName, String productPrice, int imgId, final int position) {
        final AlertDialog alert;
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        //Get the layout inflater
        LayoutInflater inflater = (LayoutInflater) this
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        //Inflate and set the layout for the dialog
        //Pass null as the parent view because its going in the dialog layout
        View view = inflater.inflate(R.layout.description_layout, null);

        builder.setView(view);
        alert = builder.create();
        alert.show();

        TextView txtProduct = view.findViewById(R.id.txt_product_name);
        TextView txtPrice = view.findViewById(R.id.txt_price);
        ImageView imageView = view.findViewById(R.id.img_product);
        Button btnCart = view.findViewById(R.id.button);


        txtProduct.setText(productName);
        txtPrice.setText(productPrice);
        imageView.setImageResource(imgId);

        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {

              //cartArray.add(productList.get(position));
              itemCount++;
              updateHotCount(itemCount);
              alert.dismiss();
            }

        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.cart_menu, menu);

        final View notifications = menu.findItem(R.id.cart_item).getActionView();

        txtItemCount = (TextView) notifications.findViewById(R.id.cart_badge);
        updateHotCount(itemCount++);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle item selection
        switch (item.getItemId()) {
            case R.id.cart_item:
                //newGame();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    public void updateHotCount(final int new_number){
        itemCount = new_number;
        if (itemCount < 0) return;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(itemCount == 0)
                    txtItemCount.setVisibility(View.GONE);
                else{
                    txtItemCount.setVisibility(View.VISIBLE);
                    txtItemCount.setText(Integer.toString(itemCount));
                    //supportInvalidateOptionsMenu();
                }
            }
        });
    }
}
