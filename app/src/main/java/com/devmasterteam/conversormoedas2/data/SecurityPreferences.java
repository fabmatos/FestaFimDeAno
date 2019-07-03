package com.devmasterteam.conversormoedas2.data;

import android.content.Context;
import android.content.SharedPreferences;

public class SecurityPreferences {

    //Instancia para armazenar dados simples, apos a aplicacao ser fechada. Um banco de dados simples.
    private SharedPreferences mSharedPreferencies;

    public SecurityPreferences(Context mContext){
        //ModePrivate: Ninguem fora da aplicação possui acesso ao sharedPreferencies
        this.mSharedPreferencies = mContext.getSharedPreferences("festaFimAno", Context.MODE_PRIVATE);
    }

    public void storeString(String key, String value){
        this.mSharedPreferencies.edit().putString(key,value).apply();
    }

    public String getStorageString(String key){
        //Def value eh o valor default retornado caso ele nao encontre a chave
        return this.mSharedPreferencies.getString(key,"");
    }
}
