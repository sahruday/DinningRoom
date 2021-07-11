package com.sahu.dinningroom.appUtil

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.sahu.dinningroom.data.cache.dao.*
import com.sahu.dinningroom.data.cache.dao.tables.*


const val DB_NAME = "dinningroom.db"

@Database(
    entities = [Orders::class, Item::class, AddOn::class, Menu::class, AddOnIds::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun orderDao() : OrdersDao
    abstract fun menuDao() : MenuDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) : AppDatabase{
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()
                        .addCallback(object : RoomDatabase.Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                fillInDB(context.applicationContext, db)
                            }
                        })
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }

        private fun fillInDB(context: Context, db: SupportSQLiteDatabase) {
            for(menu in MENU_ITEMS)
                db.execSQL("INSERT INTO Menu (id, Name, category, availableQuantity) VALUES (?, ?, ?, ?)", menu.toTypedArray())
            for (addOn in ADD_ONS)
                db.execSQL("INSERT INTO AddOnIds (id, addOnId) VALUES (?, ?)", addOn.toTypedArray())
        }

        val MENU_ITEMS = arrayListOf(
            arrayListOf(1, "Margherita", 1, 40),
            arrayListOf(2, "Indi Chicken Tikka", 1, 40),
            arrayListOf(3, "Chicken golden delight", 1, 40),
            arrayListOf(4, "Non veg Loaded", 1, 40),
            arrayListOf(5, "Chicken Meatballs", 3, 40),
            arrayListOf(6, "Taco Mexicana Non veg", 1, 12),
            arrayListOf(7, "Classic garlic bread", 1, 10),
            arrayListOf(8, "Panner & Onion", 1, 4),
            arrayListOf(9, "Tomato", 2, 40),
            arrayListOf(10, "Cheese", 2, 40),
            arrayListOf(11, "Pepsi", 3, 0),
            arrayListOf(12, "Sprite", 3, 40),
            arrayListOf(13, "Onion", 2, 40),
            arrayListOf(14, "Thin crust", 2, 2),
            arrayListOf(15, "Fesh pan", 2, 5),
        )

        val ADD_ONS = arrayListOf(
            arrayListOf(1, 9),
            arrayListOf(1, 10),
            arrayListOf(1, 11),
            arrayListOf(1, 12),
            arrayListOf(1, 13),
            arrayListOf(1, 14),
            arrayListOf(2, 9),
            arrayListOf(2, 10),
            arrayListOf(2, 11),
            arrayListOf(2, 12),
            arrayListOf(2, 13),
            arrayListOf(2, 14),
            arrayListOf(3, 9),
            arrayListOf(3, 10),
            arrayListOf(3, 11),
            arrayListOf(3, 12),
            arrayListOf(3, 13),
            arrayListOf(3, 14),
            arrayListOf(4, 9),
            arrayListOf(4, 10),
            arrayListOf(4, 11),
            arrayListOf(4, 12),
            arrayListOf(4, 13),
            arrayListOf(4, 14),
        )
    }
}