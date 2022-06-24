package uz.tuit.voqealartaqvimi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        val eventList = AppDatabase.db!!.eventDao()!!.getDateEvents(currentDate.toString())
        if (eventList.isNotEmpty()) {
            adapter = EventAdapter()
            adapter.differ.submitList(eventList)
            binding.rvView.adapter = adapter
            binding.rvView.layoutManager = LinearLayoutManager(requireContext())
        }

        binding.calendarView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { calendarView, i, i2, i3 ->
                val anoEventList =
                    AppDatabase.db!!.eventDao()!!.getDateEvents(i3.toString())
                val adapter = EventAdapter()
                adapter.differ.submitList(anoEventList)
                binding.rvView.adapter = adapter
                binding.rvView.layoutManager = LinearLayoutManager(requireContext())


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