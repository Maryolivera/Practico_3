package com.softulp.practico_3.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.softulp.practico_3.databinding.FragmentGalleryBinding;
import com.softulp.practico_3.modelo.Producto;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;
    private GalleryViewModel mv;
    private ListaAdapter la;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        mv = new ViewModelProvider(this).get(GalleryViewModel.class);
        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        binding.rvLista.setLayoutManager(new GridLayoutManager(getContext(), 1));
        la = new ListaAdapter(new ArrayList<>(), getLayoutInflater(), getContext());
        binding.rvLista.setAdapter(la);


        mv.getMLista().observe(getViewLifecycleOwner(), new Observer<ArrayList<Producto>>() {
            @Override
            public void onChanged(ArrayList<Producto> productos) {
                la = new ListaAdapter(productos, getLayoutInflater(), getContext());
                binding.rvLista.setAdapter(la);
            }
        });

        mv.cargarLista();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

