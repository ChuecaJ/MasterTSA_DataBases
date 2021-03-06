package es.usj.mastertsa.jchueca.cities.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import es.usj.mastertsa.jchueca.cities.R
import es.usj.mastertsa.jchueca.cities.presentation.view.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction().
                add(R.id.fragmentContainerView, HomeFragment.newInstance()).commit()
        }
    }
}