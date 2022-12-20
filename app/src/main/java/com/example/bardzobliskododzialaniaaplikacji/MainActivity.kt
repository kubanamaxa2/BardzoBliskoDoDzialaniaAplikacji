package com.example.bardzobliskododzialaniaaplikacji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.Collections.swap
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var lostext = findViewById<TextView>(R.id.lostext)
        var liczbalosowan = 0
        val liczbalosedit = findViewById<EditText>(R.id.editTextNumberDecimal).text
        val losuj = findViewById<Button>(R.id.button)
        val popv = findViewById<TextView>(R.id.popv)
        val popms = findViewById<TextView>(R.id.popms)
        val speedv = findViewById<TextView>(R.id.speedv)
        val speedms = findViewById<TextView>(R.id.speedms)
        val heapv = findViewById<TextView>(R.id.heapv)
        val heapms = findViewById<TextView>(R.id.heapms)
        val scalv = findViewById<TextView>(R.id.scalv)
        val scalms = findViewById<TextView>(R.id.scalms)
        fun sortbuble(arr: MutableList<Int>): MutableList<Int>{ //sortowanie bąbelkowe
            var swappedElements : Boolean
            var level = arr.size - 1
            do {
                swappedElements = false
                for (i in 0..level - 1){
                    if (arr[i] > arr[i + 1]){
                        swap(arr, i, i + 1)
                        swappedElements = true
                    }
                }
                level--
            } while (swappedElements)
            return arr
        }
        fun quicksort(items:List<Int>):List<Int>{ //sortowanie szybkie
            if (items.count() < 2){
                return items
            }
            val pivot = items[items.count()/2]
            val equal = items.filter { it == pivot }
            val less = items.filter { it < pivot }
            val greater = items.filter { it > pivot }
            return quicksort(less) + equal + quicksort(greater)
        }
        losuj.setOnClickListener {
            var lista = arrayOf<Int>()
            liczbalosowan = liczbalosedit.toString().toInt() -1
            lostext.text = "| "
            for(i in 0..liczbalosowan){
                lista = lista.plus(0)

            }
            for(i in 0..liczbalosowan){
                lista[i] = Random.nextInt(0,9)
                lostext.text =lostext.text.toString() + lista[i].toString() + " | "
            }
            popv.text = sortbuble(lista.toMutableList()).toString() //sortowanie bąbelkowe
            speedv.text = quicksort(lista.toList()).toString()
            }
        }
    }