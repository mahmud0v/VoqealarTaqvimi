package uz.tuit.voqealartaqvimi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import uz.tuit.voqealartaqvimi.databinding.HomeScreenBinding
import java.text.DecimalFormat

class HomeScreen : Fragment() {
    private var _binding: HomeScreenBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = HomeScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.calendarView.setOnDateChangeListener(
            CalendarView.OnDateChangeListener { calendarView, i, i2, i3 ->

            }
        )
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}