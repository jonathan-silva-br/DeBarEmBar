package com.example.debarembar.view.ui.cadastro;

import android.text.Layout;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.material.textfield.TextInputEditText;

public class CadastroViewModel extends ViewModel {




    private MutableLiveData<String> mText;

    public CadastroViewModel() {

        mText = new MutableLiveData<>();
        mText.setValue("This is send fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}