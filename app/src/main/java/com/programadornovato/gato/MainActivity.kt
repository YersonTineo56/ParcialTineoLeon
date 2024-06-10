package com.programadornovato.gato

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var imageView1:ImageView?=null
    var imageView2:ImageView?=null
    var imageView3:ImageView?=null
    var imageView4:ImageView?=null
    var imageView5:ImageView?=null
    var imageView6:ImageView?=null
    var imageView7:ImageView?=null
    var imageView8:ImageView?=null
    var imageView9:ImageView?=null
    var imgTurnoX:ImageView?=null
    var imgTurnoO:ImageView?=null
    var turno="x"
    val matrizGanadora= arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(4,5,6),
        intArrayOf(7,8,9),
        intArrayOf(1,4,7),
        intArrayOf(2,5,8),
        intArrayOf(3,6,9),
        intArrayOf(1,5,9),
        intArrayOf(3,5,7)
    )
    var posicionesX=IntArray(5)
    var posicionesO=IntArray(5)
    var contadorX=0
    var contadorO=0
    var ganador=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView1=findViewById(R.id.imageView1)
        imageView2=findViewById(R.id.imageView2)
        imageView3=findViewById(R.id.imageView3)
        imageView4=findViewById(R.id.imageView4)
        imageView5=findViewById(R.id.imageView5)
        imageView6=findViewById(R.id.imageView6)
        imageView7=findViewById(R.id.imageView7)
        imageView8=findViewById(R.id.imageView8)
        imageView9=findViewById(R.id.imageView9)
        imgTurnoX=findViewById(R.id.imgTurnoX)
        imgTurnoO=findViewById(R.id.imgTurnoO)
        imgTurnoX?.setBackgroundColor(Color.RED)
    }
    fun clickImagen(view:View){
        val rutaNombre=resources.getResourceName(view.id)
        val nombreClick=rutaNombre.substring(rutaNombre.lastIndexOf("/")+1)
        for(i in 1 .. 9){
            var nombreConcatenado="imageView$i"
            if(nombreClick==nombreConcatenado){
                if(turno=="x"){
                    posicionesX[contadorX]=i
                    contadorX++
                }else{
                    posicionesO[contadorO]=i
                    contadorO++
                }
            }
        }
        if(turno=="x"){
            view.setBackgroundResource(R.drawable.gato_x)
            imgTurnoX?.setBackgroundColor(Color.WHITE)
            imgTurnoO?.setBackgroundColor(Color.RED)
            turno="o"
        }else{
            view.setBackgroundResource(R.drawable.gato_o)
            imgTurnoX?.setBackgroundColor(Color.RED)
            imgTurnoO?.setBackgroundColor(Color.WHITE)
            turno="x"
        }
        var altoMatriz=matrizGanadora.size
        for(i in 0 until contadorX){
            var gano=true
            for (j in 0 until altoMatriz){
                gano=coincideGanador(matrizGanadora[j],posicionesX)
                if(gano==true){
                    ganador="x"
                    Toast.makeText(this,"Gano X",Toast.LENGTH_LONG).show()
                    dibujaLinea()
                    deshabilitar()
                    break
                }
            }
            if(gano==true){
                break
            }
        }
        for(i in 0 until contadorO){
            var gano=true
            for (j in 0 until altoMatriz){
                gano=coincideGanador(matrizGanadora[j],posicionesO)
                if(gano==true){
                    ganador="o"
                    Toast.makeText(this,"Gano O",Toast.LENGTH_LONG).show()
                    dibujaLinea()
                    deshabilitar()
                    break
                }
            }
            if(gano==true){
                break
            }
        }
        view.isEnabled=false
    }
    fun coincideGanador(registroGanador:IntArray,posiciones:IntArray):Boolean{
        for (i in 0 until 3){
            if(registroGanador[i]!=posiciones[i]){
                return false
            }
        }
        return true
    }
    fun dibujaLinea(){
        if(ganador=="x"){
            for (i in 0 until contadorX){
                if(posicionesX[i]==1){
                    imageView1?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesX[i]==2){
                    imageView2?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesX[i]==3){
                    imageView3?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesX[i]==4){
                    imageView4?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesX[i]==5){
                    imageView5?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesX[i]==6){
                    imageView6?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesX[i]==7){
                    imageView7?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesX[i]==8){
                    imageView8?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesX[i]==9){
                    imageView9?.setBackgroundColor(Color.BLUE)
                }
            }
        }else{
            for (i in 0 until contadorO){
                if(posicionesO[i]==1){
                    imageView1?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesO[i]==2){
                    imageView2?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesO[i]==3){
                    imageView3?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesO[i]==4){
                    imageView4?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesO[i]==5){
                    imageView5?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesO[i]==6){
                    imageView6?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesO[i]==7){
                    imageView7?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesO[i]==8){
                    imageView8?.setBackgroundColor(Color.BLUE)
                }
                if(posicionesO[i]==9){
                    imageView9?.setBackgroundColor(Color.BLUE)
                }
            }
        }
    }
    fun deshabilitar(){
        imageView1?.isEnabled=false
        imageView2?.isEnabled=false
        imageView3?.isEnabled=false
        imageView4?.isEnabled=false
        imageView5?.isEnabled=false
        imageView6?.isEnabled=false
        imageView7?.isEnabled=false
        imageView8?.isEnabled=false
        imageView9?.isEnabled=false
    }
    fun reiniciar(view: View){
        val intento=intent
        finish()
        startActivity(intento)
    }
}