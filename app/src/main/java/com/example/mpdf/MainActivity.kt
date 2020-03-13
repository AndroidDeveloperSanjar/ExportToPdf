package com.example.mpdf

import android.Manifest
import android.content.Context
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.itextpdf.text.*
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.OutputStream
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {
    private val permissions = 111
    private var pdfFile: File? = null
    var context: Context? = null
    var positionNumber: DataList? = null
    var regionName: DataList? = null
    private var apiNumber: DataList? = null
    private var speedOne: DataList? = null
    private var speedTwo: DataList? = null
    private var speedThree: DataList? = null
    private var speedFour: DataList? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this


        Log.i("MainAc","${dataList()}")

        downloadpdf.setOnClickListener{
            try {
                createPdfWrapper()
                Toast.makeText(this,"Pdf successfully!",Toast.LENGTH_SHORT).show()
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: DocumentException) {
                e.printStackTrace()
            }
        }
    }

    @Throws(FileNotFoundException::class, DocumentException::class)
    private fun createPdfWrapper() {
       val hasWriteStoragePermission =
               ActivityCompat.checkSelfPermission(context!!, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if (hasWriteStoragePermission != PackageManager.PERMISSION_GRANTED) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!shouldShowRequestPermissionRationale(Manifest.permission.WRITE_CONTACTS)) {
                    showMessageOKCancel("You need to allow access to Storage",
                            DialogInterface.OnClickListener { _, _ ->
                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                    requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                                            permissions)
                                }
                            })
                    return
                }
                requestPermissions(arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                        permissions)
            }
            return
        } else {
            createPdf()
        }
    }

    private fun showMessageOKCancel(message: String, okListener: DialogInterface.OnClickListener) {
        AlertDialog.Builder(context!!)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show()
    }

    @Suppress("DEPRECATION")
    @Throws(FileNotFoundException::class, DocumentException::class)
    private fun createPdf() {
        val docsFolder = File(Environment.getExternalStorageDirectory().toString())
        if (!docsFolder.exists()) {
            docsFolder.mkdir()
            Log.i(TAG, "Created a new directory for PDF")
        }
        val pdfname = "GiftItem.pdf"
        pdfFile = File(docsFolder.absolutePath, pdfname)
        val output: OutputStream = FileOutputStream(pdfFile!!)
        val document = Document(PageSize.A4)
        val table = PdfPTable(floatArrayOf(1f, 4f, 3f, 2f, 2f, 2f, 2f))
        table.defaultCell.horizontalAlignment = Element.ALIGN_CENTER
        table.defaultCell.fixedHeight = 30f
        table.totalWidth = PageSize.A4.width
        table.widthPercentage = 100f
        table.defaultCell.verticalAlignment = Element.ALIGN_MIDDLE
        table.addCell("Position Number")
        table.addCell("Region Name")
        table.addCell("Api Number")
        table.addCell("Speed One Number")
        table.addCell("Speed Two Number")
        table.addCell("Speed Three Number")
        table.addCell("Speed Four Number")
        table.headerRows = 1
        val cells = table.getRow(0).cells!!
        for (j in cells.indices) {
            cells[j].backgroundColor = BaseColor.GRAY
        }
        for (i in dataList().indices) {
            positionNumber = dataList()[i]
            regionName = dataList()[i]
            apiNumber = dataList()[i]
            speedThree = dataList()[i]
            speedFour = dataList()[i]
            speedTwo = dataList()[i]
            speedOne = dataList()[i]
            val positionNumber = positionNumber!!.positionNumber
            val regionName = regionName!!.regionName
            val apiNumber = apiNumber!!.apiNumber
            val speedOne = speedOne!!.speedOneNumber
            val speedTwo = speedFour!!.speedTwoNumber
            val speedThree = speedThree!!.speedOneNumber
            val speedFour = speedFour!!.speedFourNumber
            table.addCell(positionNumber)
            table.addCell(regionName)
            table.addCell(apiNumber)
            table.addCell(speedOne)
            table.addCell(speedThree)
            table.addCell(speedFour)
            table.addCell(speedTwo)
        }

        PdfWriter.getInstance(
                document,
                output
        )
        document.open()
        val f = Font(
                Font.FontFamily.TIMES_ROMAN,
                30.0f,
                Font.UNDERLINE,
                BaseColor.BLUE
        )
        document.add(Paragraph("Pdf Data\n", f))
        document.add(table)
        document.close()
    }



    companion object {
        private const val TAG = "PdfCreatorActivity"
    }

    private fun dataList(): ArrayList<DataList>{
        val list = ArrayList<DataList>()
        list.add(DataList("1","Toshkent v.","123.1.11.2","21","21","32","1"))
        list.add(DataList("2","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("3","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("4","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("5","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("6","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))
        list.add(DataList("7","Toshkent v.","123.1.11.2","21","21","21","21"))


        return list
    }
}