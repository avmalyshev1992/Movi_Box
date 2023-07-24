package com.example.movi_box.view.fragments
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.movi_box.databinding.FragmentSelectionsBinding
import com.example.movi_box.utils.AnimationHelper


class SelectionsFragment : Fragment() {

    private var bindingSelect: FragmentSelectionsBinding? = null
    private val binding: FragmentSelectionsBinding get() = bindingSelect!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bindingSelect = FragmentSelectionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        AnimationHelper.performFragmentCircularRevealAnimation(bindingSelect!!.selectionsFragmentRoot, requireActivity(), 1)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        bindingSelect = null
    }
}