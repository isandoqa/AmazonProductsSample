package sample.ishaq.amazon.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<T:BaseViewModel, V: ViewBinding>: AppCompatActivity() {
    lateinit var viewModel: T
    lateinit var viewBinding: V

    abstract fun getInjectedViewModel():T
    abstract fun getInjectedViewBinding():V
    abstract fun setupViews()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding= getInjectedViewBinding()
        setContentView(viewBinding.root)
        viewModel= getInjectedViewModel()
        setupViews()
    }
}