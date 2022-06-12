package sample.ishaq.amazon.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import sample.ishaq.amazon.R
import sample.ishaq.amazon.databinding.FragmentItemsListBinding
import sample.ishaq.amazon.model.Product
import sample.ishaq.amazon.ui.main.adapters.ProductsAdapter


class ItemsListFragment : Fragment() {
    private var listViewBinding: FragmentItemsListBinding?= null
    private val viewBinding: FragmentItemsListBinding
    get() = listViewBinding!!

    private val viewModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        listViewBinding= FragmentItemsListBinding.inflate(
            inflater, container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startDataCollectors()
    }

    override fun onDestroyView() {
        viewBinding.productsRecycler.layoutManager?.onSaveInstanceState()
            ?.let { viewModel.saveScrollState(it) }

        listViewBinding= null
        super.onDestroyView()
    }

    private fun startDataCollectors(){
        lifecycleScope.launchWhenStarted {
            viewModel.uiStateFlow.collectLatest { state->
                when(state){
                    is MainUiState.Loading->{
                        viewBinding.progressBar.isVisible= state.isLoading
                    }
                    is MainUiState.Error->{
                        viewBinding.progressBar.isVisible= false
                        Snackbar.make(viewBinding.root, state.error, Snackbar.LENGTH_LONG).show()
                        viewModel.consumeUiState()
                    }

                    is MainUiState.ProductsLoaded->{
                        viewBinding.progressBar.isVisible= false
                        setupRecyclerView(state.products)
                    }
                }
            }
        }
    }

    private fun setupRecyclerView(products: List<Product>){
        val productsAdapter= ProductsAdapter(
            products= products,
            onProductClicked = {
                viewModel.selectProduct(it)
                findNavController().navigate(R.id.action_itemsListFragment_to_itemDetailsFragment)
            }
        )

        val productsLayoutManager= LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        with(viewBinding.productsRecycler){
            adapter= productsAdapter
            layoutManager= productsLayoutManager

            viewModel.scrollState?.let {
                layoutManager?.onRestoreInstanceState(it)
            }
            if(itemDecorationCount==0){
                addItemDecoration(DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL))
            }
        }
    }
}