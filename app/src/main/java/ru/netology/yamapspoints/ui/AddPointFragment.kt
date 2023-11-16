package ru.netology.yamapspoints.ui

import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import ru.netology.yamapspoints.databinding.FragmentAddPointBinding
import ru.netology.yamapspoints.dto.Point
import ru.netology.yamapspoints.viewmodel.ViewModel
import ru.netology.yamapspoints.types.PointType

class AddPointFragment : Fragment() {

    companion object {
        const val ID_KEY = "ID_KEY"
        const val TITLE_KEY = "TITLE_KEY"
        const val DESCRIPTION_KEY = "DESCRIPTION_KEY"
        const val LATITUDE_KEY = "LATITUDE_KEY"
        const val LONGITUDE_KEY = "LONGITUDE_KEY"
    }

    private val viewModel by viewModels<ViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentAddPointBinding.inflate(inflater, container, false)

        binding.apply {

            titleField.text = Editable.Factory.getInstance().newEditable(
                requireArguments().getString(TITLE_KEY) ?: ""
            )
            descriptionField.text = Editable.Factory.getInstance().newEditable(
                requireArguments().getString(DESCRIPTION_KEY) ?: ""
            )


            save.setOnClickListener {


                val title = titleField.text.toString().trim()
                val description = descriptionField.text.toString().trim()

                if (title.isBlank()) {
                    emptyTitleMsg.isVisible = true
                } else {
                    viewModel.insertPoint(
                        Point(
                            id = requireArguments().getLong(ID_KEY),
                            title = title,
                            description = description,
                            latitude = requireArguments().getDouble(LATITUDE_KEY),
                            longitude = requireArguments().getDouble(LONGITUDE_KEY),
                            pointType = PointType.PINPOINT
                        )
                    )
                }

                findNavController().popBackStack()
            }


        }

        return binding.root
    }
}
