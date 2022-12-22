package com.example.bardzobliskododzialaniaaplikacji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import java.util.Collections.swap
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var lostext = findViewById<TextView>(R.id.lostext)
        val liczbaeledit = findViewById<EditText>(R.id.editTextNumberDecimal).text
        val ilerazylos = findViewById<EditText>(R.id.editTextNumberDecimal2).text
        val losuj = findViewById<Button>(R.id.button)
        val popms = findViewById<TextView>(R.id.popms)
        val speedms = findViewById<TextView>(R.id.speedms)
        val heapms = findViewById<TextView>(R.id.heapms)
        val scalms = findViewById<TextView>(R.id.scalms)
        val wstawms = findViewById<TextView>(R.id.wstawms)
        var czas1: Long; var czas2: Long
        //---------------------- sortowanie bąbelkowe ------------------------
        fun sortbuble(arr: MutableList<Int>): MutableList<Int>{
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

        // ----------------------- sortowanie szybkie -----------------------
        fun quicksort(items:List<Int>):List<Int>{
            if (items.count() < 2){
                return items
            }
            val pivot = items[items.count()/2]
            val equal = items.filter { it == pivot }
            val less = items.filter { it < pivot }
            val greater = items.filter { it > pivot }
            return quicksort(less) + equal + quicksort(greater)
        }

        //----------------------sortowanie przez wstawianie-------------------
        fun wstawsort(arr : MutableList<Int>)
        {
            for (i in 1 until arr.size) {
                val current = arr[i]
                var j = i - 1

                while (j >= 0 && arr[j] > current) {
                    arr[j + 1] = arr[j]
                    j--
                }

                arr[j + 1] = current
            }
        }

        //-------------------Funkcja sortowania przez scalanie--------------------
        fun merge(left: IntArray, right: IntArray): IntArray {
            var leftIndex = 0
            var rightIndex = 0
            val result = IntArray(left.size + right.size)

            for (i in result.indices) {
                if (leftIndex >= left.size) {
                    result[i] = right[rightIndex++]
                } else if (rightIndex >= right.size) {
                    result[i] = left[leftIndex++]
                } else if (left[leftIndex] < right[rightIndex]) {
                    result[i] = left[leftIndex++]
                } else {
                    result[i] = right[rightIndex++]
                }
            }

            return result
        }
        fun scalsort(array: IntArray): IntArray {
            if (array.size <= 1) return array

            val mid = array.size / 2
            val left = scalsort(array.copyOfRange(0, mid))
            val right = scalsort(array.copyOfRange(mid, array.size))

            return merge(left, right)
        }

        //----------------Sortowanie przez kopcowanie (heapsort)-------------
        fun partition(array: IntArray, low: Int, high: Int): Int {
            val pivot = array[high]
            var i = low - 1
            for (j in low until high) {
                if (array[j] <= pivot) {
                    i++
                    val temp = array[i]
                    array[i] = array[j]
                    array[j] = temp
                }
            }
            val temp = array[i + 1]
            array[i + 1] = array[high]
            array[high] = temp
            return i + 1
        }

        fun heapify(array: MutableList<Int>, n: Int, i: Int) {
            var largest = i
            val l = 2 * i + 1
            val r = 2 * i + 2

            if (l < n && array[l] > array[largest]) {
                largest = l
            }

            if (r < n && array[r] > array[largest]) {
                largest = r
            }

            if (largest != i) {
                val temp = array[i]
                array[i] = array[largest]
                array[largest] = temp

                heapify(array, n, largest)
            }
        }
        fun heapsort(array: MutableList<Int>) {
            // Budowanie kopca
            for (i in array.size / 2 - 1 downTo 0) {
                heapify(array, array.size, i)
            }

            for (i in array.size - 1 downTo 0) {
                val temp = array[0]
                array[0] = array[i]
                array[i] = temp

                heapify(array, i, 0)
            }
        }

        //Funkcja losująca losową liste
        fun losowanie(size: Int): MutableList<Int> {
            val random = Random()
            return List(size) { random.nextInt(1000) }.toMutableList()
        }

        //Funkcja licząca roznice miedzy pomiarami
        fun calcTime(t1 : Long, t2 : Long) : Long
        {
            return t2 - t1
        }


        // -------------------- Działanie -----------------------------
        losuj.setOnClickListener {
            if(liczbaeledit.isEmpty() && ilerazylos.isEmpty()){
                var lista = losowanie(liczbaeledit.toString().toInt() )





                czas1 = System.currentTimeMillis()
                    for(i in 0..ilerazylos.toString().toInt())
                        sortbuble(lista) //sortowanie bąbelkowe


                quicksort(lista.toList()) // sortowanie szybkie
            }
            else
            {
                Toast.makeText(this, "Wypełnij każde pole", Toast.LENGTH_SHORT).show()
            }
        }
    }
}