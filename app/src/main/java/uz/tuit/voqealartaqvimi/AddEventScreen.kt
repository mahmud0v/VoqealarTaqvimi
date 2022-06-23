package uz.tuit.voqealartaqvimi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import uz.tuit.voqealartaqvimi.databinding.AddEventBinding

class AddEventScreen : Fragment() {
    private var _binding: AddEventBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = AddEventBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.addBtn.setOnClickListener {
            val eventName = binding.eventEditText.text.toString()
            val date = binding.eventDateEdit.text.toString()
            val time = binding.eventTimeId.text.toString()
            val event = EventEntity(eventName=eventName, date = date, eventIntervalTime = time)
            AppDatabase.init(requireContext())
            val dao = AppDatabase.db?.eventDao()
            dao?.addEvent(event)
            findNavController().navigate(R.id.action_addEventScreen_to_homeScreen)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}