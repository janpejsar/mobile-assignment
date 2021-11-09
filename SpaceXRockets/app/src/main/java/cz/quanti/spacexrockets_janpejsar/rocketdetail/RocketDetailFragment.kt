package cz.quanti.spacexrockets_janpejsar.rocketdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import cz.quanti.spacexrockets_janpejsar.R
import cz.quanti.spacexrockets_janpejsar.databinding.FragmentRocketDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RocketDetailFragment: Fragment() {
    private lateinit var binding: FragmentRocketDetailBinding
    private val viewModel: RocketDetailViewModel by viewModels()
    private val args: RocketDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel.loadRocket(args.rocketId)

        if (args.rocketName != null) {
            requireActivity().title = args.rocketName
        }

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_rocket_detail, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }
}