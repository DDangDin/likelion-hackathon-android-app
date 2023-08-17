package com.hackathon.quki.core.common

import com.google.gson.Gson

object MegaCoffee {

    /** Menu **/
    val menuRawValue =
        "    {\n" +
                "        name : '메가리카노',\n" +
                "        id : 1\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 아메리카노',\n" +
                "        id : 2\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 아메리카노',\n" +
                "        id : 3\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 카페라떼',\n" +
                "        id : 4\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 카페라떼',\n" +
                "        id : 5\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 카페모카',\n" +
                "        id : 6\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 카페모카',\n" +
                "        id : 7\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 카푸치노',\n" +
                "        id : 8\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 카푸치노',\n" +
                "        id : 9\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 카라멜 마끼야또',\n" +
                "        id : 10\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 카라멜 마끼야또',\n" +
                "        id : 11\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 콜드블루',\n" +
                "        id : 12\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 콜드블루',\n" +
                "        id : 13\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 콜드블루 라떼',\n" +
                "        id : 14\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 콜드블루 라떼',\n" +
                "        id : 15\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 연유 라떼',\n" +
                "        id : 16\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 큐브 라떼',\n" +
                "        id : 17\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 헤이즐넛 아메리카노',\n" +
                "        id : 18\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 헤이즐넛 아메리카노',\n" +
                "        id : 19\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 헤이즐넛 라떼',\n" +
                "        id : 20\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 헤이즐넛 라떼',\n" +
                "        id : 21\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 꿀아메리카노',\n" +
                "        id : 22\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 꿀아메리카노',\n" +
                "        id : 23\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 바닐라 아메리카노',\n" +
                "        id : 24\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 바닐라 아메리카노',\n" +
                "        id : 25\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '블루 레몬에이드',\n" +
                "        id : 26\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '레몬에이드',\n" +
                "        id : 27\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '체리콕',\n" +
                "        id : 28\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '자몽 에이드',\n" +
                "        id : 29\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '청포도 에이드',\n" +
                "        id : 30\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '라임 모히또',\n" +
                "        id : 31\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '메가 에이드',\n" +
                "        id : 32\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '유니콘 매직 에이드(블루)',\n" +
                "        id : 33\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '유니콘 매직 에이드(핑크)',\n" +
                "        id : 34\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '흑당 버블 라떼',\n" +
                "        id : 35\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '흑당 버블 밀크티',\n" +
                "        id : 36\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '흑당 버블 라떼(펄x)',\n" +
                "        id : 37\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '흑당 버블 밀크티(펄x)',\n" +
                "        id : 38\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 곡물 라떼',\n" +
                "        id : 39\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name :  '뜨거운 곡물 라떼',\n" +
                "        id : 40\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 녹차 라떼',\n" +
                "        id : 41\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 녹차 라떼',\n" +
                "        id : 42\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 초코',\n" +
                "        id : 43\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 초코',\n" +
                "        id : 44\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 메가초코',\n" +
                "        id : 45\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 메가초코',\n" +
                "        id : 46\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '오레오 초코 라떼',\n" +
                "        id : 47\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 로얄 밀크티',\n" +
                "        id : 48\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 로얄 밀크티',\n" +
                "        id : 49\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '딸기 라떼',\n" +
                "        id : 50\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 고구마 라떼',\n" +
                "        id : 51\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 고구마 라떼',\n" +
                "        id : 52\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 사과유자차',\n" +
                "        id : 53\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 사과유자차',\n" +
                "        id : 54\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 캐모마일차',\n" +
                "        id : 55\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 캐모마일차',\n" +
                "        id : 56\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 유자차',\n" +
                "        id : 57\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 유자차',\n" +
                "        id : 58\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 자몽 티',\n" +
                "        id : 59\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 자몽 티',\n" +
                "        id : 60\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 녹차',\n" +
                "        id : 61\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 녹차',\n" +
                "        id : 62\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '차가운 허니 자몽 블랙티',\n" +
                "        id : 63\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '뜨거운 허니 자몽 블랙티',\n" +
                "        id : 64\n" +
                "    }\n" +
                "-\n" +
                "    {\n" +
                "        name : '복숭아 아이스티',\n" +
                "        id : 65\n" +
                "    }\n"

    val menuRawValue2 = menuRawValue.split("-")

    val megaCoffeeMenuList = hashMapOf<Int, String>()
    fun getMegaCoffeeMenu() {
        menuRawValue2.forEach {
            val entity = Gson().fromJson(it, MegaCoffeeMenuEntity::class.java)
            megaCoffeeMenuList[entity.id] = entity.name
        }
    }

    /** Kiosk Category **/
    val kioskCategoryRawValue = "    {\n" +
            "        name : '전체',\n" +
            "        type : 'all',\n" +
            "        id : 1\n" +
            "    }\n" +
            "-\n" +
            "    {\n" +
            "        name : '커피',\n" +
            "        type : 'coffee',\n" +
            "        id : 2\n" +
            "    }\n" +
            "-\n" +
            "    {\n" +
            "        name : '에이드',\n" +
            "        type : 'ade',\n" +
            "        id : 3\n" +
            "    }\n" +
            "-\n" +
            "    {\n" +
            "        name : '음료',\n" +
            "        type : 'drink',\n" +
            "        id : 4\n" +
            "    }\n" +
            "-\n" +
            "    {\n" +
            "        name : '차',\n" +
            "        type : 'tea',\n" +
            "        id : 5\n" +
            "    }"

    val kioskCategoryRawValue2 = kioskCategoryRawValue.split("-")

    val megaCoffeeKioskCategoryList = hashMapOf<Int, String>()
    fun getMegaCoffeeKioskCategory() {
        kioskCategoryRawValue2.forEach {
            val entity = Gson().fromJson(it, MegaCoffeeCategoryEntity::class.java)
            megaCoffeeKioskCategoryList[entity.id] = entity.name
        }
    }

    /** Store **/
    val storeRawValue = " {\n" +
            "        id : 0,\n" +
            "        brand : '메가 커피',\n" +
            "        type : '카페'\n" +
            "    }\n" +
            "-\n" +
            "    {\n" +
            "        id : 1,\n" +
            "        brand : '공차',\n" +
            "        type : '카페'\n" +
            "    }\n" +
            "-\n" +
            "    {\n" +
            "        id : 2,\n" +
            "        brand : 'KFC',\n" +
            "        type : '패스트푸드'\n" +
            "    }\n" +
            "-\n" +
            "    {\n" +
            "        id : 3,\n" +
            "        brand : '버거킹',\n" +
            "        type : '패스트푸드'\n" +
            "    }\n" +
            "-\n" +
            "    {\n" +
            "        id : 4,\n" +
            "        brand : '롯데리아',\n" +
            "        type : '패스트푸드'\n" +
            "    }"

    val storeRawValue2 = storeRawValue.split("-")

    val megaCoffeeStoreList = hashMapOf<Int, String>()
    fun getMegaCoffeeStore() {
        storeRawValue2.forEach {
            val entity = Gson().fromJson(it, MegaCoffeeStoreEntity::class.java)
            megaCoffeeStoreList[entity.id] = entity.brand
        }
    }

    /** Category **/
    fun getMegaCoffeeCategory(id: Int): String {
        storeRawValue2.forEach {
            val entity = Gson().fromJson(it, MegaCoffeeStoreEntity::class.java)
            return if (entity.id == id) {
                entity.type
            } else {
                ""
            }
        }
        return ""
    }

    /** Category (For Filter) **/
    val megaCoffeeCategoryList = listOf(
        "분식",
        "카페",
        "패스트푸드",
        "한식",
        "일식",
        "중식"
    )

    fun megaCoffeeOptionIceMapping(value: String) = when (value) {
        "less" -> "적게"
        "basic" -> "기본"
        "more" -> "많이"
        else -> "기본"
    }

    fun megaCoffeeOptionCreamMapping(value: String) = when (value) {
        "less" -> "적게"
        "basic" -> "기본"
        "more" -> "많이"
        else -> "기본"
    }

    fun megaCoffeeOptionShotMapping(value: String) = when (value) {
        "basic" -> "기본"
        "oneshot" -> "1"
        "twoshot" -> "2"
        else -> "기본"
    }
}