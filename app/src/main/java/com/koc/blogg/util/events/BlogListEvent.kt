package com.koc.blogg.util.events

/**
Created by kelvin_clark on 7/20/2021 4:53 PM
 */
sealed class BlogListEvent {
    object FetchError: BlogListEvent()
    object ListEmpty: BlogListEvent()
}