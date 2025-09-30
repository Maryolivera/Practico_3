package com.softulp.practico_3.ui.buscar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.softulp.practico_3.databinding.FragmentBuscarBinding;
import com.softulp.practico_3.ui.detalle.DetalleFragment;

public class BuscarFragment extends Fragment {

    private BuscarViewModel mv;
    private FragmentBuscarBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mv = new ViewModelProvider(this).get(BuscarViewModel.class);
        binding = FragmentBuscarBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 👉 Observa mensajes de error
        mv.getMensaje().observe(getViewLifecycleOwner(), s -> {
            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
        });

        // 👉 Observa producto encontrado
        mv.getProductoEncontrado().observe(getViewLifecycleOwner(), producto -> {
            if (producto != null) {
                Bundle bundle = new Bundle();
                bundle.putString("codigo", producto.getCodigo());
                bundle.putString("descripcion", producto.getDescripcion());
                bundle.putDouble("precio", producto.getPrecio());

                DetalleFragment detalleFragment = new DetalleFragment();
                detalleFragment.setArguments(bundle);

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(((ViewGroup) getView().getParent()).getId(), detalleFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        // 👉 Botón Buscar
        binding.btnBuscar.setOnClickListener(v -> {
            String codigo = binding.edtCodigo.getText().toString();
            mv.buscarProducto(codigo);
            binding.edtCodigo.setText(""); // limpiar campo tras búsqueda
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

