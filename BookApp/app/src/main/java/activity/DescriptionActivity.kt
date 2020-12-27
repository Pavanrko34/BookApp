package activity

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.pavanapp.bookapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_description.*
import org.json.JSONObject
import org.w3c.dom.Text
import com.pavanapp.bookapp.R.id.*
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.AsyncTask
import android.provider.Settings
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.room.Room
import database.BookDatabase
import database.BookEntity
import util.ConnectionManager
import org.json.JSONException


class DescriptionActivity : AppCompatActivity() {
    lateinit var txtBookName: TextView
    lateinit var txtBookAuthor: TextView
    lateinit var txtBookPrice: TextView
    lateinit var txtBookRating: TextView
    lateinit var imgBookImage: ImageView
    lateinit var txtBookDesc: TextView
    lateinit var btnAddToFav: Button
    lateinit var progressBar: ProgressBar
    lateinit var progressLayout: RelativeLayout
    lateinit var toolbar: Toolbar

    var bookId: String? = "100"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        txtBookName = findViewById(R.id.txtBookName)
        txtBookAuthor = findViewById(R.id.txtBookAuthor)
        txtBookPrice = findViewById(R.id.txtBookPrice)
        txtBookRating = findViewById(R.id.txtBookRating)
        imgBookImage = findViewById(R.id.imgBookImage)
        txtBookDesc = findViewById(R.id.txtBookDesc)
        btnAddToFav = findViewById(R.id.btnAddToFav)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE
        progressLayout = findViewById(R.id.progressLayout)
        progressLayout.visibility = View.VISIBLE

        if (intent != null) {
            bookId = intent.getStringExtra("book_id")
        } else {
            finish()
            Toast.makeText(
                this@DescriptionActivity, "Some unexpected error occurred!", Toast.LENGTH_SHORT
            ).show()
        }
        if (bookId == "100") {
            finish()
            Toast.makeText(
                this@DescriptionActivity, "Some unexpected error occurred!", Toast.LENGTH_SHORT
            ).show()
        }
        val queue = Volley.newRequestQueue(this@DescriptionActivity)
        val url = "http://13.235.250.119/v1/book/get_book/"

        val jsonParams = JSONObject()
        jsonParams.put("book_id", bookId)
        val jsonRequest=object: JsonObjectRequest(Request.Method.POST, url, jsonParams, Response.Listener{

         try{  val success=it.getBoolean("success")
             if(success){
                 val bookJsonObject=it.getJSONObject("book_data")
                 progressLayout.visibility=View.GONE


                 Picasso.get().load(bookJsonObject.getString("image"))
                 txtBookName.text = bookJsonObject.getString("name")
                 txtBookAuthor.text = bookJsonObject.getString("author")
                 txtBookPrice.text = bookJsonObject.getString("price")
                 txtBookRating.text = bookJsonObject.getString("rating")
                 txtBookDesc.text = bookJsonObject.getString("description")

             }else{
           Toast.makeText(this@DescriptionActivity,"Some Error Occured", Toast.LENGTH_SHORT).show()
             }
         }catch (e: Exception){
             Toast.makeText(this@DescriptionActivity,"Some Error Occured", Toast.LENGTH_SHORT).show()
         }

        },Response.ErrorListener{
            Toast.makeText(this@DescriptionActivity,"Volley Error $it", Toast.LENGTH_SHORT).show()

        }){
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["Content-type"] = "application/json"
                headers["token"] = "177a9bc2241d2f"
                return headers
            }

        }


    }

}