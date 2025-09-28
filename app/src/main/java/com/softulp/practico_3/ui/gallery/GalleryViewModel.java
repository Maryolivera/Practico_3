package com.softulp.practico_3.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.softulp.practico_3.MainActivity;
import com.softulp.practico_3.modelo.Producto;

import java.util.ArrayList;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Producto>> mLista = new MutableLiveData<>();

    public LiveData<ArrayList<Producto>> getMLista() {
        return mLista;
    }


    public void cargarLista() {
        ArrayList<Producto> stock = new ArrayList<>(MainActivity.listaProductos);
        mLista.setValue(stock);
    }
}
