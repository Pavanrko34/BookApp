package adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pavanapp.bookapp.R
import com.squareup.picasso.Picasso
import database.BookEntity
import kotlinx.android.synthetic.main.fragment_favourites.view.*


class FavouriteRecyclerAdapter(val context:Context, val bookList:List<BookEntity>): RecyclerView.Adapter<FavouriteRecyclerAdapter.FavouriteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteRecyclerAdapter.FavouriteViewHolder {
        val view= LayoutInflater.from(parent.context).inflate(R.layout.recycler_favourites_single_row, parent, false)
        return FavouriteViewHolder(view)

    }

    override fun getItemCount(): Int {
      return bookList.size
    }

    override fun onBindViewHolder(
        holder: FavouriteRecyclerAdapter.FavouriteViewHolder,
        position: Int
    ) {
        val book=bookList[position]
        holder.txtBookName.text=book.bookName
        holder. txtBookAuthor.text=book.bookAuthor
        holder.txtBookPrice.text=book.bookPrice
        holder.txtBookRating.text=book.bookRating
        Picasso.get().load(book.bookImage).error(R.drawable.default_book_cover).into(holder.imgBookImage)

    }

    class FavouriteViewHolder(view: View):RecyclerView.ViewHolder(view){
       val txtBookName: TextView =view.findViewById(R.id.txtFavBookTitle)
     val txtBookAuthor:TextView=view.findViewById(R.id.txtFavBookAuthor)
      val txtBookPrice:TextView=view.findViewById(R.id.txtBookPrice)
      val txtBookRating:TextView=view.findViewById(R.id.txtBookRating)
        val imgBookImage: ImageView = view.findViewById(R.id.llFavContent)
        val llContext: LinearLayout=view.findViewById(R.id.llFavContent)

    }



}