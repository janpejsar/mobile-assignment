package cz.quanti.spacexrockets_janpejsar.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.databinding.FragmentRocketListBinding
import cz.quanti.spacexrockets_janpejsar.ui.viewmodels.RocketListViewModel

class RocketListFragment: Fragment() {
    private lateinit var binding: FragmentRocketListBinding
    private lateinit var viewModel: RocketListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val provider = ViewModelProvider(this)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_list, container, false)

        return binding.root
    }
}