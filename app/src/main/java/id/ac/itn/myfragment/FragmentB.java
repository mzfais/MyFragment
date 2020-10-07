package id.ac.itn.myfragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FragmentB extends Fragment {
    Button btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_b, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btn = view.findViewById(R.id.btnToC);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentC fragmentC = new FragmentC();
                Bundle bundle = new Bundle();
                bundle.putString(FragmentC.EXTRA_NAME, "Data dikirim melalui Bundle");
                fragmentC.setArguments(bundle);
                fragmentC.setDescription("data dikirim melalui SETTER method");
                FragmentManager fm = getFragmentManager();
                if (fm != null) {
                    fm.beginTransaction()
                            .replace(R.id.frame_container, fragmentC, FragmentC.class.getSimpleName())
                            .addToBackStack(null)
                            .commit();
                }
            }
        });
    }
}