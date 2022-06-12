package sample.ishaq.amazon.ui.main.adapters

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import sample.ishaq.amazon.R
import sample.ishaq.amazon.databinding.MainListItemLayoutBinding
import sample.ishaq.amazon.model.Product

class ProductViewHolder(
    private val viewBinding: MainListItemLayoutBinding,
    val onProductClicked:(Product)->Unit
    ): RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(product: Product){
            with(viewBinding){
                itemNameText.text= product.name
                itemPriceText.text= product.price
                itemDateText.text= product.getFormattedDate()

                Glide.with(viewBinding.root)
                    .load(product.getThumbnailUrl())
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
                    .into(itemThumbImage)

                root.setOnClickListener {
                    onProductClicked(product)
                }
            }
        }
}