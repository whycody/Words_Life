package com.whycody.wordslife.current.language

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.whycody.wordslife.MainActivity
import com.whycody.wordslife.R
import com.whycody.wordslife.choose.language.ChooseLanguageFragment
import com.whycody.wordslife.data.SharedPreferenceStringLiveData
import com.whycody.wordslife.data.language.LanguageDao
import com.whycody.wordslife.data.language.LanguageDaoImpl
import com.whycody.wordslife.databinding.FragmentCurrentLanguageBinding
import kotlinx.android.synthetic.main.fragment_current_language.view.*
import org.koin.android.ext.android.inject

class CurrentLanguageFragment : Fragment() {

    private val languageDao: LanguageDao by inject()
    private lateinit var sharedPrefs: SharedPreferences
    private lateinit var layoutView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val binding: FragmentCurrentLanguageBinding = DataBindingUtil.inflate(inflater,
            R.layout.fragment_current_language, container, false)
        sharedPrefs = context?.getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)!!
        observeCurrentLanguages(binding)
        layoutView = binding.root
        setupViews()
        return binding.root
    }

    private fun setupViews() {
        with(layoutView) {
            mainLanguageLayout.setOnClickListener { startChooseLanguageFragment(true) }
            translationLanguageLayout.setOnClickListener { startChooseLanguageFragment(false) }
            switchLangsArrows.setOnClickListener { languageDao.switchCurrentLanguages() }
        }
    }

    private fun startChooseLanguageFragment(mainLanguage: Boolean) =
        (activity as MainActivity).navigateTo(ChooseLanguageFragment().newInstance(mainLanguage))

    private fun observeCurrentLanguages(binding: FragmentCurrentLanguageBinding) {
        observeMainLanguage(binding)
        observeTranslationLanguage(binding)
    }

    private fun observeMainLanguage(binding: FragmentCurrentLanguageBinding) =
        SharedPreferenceStringLiveData(sharedPrefs,
                LanguageDaoImpl.MAIN_LANGUAGE, LanguageDaoImpl.DEFAULT_MAIN_LANGUAGE)
                .observe(activity as MainActivity, {
                    binding.mainLanguage = languageDao.getLanguage(it)
                })

    private fun observeTranslationLanguage(binding: FragmentCurrentLanguageBinding) =
        SharedPreferenceStringLiveData(sharedPrefs,
                LanguageDaoImpl.TRANSLATION_LANGUAGE, LanguageDaoImpl.DEFAULT_TRANSLATION_LANGUAGE)
                .observe(activity as MainActivity, {
                    binding.translationLanguage = languageDao.getLanguage(it)
                })

}