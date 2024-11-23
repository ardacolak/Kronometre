package com.ardacolak.kronometre

import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ardacolak.kronometre.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var kronoMetreZamani:Long = 0
        binding.buttonStart.setOnClickListener {
            binding.kronometre.base=SystemClock.elapsedRealtime()+kronoMetreZamani
            Toast.makeText(this@MainActivity,"Süreniz Basladı",Toast.LENGTH_LONG).show()
            binding.kronometre.start()
            binding.buttonStart.visibility=View.GONE
            binding.buttonPause.visibility=View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.pause))
        }
        binding.buttonPause.setOnClickListener {
            kronoMetreZamani=binding.kronometre.base-SystemClock.elapsedRealtime()
            binding.kronometre.stop()
            Toast.makeText(this@MainActivity,"Süre durduruldu",Toast.LENGTH_LONG).show()
            binding.buttonPause.visibility=View.GONE
            binding.buttonStart.visibility=View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        }
        binding.buttonReset.setOnClickListener {
            binding.kronometre.base=SystemClock.elapsedRealtime()
            binding.kronometre.stop()
            Toast.makeText(this@MainActivity,"Kronometre sıfırlandı :D",Toast.LENGTH_LONG).show()
            kronoMetreZamani=0
            binding.buttonPause.visibility=View.GONE
            binding.buttonStart.visibility=View.VISIBLE
            binding.imageView.setImageDrawable(getDrawable(R.drawable.start))
        }

    }
}