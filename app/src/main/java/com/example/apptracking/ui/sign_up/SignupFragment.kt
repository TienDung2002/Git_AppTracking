package com.example.apptracking.ui.sign_up

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import com.example.apptracking.R
import com.example.apptracking.databinding.FragmentSignupBinding
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class SignupFragment : Fragment() {
    lateinit var binding: FragmentSignupBinding
    private var isPassVisible = false
    private var isReEnterPassVisble = false
    private var isDatePickerShowing = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)
        val rootView = binding.root

        binding.userBirthday.setOnClickListener {
            showDatePickerDialog()
        }

        binding.passwordTextInputLayout.setOnClickListener{
            togglePasswordVisibility(binding.logPass, isVisible)
            isPassVisible = !isPassVisible
        }

        binding.reEnterPassTextInputLayout.setOnClickListener{
            togglePasswordVisibility(binding.reEnterPass, isReEnterPassVisble)
            isReEnterPassVisble = !isReEnterPassVisble
        }
        return rootView
    }

    private fun showDatePickerDialog() {
        if (!isDatePickerShowing) {
            isDatePickerShowing = true

            // giới hạn, không cho chọn ngày sinh tương lai
            val constraintsBuilder = CalendarConstraints.Builder()
            constraintsBuilder.setEnd(System.currentTimeMillis())

            val builder = MaterialDatePicker.Builder.datePicker()
                .setTitleText(R.string.signup_birth)
                .setSelection(MaterialDatePicker.todayInUtcMilliseconds())
                .setInputMode(MaterialDatePicker.INPUT_MODE_CALENDAR)
                .setCalendarConstraints(constraintsBuilder.build())
                .build()

            builder.addOnPositiveButtonClickListener { selection ->
                val formattedDate = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                    .format(Date(selection))
                binding.userBirthday.setText(formattedDate)
                isDatePickerShowing = false
            }

            builder.addOnDismissListener { isDatePickerShowing = false }
            builder.addOnCancelListener { isDatePickerShowing = false }
            builder.show(parentFragmentManager, builder.toString())
        }
    }

    private fun togglePasswordVisibility(editText: TextInputEditText, isVisible: Boolean) {
        if (isVisible) editText.transformationMethod = null
        else editText.transformationMethod = PasswordTransformationMethod.getInstance()
        editText.text?.let { editText.setSelection(it.length) }
    }
}