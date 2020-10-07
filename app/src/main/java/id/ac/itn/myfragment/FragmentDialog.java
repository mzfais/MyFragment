package id.ac.itn.myfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class FragmentDialog extends DialogFragment implements View.OnClickListener {
    Button btnChoose, btnClose;
    RadioGroup rgOptions;
    RadioButton rbYes, rbNo;
    OnOptionDialogListener optionDialogListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dialog, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnChoose = view.findViewById(R.id.btn_choose);
        btnClose = view.findViewById(R.id.btn_close);
        rgOptions = view.findViewById(R.id.rg_options);
        rbYes = view.findViewById(R.id.rb_yes);
        rbNo = view.findViewById(R.id.rb_no);
        btnChoose.setOnClickListener(this);
        btnClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_close:
                getDialog().cancel();
                break;
            case R.id.btn_choose:
                int checkedRadioButtonId = rgOptions.getCheckedRadioButtonId();
                if (checkedRadioButtonId != -1) {
                    String answer = null;
                    switch (checkedRadioButtonId) {
                        case R.id.rb_yes:
                            answer = rbYes.getText().toString().trim();
                            break;
                        case R.id.rb_no:
                            answer = rbNo.getText().toString().trim();
                            break;
                    }
                    if (optionDialogListener != null) {
                        optionDialogListener.onOptionChoose(answer);
                    }
                    getDialog().dismiss();
                }
                break;
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Fragment fragment = getParentFragment();
        if (fragment instanceof FragmentC) {
            FragmentC fragmentC = (FragmentC) fragment;
            this.optionDialogListener = fragmentC.onOptionDialogListener;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        this.optionDialogListener = null;
    }

    public interface OnOptionDialogListener {
        void onOptionChoose(String text);
    }
}