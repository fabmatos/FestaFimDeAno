package com.devmasterteam.conversormoedas2.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.devmasterteam.conversormoedas2.R;
import com.devmasterteam.conversormoedas2.constant.FimDeAnoConstants;
import com.devmasterteam.conversormoedas2.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mvieViewHolder = new ViewHolder();
    private SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private SecurityPreferences mSecurityPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mvieViewHolder.textData = findViewById(R.id.text_data);
        this.mvieViewHolder.textData.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        this.mvieViewHolder.detailsCheck = findViewById(R.id.details_check);
        this.mvieViewHolder.detailsCheck.setOnClickListener(this);

        loadDataFromActivity();
    }

    private void loadDataFromActivity() {
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            String presence = extras.getString(FimDeAnoConstants.PRESENCE_KEY);
            if(presence != null && presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
                this.mvieViewHolder.detailsCheck.setChecked(true);
            }else{
                this.mvieViewHolder.detailsCheck.setChecked(false);

            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.details_check){
            if(this.mvieViewHolder.detailsCheck.isChecked()){
                //Salvar presenca
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY,FimDeAnoConstants.CONFIRMATION_YES);
            }
            else {
                //Salvar ausencia
                //this.mvieViewHolder.detailsCheck.setChecked(false);
                this.mSecurityPreferences.storeString(FimDeAnoConstants.PRESENCE_KEY,FimDeAnoConstants.CONFIRMATION_NO);

            }
        }
    }

    private static class ViewHolder{
        CheckBox detailsCheck;
        TextView textData;

    }
}
