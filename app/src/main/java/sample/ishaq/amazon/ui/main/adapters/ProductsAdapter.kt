package sample.ishaq.amazon.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sample.ishaq.amazon.databinding.MainListItemLayoutBinding
import sample.ishaq.amazon.model.Product

class ProductsAdapter(
    private val products: List<Product>,
    private val onProductClicked:(Product)->Unit
):RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ProductViewHolder(
        viewBinding = MainListItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
        false),
        onProductClicked= onProductClicked
    )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(
            product = products[position]
        )
    }

    override fun getItemCount()= products.size
}
