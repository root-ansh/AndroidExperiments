package `in`.curioustools.architectures.utils

import java.util.*
import kotlin.random.Random

//==================================util functions =================================================
//
//class SampleData {
//    companion object{
//        fun getSampleMenuCategory() = MenuCategory(
//            "cat_${UUID.randomUUID().toString()}".substring(0, 8)
//        )
//        fun getSampleMenuItem2Price(): MenuItem {
//            val rnd = Random.nextInt(0, 1000) % 200
//            return MenuItem("itm_$rnd", rnd, rnd + 50)
//        }
//        fun getSampleMenuItem1Price(): MenuItem {
//            val rnd = Random.nextInt(0, 1000) % 200
//            return MenuItem("itm_$rnd", rnd + 50)
//        }
//        fun getSampleManyManyListCI():List<MenuCategoryWithAssocItems>{
//
//            val items = listOf(
//                //Breads
//                MenuItem("Naan", 25),
//                MenuItem("Tawa Roti", 7),
//                MenuItem("Tandoori Roti", 5),
//                MenuItem("Lachha", 15),
//                MenuItem("Aloo Paratha", 20),
//                MenuItem("Gobhi Paratha", 35),
//                MenuItem("Paneer Paratha", 25),
//
//                //Daals
//                MenuItem("Dal Makhini", 50, 80),
//                MenuItem("Dal Peeli", 40, 70),
//                MenuItem("Dal Fry", 80),
//                MenuItem("Dal Tadks", 50, 80),
//                MenuItem(
//                    "Rajma",
//                    40,
//                    90
//                )/*also a subzi*/,
//                MenuItem("Chhole", 50, 90),
//                MenuItem("Chhole Fry", 60, 100),
//
//
//                //Sabzis
//                MenuItem("Aloo Gobhi", 70, 100),
//                MenuItem("kadhi", 120),
//                MenuItem("Aloo matar", 70, 100),
//
//                //Paneers
//                MenuItem("Shahi Paneer", 90, 150),
//                MenuItem("Kadhai Paneer", 100, 160),
//                MenuItem(
//                    "Aloo Paneer",
//                    100,
//                    110
//                )/*also a subzi*/,
//
//                //Extras
//                MenuItem("Butter", 5),
//
//                //Snacks
//                MenuItem("Muradabadi Dal", 40),/*also a a dal*/
//                MenuItem("Pizza", 150)
//            )
//            val cats = listOf(
//                MenuCategory("Breads"),
//                MenuCategory("Dals"),
//                MenuCategory("Sabzi"),
//                MenuCategory("Paneer"),
//                MenuCategory("Extras"),
//                MenuCategory("Snacks")
//            )
//
//            return listOf(
//
//                MenuCategoryWithAssocItems(
//                    cats[0],
//                    items.subList(0, 7)
//                ),
//
//                MenuCategoryWithAssocItems(
//                    cats[1],
//                    items.subList(7, 14)
//                ),
//
//                MenuCategoryWithAssocItems(
//                    cats[2],
//                    items.subList(14, 17)
//                ),
//
//                MenuCategoryWithAssocItems(
//                    cats[3],
//                    items.subList(17, 20)
//                ),
//
//                MenuCategoryWithAssocItems(
//                    cats[4],
//                    items.subList(20, 21)
//                )
//
//            )
//
//
//
//        }
//        fun getSampleAllCategoriesList(item: MenuItem?=null)= listOf(
//            MenuCategory("Breads"),
//            MenuCategory("Dals"),
//            MenuCategory("Sabzi"),
//            MenuCategory("Paneer"),
//            MenuCategory("Extras"),
//            MenuCategory("Snacks")
//        )
//
//        fun getSampleItemList() = listOf(
//            //Breads
//            MenuItem("Naan", 25),
//
//
//            //Daals
//            MenuItem("Dal Makhini", 50, 80),
//
//            //Sabzis
//            MenuItem("Aloo Gobhi", 70, 100),
//
//            //Paneers
//            MenuItem("Shahi Paneer", 90, 150),
//
//            //Extras
//            MenuItem("Butter", 5),
//
//            //Snacks
//            MenuItem("Muradabadi Dal", 40),/*also a a dal*/
//            MenuItem("Pizza", 150)
//
//
//        )
//
//        fun getSampleCatItemOneOneDataCI(){//:List<MenuCategoryWithAssocItems>{
////    // all data is of format Category--Item. no category has 2 items, no item belongs to 2 categories
////    //return type looks like that of category->list<Item>, but that's just a representation
////
////    val items = getSampleItemList()
////    val cats = getSampleCategoriesList()
////
////    return listOf(
////        MenuCategoryWithAssocItems(cats[0], listOf(items[0])),  /* BREADS----[ NAAN ] */
////        MenuCategoryWithAssocItems(cats[1], listOf(items[1])),  /* BREADS----[ NAAN ] */
////        MenuCategoryWithAssocItems(cats[2], listOf(items[2])),  /* BREADS----[ NAAN ] */
////        MenuCategoryWithAssocItems(cats[3], listOf(items[3])),  /* BREADS----[ NAAN ] */
////        MenuCategoryWithAssocItems(cats[4], listOf(items[4])),  /* BREADS----[ NAAN ] */
////        MenuCategoryWithAssocItems(cats[5], listOf(items[6]))   /* EXTRAS----[ PIZZA ] */
////    )
//            //todo
//
//
//        }
//
//        fun getSampleCatItemOneOneDataIC(){//}:List<MenuItemWithAssocCategories>{
//            // all data is of format Category--Item. no category has 2 items, no item belongs to 2 categories
//            //return type looks like that of Item->list<Category>, but that's just a representation
////
////    val items = getSampleItemList()
////    val cats = getSampleCategoriesList()
////
////    return listOf(
////        MenuItemWithAssocCategories(items[0], listOf(cats[0])),  /* NAAN----[ BREADS ] */
////        MenuItemWithAssocCategories(items[1], listOf(cats[1])),  /* NAAN----[ BREADS ] */
////        MenuItemWithAssocCategories(items[2], listOf(cats[2])),  /* NAAN----[ BREADS ] */
////        MenuItemWithAssocCategories(items[3], listOf(cats[3])),  /* NAAN----[ BREADS ] */
////        MenuItemWithAssocCategories(items[4], listOf(cats[4])),  /* NAAN----[ BREADS ] */
////        MenuItemWithAssocCategories(items[5], listOf(cats[6]))   /* PIZZA----[ EXTRAS ] */
////    )
//
//            // TODO: 20/06/20
//
//        }
//
//        fun getSampleCatItemOneManyDataCI(){//:List<MenuCategoryWithAssocItems>{
//            // all data is of format Category-->list<Item>. category could have multiple items, but items
//            // of one category will not belong to other
////
////    val items = getSampleItemList()
////    val cats = getSampleCategoriesList()
////
////    return listOf(
////        MenuCategoryWithAssocItems(cats[0], listOf(items[0])),
////        MenuCategoryWithAssocItems(cats[1], listOf(items[1])),
////        MenuCategoryWithAssocItems(cats[2], listOf(items[2])),
////        MenuCategoryWithAssocItems(cats[3], listOf(items[3])),
////        MenuCategoryWithAssocItems(cats[4], listOf(items[4])),
////        MenuCategoryWithAssocItems(cats[5], listOf(items[5]))
////    )
//            // TODO: 20/06/20
//
//        }
//
//        fun getSampleCatItemOneManyDataIC(){//:List<MenuItemWithAssocCategories>{
//            // TODO: 20/06/20
//        }
//    }
//}
//



