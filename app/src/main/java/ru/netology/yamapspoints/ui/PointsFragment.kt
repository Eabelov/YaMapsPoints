package ru.netology.yamapspoints.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ru.netology.yamapspoints.R
import ru.netology.yamapspoints.adapter.PointsAdapter
import ru.netology.yamapspoints.databinding.PointsFragmentBinding
import ru.netology.yamapspoints.listener.OnInteractionListener
import ru.netology.yamapspoints.viewmodel.ViewModel
import ru.netology.yamapspoints.ui.MapFragment.Companion.LATITUDE_KEY
import ru.netology.yamapspoints.ui.MapFragment.Companion.LONGITUDE_KEY

class PointsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = PointsFragmentBinding.inflate(inflater, container, false)

        val viewModel by viewModels<ViewModel>()

        val adapter = PointsAdapter(object : OnInteractionListener {

            override fun onClick(point: ru.netology.yamapspoints.dto.Point) {
                findNavController().navigate(
                    R.id.action_pointsFragment_to_mapFragment, bundleOf(
                        LATITUDE_KEY to point.latitude,
                        LONGITUDE_KEY to point.longitude
                    )
                )
            }

            override fun onRemove(point: ru.netology.yamapspoints.dto.Point) {
                viewModel.removePointById(point.id)
            }

            override fun onEdit(point: ru.netology.yamapspoints.dto.Point) {
                findNavController().navigate(R.id.action_pointsFragment_to_addPointFragment, bundleOf(
                    AddPointFragment.LATITUDE_KEY to point.latitude,
                    AddPointFragment.LONGITUDE_KEY to point.longitude,
                    AddPointFragment.ID_KEY to point.id,
                    AddPointFragment.TITLE_KEY to point.title,
                    AddPointFragment.DESCRIPTION_KEY to point.description,
                )
                )
            }
        })

        binding.list.adapter = adapter

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.points.collectLatest { points ->
                    adapter.submitList(points)
                    binding.empty.isVisible = points.isEmpty()
                }
            }
        }

        return binding.root
    }
}
