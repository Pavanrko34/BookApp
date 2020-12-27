package fragment

import adapter.FavouriteRecyclerAdapter
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

import androidx.room.Room
import com.pavanapp.bookapp.R
import database.BookDatabase
import database.BookEntity


class FavouritesFragment : Fragment() {
    lateinit var recyclerFavourite: RecyclerView
    lateinit var progressLayout: RelativeLayout
    lateinit var progressBar: ProgressBar
    lateinit var layoutManager: RecyclerView
    lateinit var recyclerAdapter: FavouriteRecyclerAdapter
    var dbBookList=listOf<BookEntity>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourites, container, false)

        recyclerFavourite= view!!.findViewById(R.id.recyclerFavourite)
        progressLayout= view!!.findViewById(R.id.progressBar)
        progressBar= view!!.findViewById(R.id.progressBar)
      // layoutManager= GridLayoutManager(activity as Context, 2)
        dbBookList= RetrieveFavourites(activity as Context).execute().get()

        if(activity!=null){
            progressLayout.visibility=View.GONE
            recyclerAdapter= FavouriteRecyclerAdapter(activity as Context, dbBookList)
           // recyclerFavourite.layoutManager= layoutManager
        }

        return view


    }
    class RetrieveFavourites(val context:Context):AsyncTask<Void, Void, List<BookEntity>>(){
        override fun doInBackground(vararg p0: Void?): List<BookEntity> {
           val db= Room.databaseBuilder(context, BookDatabase::class.java, "books-db").build()
            return db.bookDao().getAllBooks()
        }

    }


}