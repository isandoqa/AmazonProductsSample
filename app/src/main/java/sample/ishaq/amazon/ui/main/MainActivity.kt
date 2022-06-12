package sample.ishaq.amazon.ui.main

import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import sample.ishaq.amazon.base.BaseActivity
import sample.ishaq.amazon.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun getInjectedViewModel()=mainViewModel

    override fun getInjectedViewBinding()= ActivityMainBinding.inflate(layoutInflater)

    override fun setupViews() {
    }
}