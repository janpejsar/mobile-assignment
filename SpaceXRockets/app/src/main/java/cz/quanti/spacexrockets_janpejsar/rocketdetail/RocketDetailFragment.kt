package cz.quanti.spacexrockets_janpejsar.rocketdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.databinding.FragmentRocketDetailBinding

class RocketDetailFragment: Fragment() {
    private lateinit var binding: FragmentRocketDetailBinding
    private lateinit var viewModel: RocketDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val provider = ViewModelProvider(this)
        viewModel = provider.get(RocketDetailViewModel::class.java)

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_detail, container, false)

        return binding.root
    }
}