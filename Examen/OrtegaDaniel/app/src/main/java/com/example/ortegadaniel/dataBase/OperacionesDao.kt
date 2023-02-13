package com.example.ortegadaniel

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.ortegadaniel.Control.Categoria
import com.example.ortegadaniel.Control.Producto


class OperacionesDao(contexto: Context) {


    private val mBD: SQLiteDatabase

    init {
        val estructura = MyDBOpenHelper(
            contexto,//Interface with all the activity information, you can call him as you call the activity or class
            MyDBOpenHelper.DATABASE_NAME,//The dataBase
            null,//No idea
            MyDBOpenHelper.DATABASE_VERSION//The version
        )
        mBD = estructura.writableDatabase


    }

    fun tablasVacias(): Boolean {
        val cursor: Cursor = mBD.rawQuery(
            "Select * from" +
                    " ${MyDBOpenHelper.TABLA_USUARIO} ", null
        )
        val isVacia = !cursor.moveToFirst()
        if (!cursor.isClosed)
            cursor.close()
        return isVacia
    }


    fun addUsuario(login: String,pass:String) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.LOGIN, login)
        values.put(MyDBOpenHelper.PASS,pass)
        mBD.insert(MyDBOpenHelper.TABLA_USUARIO, null, values)
    }

    fun addCategoria(denominacion: String, url: String) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.DENOMINACION_CATEGORIA, denominacion)
        values.put(MyDBOpenHelper.IMAGEN, url)
        mBD.insert(MyDBOpenHelper.TABLA_CATEGORIA, null, values)
    }

    fun addProducto(codigo: Int, categoria: Int,denominacion: String,precio:Int,exclusividad:String,url: String) {
        val values = ContentValues()
        values.put(MyDBOpenHelper.CODIGO_PRODUCTO, codigo)
        values.put(MyDBOpenHelper.COD_CATEGORIA, categoria)
        values.put(MyDBOpenHelper.DENOMINACION_PRODUCTO,denominacion)
        values.put(MyDBOpenHelper.PRECIO,precio)
        values.put(MyDBOpenHelper.EXCLUSIVO,exclusividad)
        values.put(MyDBOpenHelper.IMAGEN,url)
        mBD.insert(MyDBOpenHelper.TABLA_PRODUCTO, null, values)
    }


    fun getCategorias(): MutableList<Categoria> {
        var list = mutableListOf<Categoria>()
        var cursor: Cursor = mBD.rawQuery(
            "select * from ${MyDBOpenHelper.TABLA_USUARIO}",
            null
        )
        while (cursor.moveToNext())
            list.add(
                Categoria(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2)
                )
            )
        return list
    }

    fun getProductosExclusivosOf(categoria:Int):MutableList<Producto>{
        var list = mutableListOf<Producto>()
        var cursor: Cursor = mBD.rawQuery(
            "select * from ${MyDBOpenHelper.TABLA_PRODUCTO} " +
                    "where cod_categoria=$categoria and exclusividad='SI'",
            null
        )
        while (cursor.moveToNext()){
            list.add(Producto(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getString(5)
            ))
        }
        return list
    }

    fun getProductosOf(categoria:Int):MutableList<Producto>{
        var list = mutableListOf<Producto>()
        var cursor: Cursor = mBD.rawQuery(
            "select * from ${MyDBOpenHelper.TABLA_PRODUCTO} " +
                    "where cod_categoria=$categoria",
            null
        )
        while (cursor.moveToNext()){
            list.add(Producto(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getInt(3),
                cursor.getString(4),
                cursor.getString(5)
            ))
        }
        return list
    }



    fun insertarDatos() {
        addUsuario("1","1")
        addUsuario("2","2")
        addUsuario("3","3")
        addCategoria("Pantalones", "https://upload.wikimedia.org/wikipedia/commons/d/da/Trousers-colourisolated.jpg")
        addCategoria("Zapatos", "https://i.ebayimg.com/images/g/xiAAAOSwqNlimnj8/s-l500.jpg")
        addCategoria("Vestidos", "http://cdn.shopify.com/s/files/1/1631/3281/products/vestido-morado-tirantes-vuelo-invitada-boda-evento-mujer-the-are-00006.jpg?v=1632411588")
        addProducto(1, 1,"Vaquero",45,"NO","https://www.gutteridge.com/dw/image/v2/BDJZ_PRD/on/demandware.static/-/Sites-catalog-gutteridge-master/default/dwba200095/hi-res/5T4189UOGU_0218C_005.jpg?sw=1000&sh=1350&q=90&strip=false")
        addProducto(2, 1,"Rosa",99,"SI","https://thinkingmu.com/21092-large_default/pantalon-theresa-de-pana-rosa.jpg")
        addProducto(3, 2,"Jordan",87, "NO","https://cdn.grupoelcorteingles.es/SGFM/dctm/MEDIA03/202212/21/00132429600268____8__640x640.jpg")
        addProducto(4, 2,"AstroBoy",999,"SI","https://media.revistagq.com/photos/63e36abb6c6ab595e1330bbd/1:1/w_1600,h_1600,c_limit/Big%20Red%20Boots%20de%20MSCHF-1.jpg")
        addProducto(5, 3,"Purpura",58,"NO","https://puravidaclothes.com/16163-large_default/vestido-clavage-negro.jpg")
        addProducto(6, 3,"Exclusivo",98,"SI","https://27412.cdn.simplo7.net/static/27412/sku/thumb_roupas-vestidos-vestido-longo-ramona-verde-bandeira--p-1665773052290.jpg")


    }


}
