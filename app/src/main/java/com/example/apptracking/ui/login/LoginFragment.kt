package com.example.apptracking.ui.login

import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.apptracking.R
import com.example.apptracking.databinding.FragmentLoginBinding
import com.google.android.material.textfield.TextInputEditText


class LoginFragment : Fragment() {
    lateinit var binding: FragmentLoginBinding
    private var isPassVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        val rootView = binding.root
        viewPassword()

        return rootView
    }

    private fun viewPassword(){
        binding.passwordTextInputLayout.setEndIconOnClickListener{
            isPassVisible = !isPassVisible
            togglePasswordVisible(binding.logPass)
        }
    }

    private fun togglePasswordVisible(editText: TextInputEditText) {
        if (isPassVisible) editText.transformationMethod = null // transformationMethod = null thì xem dc text
        else editText.transformationMethod = PasswordTransformationMethod.getInstance()
        editText.text?.let { editText.setSelection(it.length) } //di chuyển con trỏ về cuối text
    }

}