package com.tiki.challenge.model

import com.tiki.challenge.utils.DrawableUtils

class KeySearchModel(var data: String) : BaseModel() {
    var color: Int = DrawableUtils.generateColor()
}