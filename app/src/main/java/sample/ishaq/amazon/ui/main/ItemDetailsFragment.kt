package sample.ishaq.amazon.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import sample.ishaq.amazon.R
import sample.ishaq.amazon.databinding.FragmentItemDetailsBinding
import sample.ishaq.amazon.model.Product


class ItemDetailsFragment : Fragment() {
    private var detailsViewBinding: FragmentItemDetailsBinding?= null
    private val viewBinding: FragmentItemDetailsBinding
        get() = detailsViewBinding!!

    private val viewModel: MainViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewBinding= FragmentItemDetailsBinding.inflate(
            inflater, container, false
        )
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectedProduct?.let { setupProductViews(it) }
    }

    override fun onDestroyView() {
        detailsViewBinding= null
        super.onDestroyView()
    }

    private fun setupProductViews(product: Product) {
        with(viewBinding){
            productTitle.text= product.name
            productPrice.text= product.price
            productDate.text= product.getFormattedDate()
            productId.text= product.uID

            Glide.with(viewBinding.root)
                .load(product.getImageUrl())
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image)
                .into(productImage)
        }
    }
}