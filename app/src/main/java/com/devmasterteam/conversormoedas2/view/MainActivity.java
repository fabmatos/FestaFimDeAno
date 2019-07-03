package com.devmasterteam.conversormoedas2.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.devmasterteam.conversormoedas2.R;
import com.devmasterteam.conversormoedas2.constant.FimDeAnoConstants;
import com.devmasterteam.conversormoedas2.data.SecurityPreferences;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private SecurityPreferences mSecurityPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mSecurityPreferences = new SecurityPreferences(this);

        this.mViewHolder.textToday = findViewById(R.id.text_data);
        this.mViewHolder.textDaysLeft = findViewById(R.id.text_days_left);
        this.mViewHolder.buttonConfirm = findViewById(R.id.button_confirm);

        this.mViewHolder.buttonConfirm.setOnClickListener(this);
        //Pega o dia de hoje e seta no inicio da main activity
        this.mViewHolder.textToday.setText(SIMPLE_DATE_FORMAT.format(Calendar.getInstance().getTime()));

        //Pega a quantidade de dias restante, concatena com " dias" ao final e mostra na main activity
        String daysLeft = String.format("%s %s", String.valueOf(this.getDaysLeft()), getString(R.string.dias));
        this.mViewHolder.textDaysLeft.setText(daysLeft);

    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.verifyPresence();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.button_confirm){

            String presence = this.mSecurityPreferences.getStorageString(FimDeAnoConstants.PRESENCE_KEY);

            Intent intent  = new Intent(this, DetailsActivity.class);
            intent.putExtra(FimDeAnoConstants.PRESENCE_KEY,presence);
            startActivity(intent);
        }
    }

    private void verifyPresence() {
        //Nao confirmado, sim e não
        String presence = this.mSecurityPreferences.getStorageString(FimDeAnoConstants.PRESENCE_KEY);
        if(presence.equals("")){
            mViewHolder.buttonConfirm.setText(R.string.nao_confirmado);
        }else if(presence.equals(FimDeAnoConstants.CONFIRMATION_YES)){
            mViewHolder.buttonConfirm.setText(R.string.sim);
        }else if(presence.equals(FimDeAnoConstants.CONFIRMATION_NO)){
            mViewHolder.buttonConfirm.setText(R.string.nao);
        }
    }


    private int getDaysLeft(){
        //Data de hoje
        Calendar calendarToday = Calendar.getInstance();
        int today = calendarToday.get(Calendar.DAY_OF_YEAR);//Pega o dia do ano
        //Dia máximo do ano
        Calendar calendarLastDay = Calendar.getInstance();
        int dayMax = calendarLastDay.getActualMaximum(Calendar.DAY_OF_YEAR);

        return dayMax - today;
    }

    private static class ViewHolder{
        TextView textToday;
        TextView textDaysLeft;
        Button buttonConfirm;
    }
}
