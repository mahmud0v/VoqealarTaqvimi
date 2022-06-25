package uz.tuit.voqealartaqvimi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import uz.tuit.voqealartaqvimi.databinding.HomeScreenBinding
import java.text.DecimalFormat
import java.text.SimpleDateFormat

class HomeScreen : Fragment() {
    private var _binding: HomeScreenBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: EventAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = HomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        AppDatabase.init(requireContext())
        val list = binding.calendarView
        val customFormat = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = customFormat.format(binding.calendarView.date).substring(0, 2)
        val numberCurrentDate = currentDate.toInt()
        val eventList = AppDatabase.db!!.eventDao()!!.getDateEvents(currentDate.toString())
        if (eventList.isNotEmpty()) {
            adapter = EventAdapter()
            adapter.differ.submitList(eventList)
            binding.rvView.adapter = adapter
            binding.rvView.layoutManager = LinearLayoutManager(requireContext())
        }
        
        if (numberCurrentDate==12|| numberCurrentDate in 1..2){
            binding.faslId.text = "Qish"

        }
        if (numberCurrentDate in 3..5){
            binding.faslId.text = "Bahor"
        }
        if (numberCurrentDate in 6..8){
            binding.faslId.text = "Yoz"
        }
        if (numberCurrentDate in 9..11){
            binding.faslId.text = "Kuz"
        }


        binding.calendarView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { calendarView, i, i2, i3 ->

                val anoEventList =
                    AppDatabase.db!!.eventDao()!!.getDateEvents(i3.toString())
                val adapter = EventAdapter()
                adapter.differ.submitList(anoEventList)
                binding.rvView.adapter = adapter
                binding.rvView.layoutManager = LinearLayoutManager(requireContext())
                if ((i2+1)==12||(i2+1) in 1..2){
                    binding.faslId.text = "Qish"

                }
                if ((i2+1) in 3..5){
                    binding.faslId.text = "Bahor"
                }
                if ((i2+1)in 6..8){
                    binding.faslId.text = "Yoz"
                }
                if ((i2+1) in 9..11){
                    binding.faslId.text = "Kuz"
                }
            }
        )


        binding.addBtn.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreen_to_addEventScreen)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}