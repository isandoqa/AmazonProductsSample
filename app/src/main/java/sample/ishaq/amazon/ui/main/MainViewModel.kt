package sample.ishaq.amazon.ui.main

import android.content.Context
import android.os.Parcelable
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import sample.ishaq.amazon.R
import sample.ishaq.amazon.base.BaseViewModel
import sample.ishaq.amazon.dataSource.main.MainRepo
import sample.ishaq.amazon.model.Product
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val mainRepo: MainRepo
) : BaseViewModel() {

    private val _uiStateFlow: MutableStateFlow<MainUiState> = MutableStateFlow(MainUiState.None)
    val uiStateFlow: StateFlow<MainUiState>
    get() = _uiStateFlow

    private var _selectedProduct: Product?= null
    val selectedProduct: Product?
    get()= _selectedProduct

    private var _scrollState: Parcelable?= null
    val scrollState: Parcelable?
    get() = _scrollState

    init {
        loadProducts()
    }

    private fun loadProducts(){
        viewModelScope.launch {
            _uiStateFlow.value= MainUiState.Loading(true)

            val response= mainRepo.loadMainProductsList()
            _uiStateFlow.value= MainUiState.Loading(false)

            _uiStateFlow.value= if(response.errorMessage==null && !response.data?.products.isNullOrEmpty()){
                MainUiState.ProductsLoaded(
                    products = response.data?.products?: arrayListOf()
                )
            }else{
                MainUiState.Error(
                    error = response.errorMessage?:
                    context.getString(R.string.empty_products_message)
                )
            }
        }
    }

    fun consumeUiState(){
        _uiStateFlow.value= MainUiState.None
    }

    fun selectProduct(product: Product){
        _selectedProduct= product
    }

    fun saveScrollState(state: Parcelable){
        _scrollState= state
    }
}

sealed class MainUiState(){
    object None: MainUiState()
    data class Loading(val isLoading: Boolean): MainUiState()
    data class ProductsLoaded(val products: List<Product>): MainUiState()
    data class Error(val error: String): MainUiState()
}