package com.example.criminalintent

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.*
import androidx.core.view.isEmpty
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.criminalintent.databinding.FragmentCrimeListBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import java.util.*

class CrimeListFragment : Fragment(R.layout.fragment_crime_list) {
    private val crimeListViewModel: CrimeListViewModel by viewModels()
    private val binding: FragmentCrimeListBinding by viewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.crimeRecycleView.layoutManager = LinearLayoutManager(context)

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                crimeListViewModel.crimes.collect { crimes ->
                    binding.crimeRecycleView.adapter = CrimeListAdapter(crimes) { crimeId ->
                        findNavController().navigate(
                            CrimeListFragmentDirections.showCrimeDetail(crimeId)
                        )
                    }
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.newCrime -> {
                showNewCrime()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showNewCrime() {
        viewLifecycleOwner.lifecycleScope.launch {
            val newCrime = Crime(
                id = UUID.randomUUID(),
                title = "",
                date = Date(),
                isSolved = false
            )
            crimeListViewModel.addCrime(newCrime)
            findNavController().navigate(
                CrimeListFragmentDirections.showCrimeDetail(newCrime.id)
            )
        }
    }
}