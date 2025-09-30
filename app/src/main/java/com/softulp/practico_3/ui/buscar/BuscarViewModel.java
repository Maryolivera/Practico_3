package com.softulp.practico_3.ui.buscar;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.softulp.practico_3.MainActivity;
import com.softulp.practico_3.modelo.Producto;

public class BuscarViewModel extends AndroidViewModel {

    private MutableLiveData<Producto> productoEncontrado;
    private MutableLiveData<String> mensaje;

    public BuscarViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Producto> getProductoEncontrado() {
        if (productoEncontrado == null) {
            productoEncontrado = new MutableLiveData<>();
        }
        return productoEncontrado;
    }

    public LiveData<String> getMensaje() {
        if (mensaje == null) {
            mensaje = new MutableLiveData<>();
        }
        return mensaje;
    }

    public void buscarProducto(String codigo) {
        Producto encontrado = null;

        for (Producto p : MainActivity.listaProductos) {
            if (p.getCodigo().equals(codigo)) {
                encontrado = p;
                break;
            }
        }

        if (encontrado != null) {
            productoEncontrado.setValue(encontrado);
        } else {
            mensaje.setValue("No se encontró un producto con ese código");
        }
    }
}



