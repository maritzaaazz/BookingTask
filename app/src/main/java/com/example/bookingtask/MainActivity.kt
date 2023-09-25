package com.example.bookingtask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import android.widget.DatePicker
import com.example.bookingtask.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val keberangkatan = resources.getStringArray(R.array.keberangkatan)
        val kelas = resources.getStringArray(R.array.kelas)

        with(binding){
            val keberangkatanAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, keberangkatan)
            //OPSIONAL
            keberangkatanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinKeberangkatan.adapter = keberangkatanAdapter

            val tujuanAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, keberangkatan)
            //OPSIONAL
            tujuanAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinTujuan.adapter = tujuanAdapter


            val kelasAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, kelas)
            //OPSIONAL
            kelasAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinKelas.adapter = kelasAdapter

            var selectedDate = ""
            datePick.init(datePick.year, datePick.month, datePick.dayOfMonth){
                // view, year, month, day -> (aslinya bentuknya ky gitu, klo ga dipke diilangin aja diganti _)
                    _, year, month, day ->
                selectedDate = "$day/${month+1}/$year"
//                Toast.makeText(this@MainActivity, selectedDate, Toast.LENGTH_SHORT).show()
            }


            var selectedTime = ""
            timePick.setOnTimeChangedListener{
                    _, timeOfDay, minute ->
                selectedTime = String.format("%02d:%02d", timeOfDay, minute)
//                Toast.makeText(this@MainActivity, selectedTime, Toast.LENGTH_SHORT).show()
            }

            // Tambahkan event handler untuk tombol
            btnCheckout.setOnClickListener {
                // Ambil nilai dari EditText untuk nama pengguna (ganti dengan ID yang sesuai)
                val namaPengguna = inputNama.text.toString()

                // Ambil nilai dari Spinner
                val keberangkatan = spinKeberangkatan.selectedItem.toString()
                val tujuan = spinTujuan.selectedItem.toString()
                val kelas = spinKelas.selectedItem.toString()

                // Ambil tanggal dari DatePicker
                val tanggalKeberangkatan = selectedDate

                // Ambil jam dari TimePicker
                val jamKeberangkatan = selectedTime

                // Buat pesan notifikasi
                val notifikasi = "Halo $namaPengguna, pesanan tiket Anda dari $keberangkatan ke $tujuan pada $tanggalKeberangkatan pukul $jamKeberangkatan dengan $kelas berhasil dibuat!"

                // Tampilkan notifikasi menggunakan Toast
                Toast.makeText(this@MainActivity, notifikasi, Toast.LENGTH_LONG).show()
            }
        }
    }
}