package com.example.criminalintent

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.criminalintent.databinding.FragmentPhotoDialogBinding
import java.io.File

class PhotoPickerFragment(private val photoCrime: File?) :
    DialogFragment(R.layout.fragment_photo_dialog) {
    private val binding: FragmentPhotoDialogBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoCrime?.toUri()?.let {
            binding.fullSizeCrimePhoto.setImageURI(photoCrime.toUri())
        }
    }

}