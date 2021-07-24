package com.koc.blogg.util.events

/**
Created by kelvin_clark on 7/24/2021 3:32 PM
 */
sealed class BlogDetailsEvent {
    object FetchSuccessFull: BlogDetailsEvent()
    object FetchFailed: BlogDetailsEvent()
}
