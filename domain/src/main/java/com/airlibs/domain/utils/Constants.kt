package com.airlibs.domain.utils

import com.airlibs.domain.utils.Constants.INVALID_ENTITY_ID

object Constants {
    const val TOKEN_KEY = "token"
    const val REFRESH_TOKEN_KEY = "refresh_token"
    const val USER_ID_KEY = "user_id"
    const val BUSINESS_ID_KEY = "business_id"
    const val STORE_ID_KEY = "store_id"
    const val USER_TYPE_ID_KEY = "user_type_id"
    const val DEFAULT_PRINTER_KEY = "default_printer"
    const val IS_ACCOUNT_ACTIVE_KEY = "is_account_active"
    const val INVALID_ENTITY_ID = 0

    const val SEARCH_KEY = "Search"
    const val FILTER_KEY = "filter"
    const val CATEGORY_ID_KEY = "categoryId"
    const val SUB_CATEGORY_ID_KEY = "subCategoryId"
    const val INVENTORY_ID_KEY = "inventoryId"
    const val STORE_DATA_KEY = "store_data"
    const val DEFAULT_CURRENCY_KEY = "default_currency_key"

    const val DEFAULT_CHUNK_PAGE_SIZE = 50

}

 fun Int?.toBoolean(): Boolean {
    return this == 1
}
 fun Int.isInvalid(): Boolean {
     return  this == INVALID_ENTITY_ID
}
