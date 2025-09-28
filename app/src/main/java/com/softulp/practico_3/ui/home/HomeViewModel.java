
package com.softulp.practico_3.ui.home;
import com.softulp.practico_3.modelo.Producto;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.softulp.practico_3.MainActivity;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private MutableLiveData<String> mMensaje;

    public LiveData<String> getMensaje() {
        if (mMensaje == null) {
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }


    public void cargarProducto(String codigo, String descripcion, String precio) {
        boolean valido = validar(codigo, descripcion, precio);

        if (valido) {
            MainActivity.listaProductos.add(
                    new Producto(codigo, descripcion, Double.parseDouble(precio))
            );
            mMensaje.setValue("Creado correctamente");
        }
    }


    private boolean validar(String codigo, String descripcion, String precio) {
        boolean duplicado = false;
        boolean valido = true;
        StringBuilder mensaje = new StringBuilder();


        if (codigo.isEmpty() || descripcion.isEmpty() || precio.isEmpty()) {
            mensaje.append("Todos los campos son obligatorios\n");
            valido = false;
        }

        for (Producto producto : MainActivity.listaProductos) {
            if (producto.getCodigo().equals(codigo)) {
                duplicado = true;
            }
        }

        if (duplicado) {
            mensaje.append("Ya existe un producto con ese código\n");
            valido = false;
        }

        if (!valido) {
            mMensaje.setValue(mensaje.toString());
        }

        return valido;
    }

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
