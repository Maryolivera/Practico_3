package com.softulp.practico_3.ui.detalle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.softulp.practico_3.databinding.FragmentDetalleBinding;

public class DetalleFragment extends Fragment {

    private FragmentDetalleBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentDetalleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 👉 Recuperar argumentos enviados desde BuscarFragment
        if (getArguments() != null) {
            String codigo = getArguments().getString("codigo", "");
            String descripcion = getArguments().getString("descripcion", "");
            double precio = getArguments().getDouble("precio", 0.0);


            binding.txtCodigo.setText("Código: " + codigo);
            binding.txtDescripcion.setText("Nombre: " + descripcion);
            binding.txtPrecio.setText("Precio: " + precio);
        }

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
