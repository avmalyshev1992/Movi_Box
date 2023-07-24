package com.example.movi_box.view.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movi_box.databinding.FragmentWatchLaterBinding
import com.example.movi_box.utils.AnimationHelper


class WatchLaterFragment : Fragment() {

    private var binding: FragmentWatchLaterBinding? = null
    private val _binding: FragmentWatchLaterBinding get() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchLaterBinding.inflate(inflater, container, false)
        return _binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(binding!!.watchLaterFragmentRoot, requireActivity(), 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}